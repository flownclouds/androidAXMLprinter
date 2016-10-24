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
	 * 重命名文件
	 * @param srcPath 原始文件路径
	 * @param dstPath 重命名后的文件路径
	 * @return
	 */
	public static boolean renameFile(String srcPath,String dstPath){
		File srcFile = new File(srcPath);
		if(srcFile.exists()){
			srcFile.renameTo(new File(dstPath));
			return true;
		}
		return false;
	}
	
}
