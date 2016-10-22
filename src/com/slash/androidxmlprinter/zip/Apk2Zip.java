package com.slash.androidxmlprinter.zip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Apk2Zip {
	public static void convertApk2Zip(String apkPath,String zipPath){
		FileInputStream reader = null;
		FileOutputStream writer = null;
		try {
			reader  = new FileInputStream(apkPath);
			writer = new FileOutputStream(zipPath);
			byte[] buffer = new byte[2048];
			int len = 0;
			while((len = reader.read(buffer))!=-1){
				writer.write(buffer, 0, len);
				writer.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
