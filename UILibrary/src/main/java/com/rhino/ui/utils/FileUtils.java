package com.rhino.ui.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;


/**
 * Created by wb
 * on 2017/11/21
 * on FileUtils
 */
public class FileUtils {
	
	/**
	 * whether has SDcard
	 * @return true had
	 */
	public static boolean hasSdcard(){
		if (Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}
	
	/**
	 * create directory
	 * 
	 * @param dirPath
	 *            the directory path
	 * @return true success, false failed
	 */
	public static boolean createDir(String dirPath) {
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
	public static boolean createFile(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return false;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				return file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * read the file content
	 * 
	 * @param file
	 *            the file path
	 * @return the content
	 */
	public static String readFile(String file) {
		try {
			int length;
			byte[] bytes = new byte[1024];
			FileInputStream mFileInputStream = new FileInputStream(file);
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			while ((length = mFileInputStream.read(bytes)) != -1) {
				arrayOutputStream.write(bytes, 0, length);
			}
			mFileInputStream.close();
			arrayOutputStream.close();
			return new String(arrayOutputStream.toByteArray());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * read the file content from assets file
	 * 
	 * @param ctx
	 *            the context
	 * @param fileName
	 *            the file name
	 * @return the content
	 */
	public static String readFileFromAssets(Context ctx, String fileName) {
		try {
			int length;
			byte[] bytes = new byte[1024];
			InputStream mInputStream = ctx.getResources().getAssets()
					.open(fileName);
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			while ((length = mInputStream.read(bytes)) != -1) {
				arrayOutputStream.write(bytes, 0, length);
			}
			mInputStream.close();
			arrayOutputStream.close();
			return new String(arrayOutputStream.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * write data to file
	 * 
	 * @param file
	 *            the file path
	 * @param data
	 *            the content
	 * @return true write success
	 */
	public static boolean writeFile(String file, String data){
		FileOutputStream mFileOutputStream;
		try {
			mFileOutputStream = new FileOutputStream(file);
			mFileOutputStream.write(data.getBytes());
			mFileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
