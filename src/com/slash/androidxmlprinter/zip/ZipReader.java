package com.slash.androidxmlprinter.zip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipReader {
	
	private static final String MANIFEST_NAME = "AndroidManifest.xml";
	
	public static void readManifestFromZip(String zipPath){
		ZipInputStream zipReader = null;
		try {
			zipReader = new ZipInputStream(new FileInputStream(zipPath));
			ZipEntry zipEntry;
			while((zipEntry = zipReader.getNextEntry())!=null){
				String name = zipEntry.getName();
				if(name.equals(MANIFEST_NAME)){
					copyMainfest(zipReader);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(zipReader!=null){
				try {
					zipReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 读出manifest文件
	 */
	private static void copyMainfest(ZipInputStream zipReader) {
		FileOutputStream writer = null;
		try {
			writer = new FileOutputStream("D://android.txt");
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
