package com.zy.cms.common;

import org.logicalcobwebs.proxool.ProxoolDataSource;

/**
 * 数据库密码密文
 * @author hmj
 *
 */
public class MyProxoolDataSource extends ProxoolDataSource{
	 private static final ZyLogger log  = ZyLogger.getLogger(MyProxoolDataSource.class);
	//重置密码明文
	public void setPassword(String pwd) {
	  
	  log.info("passWord="+pwd);
	  super.setPassword(pwd);
	}
	   
	public String getPassword() {
	  return super.getPassword();
	}
	//重置解密用户
	public void setUser(String user){

		log.info("_user="+user);
		super.setUser(user);
	}
	public String getUser() {
		  return super.getUser();
	}
	//重置解密driverurl
	public void setDriverUrl(String driver){
		
		log.info("driverurl="+driver);
		super.setDriverUrl(driver);
	}
	public String getDriverUrl(){
		return super.getDriverUrl();
	}
}