package com.corewell.study.utils;/*
package com.corewell.study.utils;

import com.jcraft.jsch.*;
import com.jsjunyi.access.domain.SshConfiguration;

import java.io.File;
import java.util.Properties;

public class TranscodingAnalyzerDemo {

	private ChannelSftp channelSftp;  
    private Session session=null;  
    private int timeout=60000;  
      
    public TranscodingAnalyzerDemo(SshConfiguration conf) throws JSchException{  
        System.out.println("try connect to  "+conf.getHost()+",username: "+conf.getUsername()+",password: "+conf.getPassword()+",port: "+conf.getPort());  
        JSch jSch=new JSch(); //创建JSch对象  
        session=jSch.getSession(conf.getUsername(), conf.getHost(), conf.getPort());//根据用户名，主机ip和端口获取一个Session对象  
        session.setPassword(conf.getPassword()); //设置密码  
        Properties config=new Properties();  
        config.put("StrictHostKeyChecking", "no");  
        session.setConfig(config);//为Session对象设置properties  
        session.setTimeout(timeout);//设置超时  
        session.connect();//通过Session建立连接         
    }  
    public void download(String src,String dst) throws JSchException, SftpException, InterruptedException{  
        //src linux服务器文件地址，dst 本地存放地址 
        //判断 如果本地dst如果存在，则先删除，然后在下载 
		File file = new File(dst);
		if(file.exists())
		{
			file.delete();
			Thread.sleep(1000);
		}
        channelSftp=(ChannelSftp) session.openChannel("sftp");  
        channelSftp.connect();  
        channelSftp.get(src, dst);  
        System.out.println(">>>>>>>>>>  File:"+src+" has downloaded success!");
        channelSftp.quit();  
    }  
    public void close(){  
        session.disconnect();  
    }  
	
	public static void main(String[] args)
	{
		 SshConfiguration configuration=new SshConfiguration();  
	        configuration.setHost("192.168.1.26");  
	        configuration.setUsername("cheersqa");  
	        configuration.setPassword("123456");  
	        configuration.setPort(22);  
	        try{  
	        	com.jsjunyi.access.utils.TranscodingAnalyzerDemo sshUtil=new com.jsjunyi.access.utils.TranscodingAnalyzerDemo(configuration);
	            sshUtil.download("/usr/local/1111111111","logs1_26");  
	            sshUtil.close();  
	        }catch(Exception e){  
	            e.printStackTrace();  
	        }      
	}

	
}
*/
