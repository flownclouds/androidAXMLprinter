package com.slash.androidxmlprinter.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.text.TextUtils;

public class Apk2Zip {
	public static boolean copyAPK2ZIP(String apkPath,String zipPath){
		if(TextUtils.isEmpty(apkPath)||TextUtils.isEmpty(zipPath)){
			return false;
		}
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
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
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
		return true;
	}
	
	/**
	 * 将APK文件重命名为zip文件
	 * @param srcApk
	 * @return
	 */
	public static boolean renameApk2Zip(String srcApk,String dstZip){
		File srcFile = new File(srcApk);
		if(srcFile.exists()){
//			String dstZip = srcApk.substring(0,srcApk.lastIndexOf("."))+".zip";
			srcFile.renameTo(new File(dstZip));
			return true;
		}
		return false;
	}
	
	/**
	 * 还原APK文件的后缀名
	 * @param srcApk
	 * @return
	 */
	public static boolean resetApk(String srcApk){
		String dstZip = srcApk.substring(0,srcApk.lastIndexOf("."))+".zip";
		File zipFile = new File(dstZip);
		if(zipFile.exists()){
			zipFile.renameTo(new File(srcApk));
			return true;
		}
		return false;
	}
}
