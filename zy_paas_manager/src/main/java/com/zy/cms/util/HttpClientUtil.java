
package com.zy.cms.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.zy.cms.common.ZyLogger;


/*********************************************************************************************
 * <pre>
 *     FileName: com.zy.util.http.HttpClientUtil
 *         Desc: Http請求客戶端工具類
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-10-08 12:22
 *   LastChange: 2015-10-08 12:22
 *      History:
 * </pre>
 *********************************************************************************************/
public enum HttpClientUtil
{
	INSTANCE;

	private  final ZyLogger	logger	= ZyLogger.getLogger( HttpClientUtil.class ) ;
	private final int      MAX_TOTAL_CONNECTIONS = 200;
	private final int      MAX_ROUTE_CONNECTIONS = 50;
	private final HttpHost DEFAULT_TARGETHOST    = new HttpHost( "http://localhost", 8888 );
	private final int      CONNECT_TIMEOUT       = 61000;
	private final int      SOCKET_TIMEOUT        = 61000;
	private final int      CONN_MANAGER_TIMEOUT  = 61000;

	private final RequestConfig config = RequestConfig.custom().setSocketTimeout( SOCKET_TIMEOUT ).setConnectTimeout( CONNECT_TIMEOUT )
													  .setConnectionRequestTimeout( CONN_MANAGER_TIMEOUT ).build();

	private CloseableHttpClient httpClient = null;

	HttpClientUtil()
	{
		try
		{
			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
			SocketConfig socketConfig = SocketConfig.custom().setTcpNoDelay( true ).build();
			connManager.setDefaultSocketConfig( socketConfig );
			MessageConstraints messageConstraints = MessageConstraints.custom().setMaxHeaderCount( 200 ).setMaxLineLength( 2000 ).build();
			ConnectionConfig connectionConfig = ConnectionConfig.custom().setMalformedInputAction( CodingErrorAction.IGNORE )
																.setUnmappableInputAction( CodingErrorAction.IGNORE ).setCharset( Consts.UTF_8 )
																.setMessageConstraints( messageConstraints ).build();
			connManager.setDefaultConnectionConfig( connectionConfig );
			connManager.setMaxTotal( MAX_TOTAL_CONNECTIONS );
			connManager.setDefaultMaxPerRoute( MAX_ROUTE_CONNECTIONS );
			connManager.setMaxPerRoute( new HttpRoute( DEFAULT_TARGETHOST ), 50 );

			HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler()
			{
				@Override
				public boolean retryRequest( IOException exception, int executionCount, HttpContext context )
				{
					if ( executionCount >= 5 ) return false;
					if ( exception instanceof InterruptedIOException ) return false;
					if ( exception instanceof UnknownHostException ) return false;
					if ( exception instanceof ConnectTimeoutException ) return false;
					if ( exception instanceof SSLException ) return false;
					HttpClientContext clientContext = HttpClientContext.adapt( context );
					HttpRequest       request       = clientContext.getRequest();
					boolean           idempotent    = !( request instanceof HttpEntityEnclosingRequest );
					if ( idempotent ) return true;
					return false;
				}
			};

			httpClient = HttpClients.custom().setConnectionManager( connManager ).setRetryHandler( retryHandler ).build();
		}
		catch ( Exception e )
		{
			logger.error( "HttpClientUtil error : "+e.getMessage(), e );
		}
	}
	/**
	 * 避免特殊字符，請使用 URLEncoder.encode（content,"utf-8"）進行轉換
	 * JSON,XML 等 格式傳遞參數
	 * 自定義header
	 * Create by : 2015年9月2日 下午2:52:24
	 *
	 * @param url
	 * @param msg
	 * @param headerMap
	 * @param postType xml\json ..
	 *
	 * @return
	 *
	 * @throws Exception
	 */
	public String httpPost( String url, String msg, Map<String, String> headerMap, String postType ) throws Exception
	{
		try
		{
			StringEntity stringEntity = new StringEntity( msg, "utf-8" );// 解决中文乱码问题
			stringEntity.setContentType( "application/" + postType );

			logger.info("【HttpClientUtil】json param="+ msg );

			HttpPost httpPost = new HttpPost( url );
			httpPost.setEntity( stringEntity );
			httpPost.addHeader("Connection", "close");
			setHeader( httpPost, headerMap );

			return doPost( httpPost );
		}
		catch ( Exception e )
		{
			logger.error( "【HttpClientUtil】 error ="+e.getMessage(), e );
			throw e;
		}
	}
	/**
	 * header由調用者set
	 * Create by : 2015年9月14日 上午11:54:59
	 */
	private String doPost( HttpPost httpPost ) throws Exception
	{
		CloseableHttpResponse response =null;
		try
		{
			httpPost.setConfig( config );
			httpPost.addHeader("Connection", "close");
			
			int times = 0;
			while (true) {
				try {
					if (times != 0) {
						logger.info("【HttpClientUtil】请求异常,重试第 " + times + " 次.");
					}
					response = httpClient.execute(httpPost, HttpClientContext.create());
				} catch (Exception e) {
					times++;
					if (times > 3) {
						throw e;
					}
				}
				if (response != null) {
					break;
				}
			}

			HttpEntity entity = response.getEntity();
			logger.info( "【HttpClientUtil】entity"+entity );
			
			return EntityUtils.toString( entity, "utf-8" );
		}
		catch ( ClientProtocolException e )
		{
			logger.error( "【HttpClientUtil】 error ="+ e.getMessage(), e );
			throw e;
		}
		catch ( ParseException e )
		{
			logger.error( "【HttpClientUtil】 error ="+ e.getMessage(), e );
			throw e;
		}
		catch ( IOException e )
		{
			logger.error( "【HttpClientUtil】 error ="+ e.getMessage(), e );
			throw e;
		}
		catch ( Exception e )
		{
			logger.error( "【HttpClientUtil】 error="+ e.getMessage(), e );
			throw e;
		}finally
		{
			if ( null != response ) response.close();
		}
	}

