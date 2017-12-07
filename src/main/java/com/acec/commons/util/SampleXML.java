package com.acec.commons.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class SampleXML {
	
	public static void Transform(String xmlFileName, String xslFileName,OutputStream out) {

		try {
			File dataFile = new File(xmlFileName);
			File styleFile = new File(xslFileName);
			InputStream dataStream = new FileInputStream(dataFile);
			InputStream styleStream = new FileInputStream(styleFile);

			Source data = new StreamSource(dataStream);
			Source style = new StreamSource(styleStream);
			Result output = new StreamResult(out);

			Transformer xslt = TransformerFactory.newInstance().newTransformer(
					style);
			xslt.transform(data, output);
			
			out.flush();
			//out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}   
	    
  

	}
	
	public static void main(String[] args){
		
		Transform("booklist.xml","booklist.xsl",System.out);
	}
}
