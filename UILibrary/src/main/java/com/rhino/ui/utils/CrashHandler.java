package com.rhino.ui.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.rhino.u.R;
import com.sun.mail.util.MailSSLSocketFactory;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * <p>
 *     The handler about UncaughtException, will write the error info to file, 
 *     and send the debug info to email, need the follow permission.
 * 			<p>"android.permission.WRITE_EXTERNAL_STORAGE"</p>
 * 			<p>"android.permission.MOUNT_UNMOUNT_FILESYSTEMS"</p>
 * 			<p>"android.permission.INTERNET"</p>
 * </p>
 * 
 **/
/**
 * Created by wb
 * on 2017/11/21 
 * on CrashHandler 
 */
public class CrashHandler implements UncaughtExceptionHandler {

	/**
	 * the log tag
	 */
	private static final String TAG = "CrashHandler";

	/**
	 * the SSL encryption
	 */
	private boolean MAIL_SMTP_SSL_ENABLE;
	/**
	 * whether to open the email debug logs
	 */
	private boolean MAIL_DEBUG;
	/**
	 * the authentication is required
	 */
	private boolean MAIL_SMTP_AUTH;
	/**
	 * the mail transport protocol
	 */
	private String MAIL_TRANSPORT_PROTOCOL;
	/**
	 * the host name
	 */
	private String MAILE_HOST;
	/**
	 * the account
	 */
	private String MAILE_USER;
	/**
	 * the authorization password
	 */
	private String MAILE_PASSWORD;
	/**
	 * the sender
	 */
	private String MAILE_FROM;
	/**
	 * the receiver
	 */
	private String MAILE_TO;
	/**
	 * the subject of an email
	 */
	private String MAILE_SUBBJECT;

	/**
	 * the default handler of UncaughtException
	 */
	private UncaughtExceptionHandler mDefaultHandler;
	/**
	 * the file directory path of error information
	 */
	private String dirPath;
	/**
	 * the file file name of error information
	 */
	private String fileName;
	/**
	 * whether send email when crash
	 */
	private boolean sendEmail;
	/**
	 * the error description
	 */
	private String errorDesc;
	/**
	 * the context
	 */
	private Context mContext;

	
	private static CrashHandler instance = null;
	public static CrashHandler getInstance() {
		if (instance == null)
			instance = new CrashHandler();
		return instance;
	}

	private CrashHandler() {
	}

	/**
	 * do something about init
	 * 
	 * @param context
	 *            the context
	 */
	public void init(Context context, String dirPath, String fileName, boolean sendEmail) {
		this.mContext = context;
		this.dirPath = dirPath;
		this.fileName = fileName;
		this.sendEmail = sendEmail;
		/** get the default handler of UncaughtException **/
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		/** set this CrashHandler to the default handler of UncaughtException **/
		Thread.setDefaultUncaughtExceptionHandler(this);
		
		Resources rs = context.getResources();
		MAIL_SMTP_SSL_ENABLE = rs.getBoolean(R.bool.MAIL_SMTP_SSL_ENABLE);
		MAIL_DEBUG = rs.getBoolean(R.bool.MAIL_DEBUG);
		MAIL_SMTP_AUTH = rs.getBoolean(R.bool.MAIL_SMTP_AUTH);
		MAIL_TRANSPORT_PROTOCOL = mContext.getString(R.string.MAIL_TRANSPORT_PROTOCOL);
		MAILE_HOST = mContext.getString(R.string.MAILE_HOST);
		MAILE_USER = mContext.getString(R.string.MAILE_USER);
		MAILE_PASSWORD = mContext.getString(R.string.MAILE_PASSWORD);
		MAILE_FROM = mContext.getString(R.string.MAILE_FROM);
		MAILE_TO = mContext.getString(R.string.MAILE_TO);
		MAILE_SUBBJECT = mContext.getString(R.string.MAILE_SUBBJECT);
	}

