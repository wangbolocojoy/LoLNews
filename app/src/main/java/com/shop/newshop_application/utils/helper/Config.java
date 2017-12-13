package com.shop.newshop_application.utils.helper;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.shop.newshop_application.application.MyApplication;
import com.shop.newshop_application.sys.SystemInfo;


/**
 * <p>配置信息</p>
 * Create by LuoLin on 2016年12月06日
 */
public class Config {
	
	/** SharedPreferences文件名 **/
	private static final String SF_PREF_CONFIG_NAME = "Config.newshop";
	/** debug模式 **/
	private static final String SF_KEY_DEBUG_MODE = "Config.debug_mode";
	/** app版本 **/
	private static final String SF_KEY_VERSIN = "Config.version";
	/** 用户名 **/
	private static final String SF_KEY_USER_NAME = "Config.user_name";
	/** 用户密码 **/
	private static final String SF_KEY_USER_PASSWORD = "Config.user_assword";
	/** 第三方openid **/
	private static final String SF_KEY_THIRD_OPEN_ID = "Config.third_open_id";
	/** 第三方type **/
	private static final String SF_KEY_THIRD_TYPE = "Config.third_type";
	/** 上下文 **/
	private Context mContext;
	/** SharedPreferences实例 **/
	private SharedPreferences mSharedPreferences;
	
	public static Config _instance = null;
	public static Config getInstance() {
		if (_instance == null) {
			_instance = new Config(MyApplication.getContext());
		}
		return _instance;
	}
	
	public Config(Context context) {
		this.mContext = context;
		mSharedPreferences = mContext.getSharedPreferences(SF_PREF_CONFIG_NAME, 0);
	}
	
	/**
	 * 设置调试模式开关
	 * @param debug true 调试模式，输出日志到手机内存
	 */
	public void setDebugMode(boolean debug) {
		mSharedPreferences.edit().putBoolean(SF_KEY_DEBUG_MODE, debug).commit();
	}

	/**
	 * 获取是否处于调试模式
	 * @return true 调试模式，输出日志到手机内存
	 */
	public boolean isDebugMode() {
		return mSharedPreferences.getBoolean(SF_KEY_DEBUG_MODE, false);
	}
	
	/**
	 * 设置是否显示引导页
	 * @param showGuide true 显示
	 */
	public void setShowGuide(boolean showGuide) {
		String versionName = showGuide ? "" : SystemInfo.getInstance().getVersionName();
		mSharedPreferences.edit().putString(SF_KEY_VERSIN, versionName).commit();
	}

	/**
	 * 获取是否显示引导页
	 * @return true 显示
	 */
	public boolean isShowGuide() {
		String storeVersion = mSharedPreferences.getString(SF_KEY_VERSIN, "");
		return TextUtils.isEmpty(storeVersion) || 
				!storeVersion.equals(SystemInfo.getInstance().getVersionName());
	}
	
	/**
	 * 保存用户名
	 * @param userName 用户名
	 */
	public void setUserName(String userName) {
		mSharedPreferences.edit().putString(SF_KEY_USER_NAME, userName).commit();
	}

	/**
	 * 获取用户名
	 * @return 用户名
	 */
	public String getUserName() {
		return mSharedPreferences.getString(SF_KEY_USER_NAME, "");
	}
	
	/**
	 * 保存用户密码
	 * @param userPassword 密码
	 */
	public void setUserPassword(String userPassword) {
		mSharedPreferences.edit().putString(SF_KEY_USER_PASSWORD, userPassword).commit();
	}

	/**
	 * 获取用户密码
	 * @return 密码
	 */
	public String getUserPassword() {
		return mSharedPreferences.getString(SF_KEY_USER_PASSWORD, "");
	}
	
	
	/**
	 * 保存第三方登录open id
	 * @param thirdOpenId 第三方登录open id
	 */
	public void setThirdOpenId(String thirdOpenId) {
		mSharedPreferences.edit().putString(SF_KEY_THIRD_OPEN_ID, thirdOpenId).commit();
	}

	/**
	 * 获取第三方登录open id
	 * @return 第三方登录open id
	 */
	public String getThirdOpenId() {
		return mSharedPreferences.getString(SF_KEY_THIRD_OPEN_ID, "");
	}
	
	/**
	 * 保存第三方登录的类型
	 * @param thirdType 类型
	 */
	public void setThirdType(String thirdType) {
		mSharedPreferences.edit().putString(SF_KEY_THIRD_TYPE, thirdType).commit();
	}

	/**
	 * 获取第三方登陆的类型
	 * @return 类型
	 */
	public String getThirdType() {
		return mSharedPreferences.getString(SF_KEY_THIRD_TYPE, "");
	}


	
	
}
