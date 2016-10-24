package com.slash.androidxmlprinter.axml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.slash.androidxmlprinter.android.AttrValueConverter;

import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

public class XMLparser {
	
	static String PERMISSION_TAG = "uses-permission";
	static String ATTR_NAME = "android:name";
	
	public static List<String> parseXMLForPermissions(String xmlPath){
		FileInputStream xmlReader = null;
		List<String> permissions = new ArrayList<String>();
		
		try {
			xmlReader = new FileInputStream(xmlPath);
			XmlPullParser parser = Xml.newPullParser();
			
			parser.setInput(xmlReader, "utf-8");
			int type = parser.getEventType();
			
			while(type!=XmlPullParser.END_DOCUMENT){
				switch (type) {
				case XmlPullParser.START_TAG:
					if(TextUtils.equals(parser.getName(), PERMISSION_TAG)){
						String attrValue = parser.getAttributeValue(0);
						String p = AttrValueConverter.convert(attrValue);
						if(!permissions.contains(p)){
							permissions.add(p);
						}
					}
					break;
				}
				
				type = parser.next();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(xmlReader!=null){
				try {
					xmlReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return permissions;
	}
}
