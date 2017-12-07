package com.acec.commons.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

public class FileUtil {

	public HttpServletResponse download(String path, HttpServletResponse response) throws IOException{

		// path是指欲下载的文件的路径。
		File file = new File(path);
		// 取得文件名。
		String filename = file.getName();
		// 取得文件的后缀名。
		String ext = filename.substring(filename.lastIndexOf(".") + 1)
				.toUpperCase();

		// 以流的形式下载文件。
		InputStream fis = new BufferedInputStream(new FileInputStream(path));
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		// 清空response
		response.reset();
		// 设置response的Header
		byte[] b = filename.getBytes("GBK");
		filename = new String(b, "8859_1");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ filename);
		response.addHeader("Content-Length", "" + file.length());
		OutputStream toClient = new BufferedOutputStream(response
				.getOutputStream());
		response.setContentType("application/octet-stream");
		toClient.write(buffer);
		toClient.flush();
		toClient.close();

		return response;
    }

    public void downloadLocal(HttpServletResponse response) throws IOException {
        // 下载本地文件
        String fileName = "Operator.doc".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("c:/Operator.doc");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
		int len;
		while((len = inStream.read(b)) > 0)
			response.getOutputStream().write(b, 0, len);
		
		inStream.close();
       
    }

    public void downloadNet(HttpServletResponse response) throws IOException {
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;

		URL url = new URL("windine.blogdriver.com/logo.gif");

		URLConnection conn = url.openConnection();
		InputStream inStream = conn.getInputStream();
		FileOutputStream fs = new FileOutputStream("c:/abc.gif");

		byte[] buffer = new byte[1204];
		int length;
		while ((byteread = inStream.read(buffer)) != -1) {
			bytesum += byteread;
			System.out.println(bytesum);
			fs.write(buffer, 0, byteread);
		}

    }
    
//  文件拷贝  
	public void bakFile(File theFile, String createFilePath) throws IOException {
	  //FileInputStream fis=new FileInputStream(theFile);
	     /*加入缓冲区*/
	   BufferedInputStream fis = new BufferedInputStream(new FileInputStream(theFile)); 
	  
	   /*InputStream 与 Reader对应，只是用的读取和写入是char，支持UNICODE*/
	    // 2.BufferedReader fis = new BufferedReader(new FileReader(file));
	     
	  // FileOutputStream bw= new FileOutputStream(createFilePath);
	   BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(createFilePath));
	     //2.BufferedWriter bw = new BufferedWriter(new FileWriter(cFile));

	   byte[] buf = new byte[1024];  
	   //char []buf = new char[1024];
	   int c;
	   while((c = fis.read(buf))!=-1)//返回实际读取到的大小
	   {
	    bw.write(buf,0,c);
	  //  String s = new String(buf,0,c);
	  //  System.out.println(s);
	   }
	   bw.flush(); //刷新缓冲区,记得
	   
	   fis.close();
	   bw.close();
	}

}
