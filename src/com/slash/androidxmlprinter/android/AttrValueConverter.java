package com.slash.androidxmlprinter.android;
/**
 * 将Permission属性值转换成中文字符串
 * @author created by W.H.S
 *
 * @date 2016-10-22
 */
public class AttrValueConverter {
	
	public static String convert(String attrValue){
		String str = null;
		switch (attrValue) {
		case "android.permission.ACCESS_CHECKIN_PROPERTIES":
			str = "允许读写访问 properties表";
			break;
		case "android.permission.ACCESS_COARSE_LOCATION":
		case "android.permission.ACCESS_FINE_LOCATION":
		case "android.permission.ACCESS_MOCK_LOCATION":
		case "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS":
			str = "获取用户的经纬度信息";
			break;
		case "android.permission.ACCESS_NETWORK_STATE":
		case "android.permission.ACCESS_WIFI_STATE":
		case "android.permission.CHANGE_NETWORK_STATE":
		case "android.permission.CHANGE_WIFI_MULTICAST_STATE":
		case "android.permission.CHANGE_WIFI_STATE":
			str = "获取用户的网络信息状态或网络变化信息";
			break;
		case "android.permission.ACCOUNT_MANAGER":
			str = "获取获取账户验证信息";
			break;
		case "android.permission.AUTHENTICATE_ACCOUNTS":
			str = "允许程序通过账户验证方式访问账户管理相关信息";
			break;
		case "android.permission.BLUETOOTH":
		case "android.permission.BLUETOOTH_ADMIN":
		case "android.permission.ACCESS_BLUETOOTH_SHARE":
			str = "允许程序发现和配对蓝牙设备或通过蓝牙分享";
			break;
		case "android.permission.CAMERA":
			str = "请求访问使用照相设备";
			break;
		case "android.permission.INTERNET":
			str = "访问网络连接，可能产生GPRS流量";
			break;
		case "android.permission.VIBRATE":
			str = "允许震动";
			break;
		case "android.permission.EXPAND_STATUS_BAR":
			str = "允许扩展收缩在状态栏";
			break;
		case "android.permission.WRITE_SETTINGS":
			str = "允许程序读取或写入系统设置";
			break;
		case "android.permission.SYSTEM_ALERT_WINDOW":
			str = "允许显示系统窗口";
			break;
		case "android.permission.WAKE_LOCK":
			str = "允许程序在手机屏幕关闭后后台进程仍然运行";
			break;
		case "android.permission.READ_PHONE_STATE":
			str = "允许访问电话状态";
			break;
		case "android.permission.WRITE_EXTERNAL_STORAGE":
			str = "允许程序写入外部存储";
			break;
			
		case "android.permission.READ_LOGS":
			str = "允许程序读取底层系统日志文件";
			break;
		case "android.permission.GET_TASKS":
			str = "获取信息有关当前或最近运行的任务";
			break;
		case "android.permission.MODIFY_AUDIO_SETTINGS":
		case "android.permission.RECORD_AUDIO":
			str = "修改声音设置,允许录音";
			break;
		case "android.permission.DISABLE_KEYGUARD":
			str = "允许程序禁用键盘锁";
			break;
		case "android.permission.INTERACT_ACROSS_USERS_FULL":
			str = "完全交互界面显示";
			break;
		case "android.permission.MOUNT_UNMOUNT_FILESYSTEMS":
			str = "挂载、反挂载外部文件系统";
			break;
		case "android.permission.READ_CONTACTS":
			str = "访问联系人通讯录信息";
			break;
		default:
			str="其他未知权限";
			break;
		}
		return str;
	}
}
