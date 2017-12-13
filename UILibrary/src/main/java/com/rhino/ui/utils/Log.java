package com.rhino.ui.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;


/**
 * Created by wb
 * on 2017/11/21
 * on Log
 */
public class Log {

	private static final String TAG = "DEBUG";

	/**
	 * the context
	 */
	private static Context mContext;
	/**
	 * whether output log
	 */
	private static boolean mDebug;
	/** 是否写入日志到文件 **/
	/**
	 * whether write log to file
	 */
	private static boolean mWriteFile;
	private static SimpleDateFormat dateFormat = null;
	private static FileOutputStream fileOutputStream = null;
	
	private static Log instance = null;
	public static Log getInstance() {
		if (instance == null)
			instance = new Log();
		return instance;
	}
	
	public void init(Context context, boolean debug, boolean writeFile) {
		mContext = context;
		mDebug = debug;
		mWriteFile = writeFile;
	}

	public static void d(String msg) {
		println(android.util.Log.DEBUG, TAG, buildMessage(msg), null);
	}

	public static void d(String tag, String msg) {
		println(android.util.Log.DEBUG, tag, buildMessage(msg), null);
	}

	public static void i(String msg) {
		println(android.util.Log.INFO, TAG, buildMessage(msg), null);
	}

	public static void i(String tag, String msg) {
		println(android.util.Log.INFO, tag, buildMessage(msg), null);
	}

	public static void w(String msg) {
		println(android.util.Log.WARN, TAG, buildMessage(msg), null);
	}

	public static void w(String tag, String msg) {
		println(android.util.Log.WARN, tag, buildMessage(msg), null);
	}

	public static void e(String msg) {
		println(android.util.Log.ERROR, TAG, buildMessage(msg), null);
	}
	
	public static void e(String msg, Exception ex) {
		if (ex != null) {
			println(android.util.Log.ERROR, TAG, buildMessage(msg) + ex.toString(), null);
		}
	}
	
	public static void e(String tag, String msg, Exception ex) {
		if (ex != null) {
			println(android.util.Log.ERROR, tag, buildMessage(msg) + ex.toString(), null);
		}
	}

	public static void e(Exception ex) {
		if (ex != null) {
			println(android.util.Log.ERROR, TAG, ex.toString(), ex);
		}
	}
	
	public static void e(String tag, String msg) {
		println(android.util.Log.ERROR, tag, buildMessage(msg), null);
	}

	public static void println(int level, String tag, String msg, Exception ex) {
		if (!mDebug) return;
		if (TextUtils.isEmpty(tag)) {
			tag = "";
		}
		if (ex != null) {
			android.util.Log.e(tag, msg, ex);
		} else {
			android.util.Log.println(level, tag, msg);
		}
	}

	public static String buildMessage(String msg) {
		StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
		StringBuilder text = new StringBuilder();
		text.append(caller.getFileName()
				.replace(".java", ""))
				.append(".")
				.append(caller.getMethodName())
				.append("[")
				.append(caller.getLineNumber())
				.append("]:")
				.append(msg);
		if (mWriteFile) {
			writeLog2File(text.toString());
		}
		return text.toString();
	}
	
	public static void writeLog2File(String text){
		try {
			if (fileOutputStream == null) {
                dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault());
                File dir;
                if (Environment.getExternalStorageState()
                		.equals(Environment.MEDIA_MOUNTED)) {
                	dir = new File(Environment.getExternalStorageDirectory(),
                			mContext.getPackageName());
        		} else {
        			dir = mContext.getFilesDir();
        		}
                if(!dir.exists()){
                	dir.mkdirs();
                }
                File file = new File(new File(dir, "log"),
                		"log_"+dateFormat.format(new Date())+".txt");
                if(!file.exists()){
                    file.getParentFile().mkdirs();
                }
                fileOutputStream = new FileOutputStream(file, true);
                dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss.SSS");
			}
			String log = dateFormat.format(new Date()) + ":(" + TAG + ")" 
					+ " >> " + text + "\n";
			fileOutputStream.write(log.getBytes());
			fileOutputStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
