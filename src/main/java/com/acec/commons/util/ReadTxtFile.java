package com.acec.commons.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadTxtFile {

	public List readTxtFile(File f) {
		List retList = new ArrayList();
//		File f = new File("");
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(f));
			BufferedReader fr = new BufferedReader(read);
			String str ="";
			while ((str = fr.readLine()) != null) 
			{
				if(str.length() == 11 && str.startsWith("1"))
					retList.add(str);
					
//					throw new RuntimeException("有时间号码长度不是11位！");
			}
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retList;

	}

	public static void main(String[] args) {
		ReadTxtFile r = new ReadTxtFile();
//		r.readTxtFile("c:\\tel.txt");
	}

}