	/**
	 * @return the httpClient
	 */
	public CloseableHttpClient getHttpClient()
	{
		return httpClient;
	}

	/**
	 * 避免特殊字符，請使用 URLEncoder.encode（content,"utf-8"）進行轉換
	 *
	 * @param url
	 *
	 * @return
	 *
	 * @throws Exception
	 */
	public String httpPost( String url ) throws Exception
	{
		try
		{
			HttpPost httpPost = new HttpPost( url );
			return doPost( httpPost );
		}
		catch ( Exception e )
		{
			logger.error( "【HttpClientUtil】 error ="+ e.getMessage(), e );
			throw e;
		}

	}

	/**
	 * 避免特殊字符，請使用 URLEncoder.encode（content,"utf-8"）進行轉換
	 *
	 * @param url
	 * @param headerMap
	 *
	 * @return
	 *
	 * @throws Exception
	 */
	public String httpPost( String url, Map<String, String> headerMap ) throws Exception
	{
		try
		{
			HttpPost httpPost = new HttpPost( url );
			httpPost.addHeader("Connection", "close");
			setHeader( httpPost, headerMap );
			return doPost( httpPost );
		}
		catch ( Exception e )
		{
			logger.error( "【HttpClientUtil】 error="+ e.getMessage(), e );
			throw e;
		}

	}

	/**
	 * 避免特殊字符，請使用 URLEncoder.encode（content,"utf-8"）進行轉換
	 * JSON格式傳遞參數
	 * 自定義header
	 * Create by : 2015年9月2日 下午2:52:24
	 */
	public String httpPost( String url, String msg, Map<String, String> headerMap ) throws Exception
	{
		try
		{
			StringEntity stringEntity = new StringEntity( msg, "utf-8" );// 解决中文乱码问题
			stringEntity.setContentType( "application/json" );

			logger.info( "【HttpClientUtil】json param="+ msg );

			HttpPost httpPost = new HttpPost( url );
			httpPost.setEntity( stringEntity );
			httpPost.addHeader("Connection", "close");

			setHeader( httpPost, headerMap );

			return doPost( httpPost );
		}
		catch ( Exception e )
		{
			logger.error( "【HttpClientUtil】 error ="+e.getMessage(), e );
			throw e;
		}
	}

