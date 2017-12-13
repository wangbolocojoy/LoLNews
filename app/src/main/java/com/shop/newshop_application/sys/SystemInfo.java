package com.shop.newshop_application.sys;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.TrafficStats;
import android.os.Build;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;
import java.util.List;


/**
 * Create by LuoLin on 2016年12月6日
 */
public class SystemInfo {
	/** 网络状态 无网络连接 **/
	public static final int NET_STATE_DISABLED = -1;
	/** 网络状态 Wi-Fi **/
	public static final int NET_STATE_WOKE_WIFI = 0x1;
	/** 网络状态 2G **/
	public static final int NET_STATE_WOKE_2G = 0x2;
	/** 网络状态 3G **/
	public static final int NET_STATE_WOKE_3G = 0x3;
	/** 网络状态 4G **/
	public static final int NET_STATE_WOKE_4G = 0x4;

	/** 屏幕状态 屏幕开启 **/
	public static final int SCREEN_ON = 0x1;
	/** 屏幕状态 关闭 **/
	public static final int SCREEN_OFF = 0x2;
	/** 屏幕状态 屏幕开启后，解锁前，该状态不一定有用，有些第三方原件引起的锁屏可能检测不到 **/
	public static final int SCREEN_DISABLE = 0x3;

	/** 厂商名字 **/
	public final String manufacturer = Build.MANUFACTURER;
	public final String product = Build.PRODUCT;
	/** 手机硬件名 如：华为荣耀3c，mx3，红米note等 **/
	public final String hardware = Build.HARDWARE;
	/** 手机系统，各个手机厂商定制的，如：flyme3.5.7xxx，miui4.0.1xxx等 **/
	public final String software = Build.DISPLAY; //
	/** 当前Android原生系统版本, 如：9、17、21等 9是指2.3.x 依次类推 **/
	public final int android_sdk = Build.VERSION.SDK_INT;
	/** 4.2.1格式 **/
	public final String os = Build.VERSION.RELEASE;
	/** 系统uid **/
	public final int process_uid = android.os.Process.myUid();

	/** 手机的总内存 单位M **/
	public long totalMem;
	/** app分配内存的最大值 单位M，不包括额外申请的内存 **/
	public long appAllowMem;

	/** 当前屏幕状态 **/
	public int screen_state;
	/** 当前网络状态 **/
	public int netState;
	/** WiFi状态可用时有效 名称带有引号，需要去掉 **/
	public String wifiSSID;
	/** svn版本信息 在Manifest中申明了的 xxxxx **/
	public String versionCode;
	/** app版本信息1.0 **/
	public String versionName;
	/** 当前程序的包名 **/
	public String packageName;
	/** px:dip的比值 **/
	public float density;
	/** 宽度像素 **/
	public int widthPx;
	/** 高度像素 **/
	public int heightPx;

	public static SystemInfo _instance = null;

	public static SystemInfo getInstance() {
		if (_instance == null) {
			_instance = new SystemInfo();
		}
		return _instance;
	}

	public SystemInfo() {
	}
	
	public boolean isNetEnable(){
		return netState != NET_STATE_DISABLED;
	}

	/** 初始化 固定值 **/
	public void initConstantInfo(Context context) {
		packageName = context.getPackageName();
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (pi != null) {
				versionName = pi.versionName == null ? "0" : pi.versionName;
				versionCode = pi.versionCode + "";
			}

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		density = dm.density;
		widthPx = dm.widthPixels;
		heightPx = dm.heightPixels;

		final ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo mInfo = new MemoryInfo();
		activityManager.getMemoryInfo(mInfo);

		try {
			Field field1 = Class.forName(
					"android.app.ActivityManager$MemoryInfo").getField(
					"totalMem");
			Field field2 = Class.forName(
					"android.app.ActivityManager$MemoryInfo").getField(
					"foregroundAppThreshold");

			if (null != field1) {
				Object tM = field1.get(mInfo);
				if (null != tM) {
					totalMem = ((Long) tM) >> 20;
				}
			}

			if (null != field2) {
				Object appM = field2.get(mInfo);
				if (null != appM) {
					appAllowMem = ((Long) appM) >> 20;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 手机是否在前台运行 **/
	public boolean isAppOnForeground(Context context) {

		if (null == packageName) {
			return false;
		}

		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;

		for (RunningAppProcessInfo appProcess : appProcesses) {
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}

		return false;
	}

	/** 获取app的总上传流量 单位B **/
	public long getAppTx(Context context) {
		return getTrafficStats(context, false);
	}

	/** 获取app的总下载流量 单位B **/
	public long getAppRx(Context context) {
		return getTrafficStats(context, true);
	}

	/** 获取当前app versionName **/
	public String getVersionName() {
		return versionName == null ? "" : versionName;
	}
	
	/** 获取当前app versionCode **/
	public String getVersionCode() {
		return versionCode == null ? "" : versionCode;
	}

	/** 获取app的总上传/总下载流量 单位B **/
	private long getTrafficStats(Context context, boolean isRx) {
		PackageManager pm = context.getPackageManager();// 获取系统应用包管理
		PackageInfo pInfo;
		int uId = 0;
		try {
			pInfo = pm.getPackageInfo(context.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			uId = pInfo.applicationInfo.uid;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		long rx = TrafficStats.getUidRxBytes(uId);
		long tx = TrafficStats.getUidTxBytes(uId);

		if (isRx) {
			return rx;
		} else {
			return tx;
		}
	}

	public String netWorkStatToString() {
		String string = "无网络";
		switch (netState) {
		case NET_STATE_WOKE_WIFI:
			string = "Wi-Fi";
			break;
		case NET_STATE_WOKE_2G:
			string = "2G";
			break;
		case NET_STATE_WOKE_3G:
			string = "3G";
			break;
		case NET_STATE_WOKE_4G:
			string = "4G";
			break;

		default:
			break;
		}

		return string;
	}
}
