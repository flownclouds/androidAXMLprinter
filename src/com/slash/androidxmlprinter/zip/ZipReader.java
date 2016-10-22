package com.slash.androidxmlprinter.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.text.TextUtils;

public class ZipReader {
	
	private static final String MANIFEST_NAME = "AndroidManifest.xml";
	
	public static boolean readManifestFromZip(String zipPath,String xmlSavePath){
		if(TextUtils.isEmpty(zipPath)||!new File(zipPath).exists()){
			return false;
		}
		ZipInputStream zipReader = null;
		try {
			zipReader = new ZipInputStream(new FileInputStream(zipPath));
			ZipEntry zipEntry;
			while((zipEntry = zipReader.getNextEntry())!=null){
				String name = zipEntry.getName();
				if(name.equals(MANIFEST_NAME)){
					copyMainfest(zipReader,xmlSavePath);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally{
			if(zipReader!=null){
				try {
					zipReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	/**
	 * 读出manifest文件
	 */
	private static void copyMainfest(ZipInputStream zipReader,String xmlPath) {
		FileOutputStream writer = null;
		try {
			writer = new FileOutputStream(xmlPath);
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = zipReader.read(buffer))!=-1){
				writer.write(buffer, 0, len);
				writer.flush();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
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
