package com.acec.commons.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class ReadConfigXML {

	DocumentBuilderFactory dbf;
	DocumentBuilder db;			
	private String inFile;

	public ReadConfigXML(String file){
		init();
		this.inFile = this.getClass().getResource(file).toString();
		
	}

	public void init(){
		dbf = DocumentBuilderFactory.newInstance();
		db= null;
		try{
			db = dbf.newDocumentBuilder();
		}catch(ParserConfigurationException pce){
			System.err.println(pce);
			System.exit(1);
		}

	}
	public String readXMLFile(String field) {

		Document doc=null;
		try{
			doc=db.parse(inFile);
		}catch(DOMException dom){
			System.err.println(dom.getMessage());
			System.exit(1);
		}catch(IOException ioe){
			System.err.println(ioe);
			System.exit(1);
		}catch(Exception e){
			System.err.println(e);
			System.exit(1);
		}

		Element root = doc.getDocumentElement();
//		String[] fields = field.split(",");
				
		NodeList lanmus=root.getElementsByTagName(field);
		String str_text = "";
		if(lanmus.getLength()>0){
			Element e =(Element) lanmus.item(0);
			Text t = (Text) e.getFirstChild();
			str_text =t.getNodeValue();
		}
		
//		for(int i=1;i<fields.length;i++){
//			Element student =(Element)lanmus.item(0);
//			names=student.getElementsByTagName(fields[i]);
//			lanmus = names;
//		}
//		if(names.getLength()==1){
//			Element e =(Element) names.item(0);
//			Text t = (Text) e.getFirstChild();
//			str_text =t.getNodeValue();
//			System.out.println(str_text);
//		}
		return str_text;
	}

	public static void main(String[] args){
		ReadConfigXML xt= new ReadConfigXML("WebRoot/WEB-INF/app.xml");
		try{
			String s = xt.readXMLFile("prefixion");
			System.out.println(s);

		}catch(Exception e){
			System.err.println(e);
		}
	}


}
