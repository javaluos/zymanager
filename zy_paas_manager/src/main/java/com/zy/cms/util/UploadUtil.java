package com.zy.cms.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.zteict.xdfs.FileClient;
import com.zy.cms.common.ZyLogger;

public class UploadUtil {
	private static final ZyLogger logger = ZyLogger.getLogger(UploadUtil.class);
    public static Map<String,Object> upload(InputStream in,String fileId,String fieldName,long size) throws Exception{
    	String statu = "FAIL";
        try {
			fileId = FileClient.instance.upload(in, fieldName, size);
			logger.info("上传ID="+fileId);
			statu="SUCCESS";
		} catch (Exception e) {
			logger.error("上传异常"+e.getMessage(),e);
		}
        if (in != null) {
        	in.close();
        }
        return getRs( fileId, 0,  statu, 0, null);
    }
    
    public static Map<String,Object> getRs(String fileId,long uploadSize,String  statu,long endSize,String uploadStatus) throws Exception{
    	Map jsonObject = new HashMap();
    	jsonObject.put("FILE_ID", fileId);
        jsonObject.put("STATE", statu);
        jsonObject.put("DOWNLOAD_URL", !StringUtil.isEmpty(fileId)?FileClient.instance.getHttpDownloadUrl(fileId):"");
        return jsonObject;
    }
    
    public static Map<String,Object> getExceptionRs(String fileId) throws Exception{
    	Map jsonObject = new HashMap();
    	jsonObject.put("FILE_ID", fileId);
        jsonObject.put("UPLOAD_SIZE", 0);
        jsonObject.put("STATE", "FAIL");
        jsonObject.put("INFO", null);
        jsonObject.put("FILE_END", getSrcFileSize(fileId));
        jsonObject.put("DOWNLOAD_URL", FileClient.instance.getHttpDownloadUrl(fileId));
        jsonObject.put("data", null);
        return jsonObject;
    }
    
    public static void closeStream(InputStream in,OutputStream out)  {
    	if (in != null) {
	          try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }
	      if (out != null) {
	        try {
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
    }
    public static long getSrcFileSize(String fileId) {
    	if(fileId!=null&&!fileId.equals("")){
    		try {
				return FileClient.instance.getFileSize(fileId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return 0;
    }
    public static boolean deleteFile(String sPath) {  
    	boolean flag = false;  
    	File file = new File(sPath);  
    	// 路径为文件且不为空则进行删除  
    	if (file.isFile() && file.exists()) {  
    		file.delete();  
    		flag = true;  
    	}  
    	return flag;  
    }  
}
