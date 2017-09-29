package com.zy.cms.elastic;


import java.net.InetAddress;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.stereotype.Component;

import com.zy.cms.util.StringUtil;

 
/**
 * 公共服务
 * 
 * @author hmj
 */
@Component
public class ESClient {
	@Resource
	private GlobalConfig globalConfig;
    private Client client;
    @SuppressWarnings("unused")
	@PostConstruct
    public void init() {
    	try{
    		String cluster_name=globalConfig.getConfigV("cluster.name");
    		String client_transport_ping_timeout=globalConfig.getConfigV("client.transport.ping_timeout")+"";
    		String cluster_nodes=globalConfig.getConfigV("cluster.nodes")+"";
    		Settings settings = Settings.settingsBuilder()   //2.1
    				.put("cluster.name", cluster_name)
    				.put("client.transport.ping_timeout", client_transport_ping_timeout)
    				.put("client.transport.sniff", "true")
    				.put("index.max_result_window", Integer.MAX_VALUE+1)
    				.build();
    		
    		client=TransportClient.builder().settings(settings).build();//2.1
    		if(!StringUtil.isEmpty(cluster_nodes)){
    			String [] str=cluster_nodes.split(",");
    			for(int i=0;i<str.length;i++){
    				String [] t=str[i].split(":");
    				InetSocketTransportAddress inetSocketTransportAddre=new InetSocketTransportAddress(InetAddress.getByName(t[0]), Integer.parseInt(t[1]));
    				client=((TransportClient)client).addTransportAddress(inetSocketTransportAddre);
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
	public Client getClient() {
		return client;
	}
}