	/**
	 * set the error description
	 * 
	 * @param errorDesc
	 *            the error description when crash
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	/**
	 * called when UncaughtException happen
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			/**
			 * If the user does not handle the exception handler to allow the
			 * system to handle the default
			 */
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// exit app
			ActivityManager.getInstance().Exit();
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(1);
		}
	}

	/**
	 * collecting device parameter information and save error information to
	 * file
	 * 
	 * @param ex
	 *            the throwable
	 * @return true:handled; false:not handled
	 */
	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		/** save error information to file **/
		saveCrashInfo2File(ex);

		try {
			new Thread() {
				@Override
				public void run() {
					Looper.prepare();
					Toast.makeText(
							mContext,
							TextUtils.isEmpty(errorDesc) ? "很抱歉，程序出现异常，即将退出"
									: errorDesc, Toast.LENGTH_LONG).show();
					Looper.loop();
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * save error information to file
	 * 
	 * @param ex
	 *            the throwable
	 * @return the file name
	 */
	private String saveCrashInfo2File(Throwable ex) {

		final StringBuffer sb = new StringBuffer();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		sb.append("DATE=" + sdf.format(new Date(System.currentTimeMillis())) + "\n");
		
		String path = getFilePath();
		Log.i(TAG, "path = " + path);
		sb.append("FILE_PATH=" + path + "\n");
		
		ApplicationInfo info = mContext.getApplicationInfo();
		boolean isDebugVersion = (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		sb.append("IS_DEBUG_VERSION=" + isDebugVersion + "\n");
		
		String packageName = mContext.getPackageName();
		sb.append("PACKAGE_NAME=" + packageName + "\n");
		sb.append("APP_NAME=" + mContext.getString(R.string.app_name) + "\n");
		
		try {
			PackageManager pm = mContext.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
			if (pi != null) {
				sb.append("VERSION_NAME=" + pi.versionName + "\n");
				sb.append("VERSION_CODE=" + pi.versionCode + "\n");
			}
		} catch (NameNotFoundException e) {
			Log.e(TAG, "an error occured when collect package info", e);
		}
		
		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				sb.append(field.getName() + "=" +field.get(null).toString() + "\n");
				Log.e(TAG, field.getName() + " : " + field.get(null));
			} catch (Exception e) {
				Log.e(TAG, "an error occured when collect crash info", e);
			}
		}
		
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		sb.append(result);
		Log.e(TAG, result, ex);

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);
			fos.write(sb.toString().getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "error when write file.", e);
		}
		
		if(sendEmail){
			new Thread() {
				@Override
				public void run() {
					Log.i(TAG, "begain send email, MAILE_TO = " + MAILE_TO);
					try {
						if (sendEmail(sb.toString())) {
							Log.d(TAG, "send email success");
						} else {
							Log.d(TAG, "send email failed");
						}
					} catch (Exception e) {
						Log.e(TAG, e.getMessage(), e);
					}
				}
			}.start();
		}
		return path;
	}

	/**
	 * get the debug file path
	 * 
	 * @return the debug file path
	 */
	public String getFilePath() {
		if (!createDir(dirPath)) {
			return null;
		}

		String filePath = dirPath + File.separator + fileName;
		if (!createFile(filePath)) {
			return null;
		}
		return filePath;
	}

	/**
	 * create directory
	 * 
	 * @param dirPath
	 *            the directory path
	 * @return true success, false failed
	 */
	private boolean createDir(String dirPath) {
		if (TextUtils.isEmpty(dirPath)) {
			return false;
		}
		File file = new File(dirPath);
		if (!file.exists()) {
			return file.mkdirs();
		}
		return true;
	}

	/**
	 * create file
	 * 
	 * @param filePath
	 *            the file path
	 * @return true success, false failed
	 */
	private boolean createFile(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return false;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				return file.createNewFile();
			} catch (IOException e) {
				Log.e(TAG, "filePath" + filePath, e);
				return false;
			}
		}
		return true;
	}

	/**
	 * send debug info to email
	 * 
	 * @param debugInfo
	 *            the debug info
	 * @return true send success
	 */
	private boolean sendEmail(String debugInfo) {
		try {
			Properties props = new Properties();
			props.put("mail.debug", MAIL_DEBUG);
			props.put("mail.smtp.auth", MAIL_SMTP_AUTH);
			props.put("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);
			try {
				MailSSLSocketFactory sf = new MailSSLSocketFactory();
				sf.setTrustAllHosts(true);
				props.put("mail.smtp.ssl.enable", MAIL_SMTP_SSL_ENABLE);
				props.put("mail.smtp.ssl.socketFactory", sf);
			} catch (GeneralSecurityException e) {
				Log.e(TAG, e.getMessage(), e);
				return false;
			}

			Session session = Session.getInstance(props);
			Message msg = new MimeMessage(session);
			msg.setSubject(MAILE_SUBBJECT+"("+Build.MANUFACTURER+"-"+Build.MODEL+")");
			msg.setText(debugInfo);
			msg.setFrom(new InternetAddress(MAILE_FROM));

			Transport transport = session.getTransport();
			transport.connect(MAILE_HOST, MAILE_USER, MAILE_PASSWORD);
			transport.sendMessage(msg, new Address[] { new InternetAddress(
					MAILE_TO) });
			transport.close();
		} catch (MessagingException e) {
			Log.e(TAG, e.getMessage(), e);
			return false;
		}
		return true;
	}
}
