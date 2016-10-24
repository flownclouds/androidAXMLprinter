package com.slash.androidxmlprinter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.slash.androidxmlprinter.axml.XMLparser;
import com.slash.androidxmlprinter.xmlprinter.AXMLDecoder;
import com.slash.androidxmlprinter.zip.Apk2Zip;
import com.slash.androidxmlprinter.zip.ZipReader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
/**
 * 1.重命名apk为zip
 * 2.取出manifest
 * 3.解码manifest并读取permission属性值
 * 4.删除manifest文件
 * 4.恢复apk文件名
 * @author created by W.H.S
 *
 * @date 2016-10-22
 */
public class MainActivity extends Activity {

	protected static final String TAG = "MainActivity";
	static String apkPath = Environment.getExternalStorageDirectory()+"/anzhimarket.apk";
	static String outXMLpath = Environment.getExternalStorageDirectory()+"/manifest.xml";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        syncGetPermissions(new IOnPermissionsDone() {
			
			@Override
			public void onPermissionsDone(List<String> permissions) {
				Log.e(TAG, "Permission get !"+permissions.size());
				for (String p : permissions) {
					Log.e(TAG, "permission = "+p);
				}
			}
		});
    }

	private void syncGetPermissions(final IOnPermissionsDone callback) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				List<String> permissions = AXMLDecoder.getPermissions(apkPath);
				
				if(callback!=null){
					callback.onPermissionsDone(permissions);
				}
			}
		}).start();
	}
	
	public interface IOnPermissionsDone{
		void onPermissionsDone(List<String> permissions);
	}
}
