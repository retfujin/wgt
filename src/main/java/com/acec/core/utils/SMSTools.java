package com.acec.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class SMSTools {
 

    static File file;

    private static void getFile() {
		if (file == null) {
		    String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().toString()
			    + "blackword.txt";
		    file = new File(path);
		}
    }

    public static String getSubId() {
		String srcId = com.bestnet.base.util.helper.BaseConfigHelper.getProperty("subid");
		if (srcId == null) {
		    return null;
		} else if (srcId.equals("")) {
		    return null;
		} else {
		    return srcId;
		}
    }
    
    public static String getSubPass() {
		String srcPass = com.bestnet.base.util.helper.BaseConfigHelper.getProperty("subpass");
		if (srcPass == null) {
		    return null;
		} else if (srcPass.equals("")) {
		    return null;
		} else {
		    return srcPass;
		}
    }
    
    public static String getSubName() {
		String srcName = com.bestnet.base.util.helper.BaseConfigHelper.getProperty("subname");
		if (srcName == null) {
		    return null;
		} else if (srcName.equals("")) {
		    return null;
		} else {
		    return srcName;
		}
    }

    /**
     * 关键词过滤
     * 
     * @param content
     * @return ""正常 其余为被屏蔽词
     */
    public static String checkWord(String content) {
		String res = "";
		try {
		    getFile();
		    if (file.isFile() && file.exists()) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), "GBK");
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTXT = null;
			while ((lineTXT = bufferedReader.readLine()) != null) {
			    if (content.indexOf(lineTXT.toString()) != -1) {
				res = lineTXT.toString();
				break;
			    }
			    // System.out.println(lineTXT.toString().trim());
			}
			read.close();
		    } else {
			System.out.println("找不到指定的文件！");
		    }
		} catch (Exception e) {
		    System.out.println("读取文件内容操作出错");
		    e.printStackTrace();
		}
		return res;
    }
 
}