	/**
	 * 避免特殊字符，請使用 URLEncoder.encode（content,"utf-8"）進行轉換
	 * URL參數map
	 * 自定義header
	 * Create by : 2015年9月14日 上午11:02:05
	 */
	public String httpPost( String httpUrl, Map<String, String> paramMap, Map<String, String> headerMap ) throws Exception
	{
		try
		{
			HttpPost httpPost = new HttpPost( httpUrl );
			setParam( httpPost, paramMap );
			httpPost.addHeader("Connection", "close");
			setHeader( httpPost, headerMap );

			return doPost( httpPost );
		}
		catch ( Exception e )
		{
			logger.error( "【HttpClientUtil】 error ="+e.getMessage(), e );
			throw e;
		}
	}

	/**
	 * Create by : 2015年9月14日 上午11:03:15
	 */
	private void setHeader( HttpPost httpPost, Map<String, String> headerMap )
	{
		if ( ( null != headerMap ) && ( headerMap.size() > 0 ) ) for ( Map.Entry<String, String> entry : headerMap.entrySet() )
			httpPost.setHeader( StringUtils.trimToEmpty( entry.getKey() ), StringUtils.trimToEmpty( entry.getValue() ) );

	}

	/**
	 * Create by : 2015年9月14日 上午11:04:22
	 *
	 * @throws UnsupportedEncodingException
	 */
	private void setParam( HttpPost httpPost, Map<String, String> paramMap ) throws UnsupportedEncodingException
	{
		if ( ( null != paramMap ) && !paramMap.isEmpty() )
		{
			List<NameValuePair> params = new LinkedList<NameValuePair>();

			for ( Map.Entry<String, String> entry : paramMap.entrySet() )
				params.add( new BasicNameValuePair( StringUtils.trimToEmpty( entry.getKey() ), StringUtils.trimToEmpty( entry.getValue() ) ) );

			if ( ( null != params ) && !params.isEmpty() ) httpPost.setEntity( new UrlEncodedFormEntity( params, "UTF-8" ) );
		}

	}
	 /**
     * 创建httpURLConnection连接
     * 
     * @return
     * @throws ProtocolException
     */
	public String httpPost(String pathUrl,String str) throws Exception {
        StringBuffer sb = new StringBuffer();
        String rs = null;
		URL url = null;
		try {
			if(StringUtil.isEmpty(pathUrl)){
				return null;
			}
			url = new URL(pathUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        HttpURLConnection httpURLConnection = null;
        try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
        httpURLConnection.setConnectTimeout(this.CONNECT_TIMEOUT);// 连接超时时间
        httpURLConnection.setReadTimeout(this.SOCKET_TIMEOUT);// 读取结果超时时间
        httpURLConnection.setDoInput(true); // 可读
        httpURLConnection.setDoOutput(true); // 可写
        httpURLConnection.setUseCaches(false);// 取消缓存
        httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpURLConnection.setRequestMethod("POST");
        
        DataOutputStream outputStream = new DataOutputStream(httpURLConnection.getOutputStream());
		outputStream.writeBytes(str.toString()); 
		outputStream.flush();
		outputStream.close(); 
		// 获得响应状态 
		int responseCode = httpURLConnection.getResponseCode(); 
		if (HttpURLConnection.HTTP_OK == responseCode) {
			// 连接成功
			// 当正确响应时处理数据 
			String readLine; 
			BufferedReader responseReader; 
			// 处理响应流，必须与服务器响应流输出的编码一致 
			 responseReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8")); 
			 char[] buf = new char[2048];  
			 int k = responseReader.read(buf); 
			 rs = new String(buf,0,k); 
			while ((readLine = responseReader.readLine()) != null) { 
				sb.append(readLine); 
			}
		}
		return rs;
	}
}
