package com.blog.news.base;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.blankj.utilcode.util.NetworkUtils;
import com.blog.news.utils.Event;
import com.blog.news.utils.EventBusUtil;
import com.rhino.ui.base.BaseSimpleTitleActivity;
import com.rhino.ui.utils.AlertToast;
import com.rhino.ui.utils.Log;
import com.rhino.ui.widget.LoadingDialog;
import com.blog.news.application.MyApplication;
import com.blog.news.http.request.HttpParams;
import com.blog.news.utils.UiUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 *
 */
public abstract class BaseHttpActivity extends BaseSimpleTitleActivity {
	protected Unbinder mBinder;
	/** 加载对话框 **/
	protected LoadingDialog mLoadingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		mLoadingDialog = new LoadingDialog(this);
		mLoadingDialog.setCanceledOnTouchOutside(false);
		mLoadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialogInterface) {
				onLoadingDialogCancel(dialogInterface);
			}
		});
		super.onCreate(savedInstanceState);
		inButterKnifeView();
		if (isRegisterEventBus()) {
			EventBusUtil.register(this);
		}
	}
	public void inButterKnifeView(){
		mBinder = ButterKnife.bind(this);
	}

	@Override
	protected void onDestroy() {
		// 取消绑定
		mBinder.unbind();
		super.onDestroy();
		dismissLoadingDialog();
		mLoadingDialog = null;
		if (isRegisterEventBus()) {
			EventBusUtil.unregister(this);
		}
	}
	/**
	 * 是否注册事件分发
	 *
	 * @return true绑定EventBus事件分发,默认不绑定,子类需要绑定的话复写此方法返回true.
	 */
	protected boolean isRegisterEventBus() {
		return false;
	}
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEventBusCome(Event event) {
		if (event != null) {
			receiveEvent(event);
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void onStickyEventBusCome(Event event) {
		if (event != null) {
			receiveStickyEvent(event);
		}
	}

	/**
	 * 接收到分发到事件
	 *
	 * @param event 事件
	 */
	protected void receiveEvent(Event event) {

	}

	/**
	 * 接受到分发的粘性事件
	 *
	 * @param event 粘性事件
	 */
	protected void receiveStickyEvent(Event event) {

	}
	/**
	 * 页面跳转
	 * @param cls activity
	 */
	protected void gotoActivity(Class<?> cls){
		UiUtils.showPage(getApplicationContext(), cls, null);
	}

	/**
	 * 页面跳转
	 * @param cls activity
	 * @param extras bundle
	 */
	protected void gotoActivity(Class<?> cls, Bundle extras){
		UiUtils.showPage(getApplicationContext(), cls, extras);
	}

	/**
	 * 加载对话框被取消监听
	 * @param dialogInterface dialogInterface
	 */
	protected void onLoadingDialogCancel(DialogInterface dialogInterface){

	}

	/**
	 * 刷新加载对话框文字描述
	 * @param text 描述
	 */
	protected void updateLoadingDialogText(String text){
		mLoadingDialog.setText(text);
	}

	/** 显示加载对话框 **/
	protected void showLoadingDialog(String text){
		if(null != mLoadingDialog){
			updateLoadingDialogText(text);
			mLoadingDialog.show();
		}
	}

	/** 显示加载对话框 **/
	protected void showLoadingDialog(){
		if(null != mLoadingDialog){
			mLoadingDialog.show();
		}
	}

	/** 取消加载对话框 **/
	protected void dismissLoadingDialog() {
		if(null != mLoadingDialog){
			mLoadingDialog.dismiss();
		}
	}

	/**
	 * 显示toast提示
	 * @param msg 提示
	 */
	protected void showAlert(String msg) {
		if(null != getApplicationContext()){
			AlertToast.show(getApplicationContext(), msg);
		}
	}
	
	/**
	 * 检查网络状态
	 * @return true 网络正常
	 */
	/**
	 * 检查网络状态
	 * @return true 网络正常
	 */
	protected boolean checkNetWork(){
		if (NetworkUtils.isConnected()) {
			Log.d("网络可用");
			return true;
		}
		showAlert("网络不可用");
		Log.d("网络不可用");
		return false;
	}
	
	/**
	 * 此处可以更新ui
	 * @param event 成功或失败
	 * @param url 请求url
	 * @param data 请求得到的数据
	 * @param reqData 请求的数据
	 */
	protected void CallbackHandler(int event, String url, String data, Object reqData) {
		
	}

	/** 将请求结果放在handle处理，callback可以更新ui **/
	@SuppressLint("HandlerLeak")
	protected Handler mHttpHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			Bundle b = msg.getData();
			int event = b.getInt("event");
			String url = b.getString("url");
			String data = b.getString("data");
			Object reqData = msg.obj;
			if(!isPageAlive()){
				Log.e("The activity is not alive");
				return;
			}
			CallbackHandler(event, url, data, reqData);
		}
	};

	/**
	 * 发送消息
	 * @param event 请求结果EVENT_XXX
	 * @param url 请求url
	 * @param data 请求返回数据
	 * @param reqData 请求数据
	 */
	protected void sendMsg(int event, String url, String data, Object reqData){
		Message msg = mHttpHandler.obtainMessage();
		Bundle b = new Bundle();
		b.putInt("event", event);
		b.putString("url", url);
		b.putString("data", data);
		msg.setData(b);
		msg.obj = reqData;
		mHttpHandler.sendMessage(msg);
	}

	/** 请求成功 **/
	public static final int EVENT_SUCCESS = 0x1;
	/** 请求失败 **/
	public static final int EVENT_FAILED = 0x2;

	/**
	 * 是否请求成功
	 * @param event 请求结果
	 * @return true 成功
	 */
	protected boolean isHttpRequestSuccess(int event){
		return EVENT_SUCCESS == event;
	}

	/**
	 * 发起post请求
	 * @param url 请求rul
	 */
	protected void doPost(final String url){
		doPost(url, null);
	}

	/**
	 * 发起post请求
	 * @param url 请求rul
	 * @param params 请求参数
	 */
	protected void doPost(final String url, HttpParams params){
		doPost(url, params, null);
	}

	/**
	 * 发起post请求
	 * @param url 请求rul
	 * @param params 请求参数
	 * @param reqData 请求数据
	 */
	protected void doPost(final String url, HttpParams params, final Object reqData){
		MyApplication.getInstance().getHttpRequest().doPost(url, params, new Callback() {

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				sendMsg(EVENT_FAILED, url, arg1.toString(), reqData);
			}

			@Override
			public void onResponse(Call arg0, Response arg1)
					throws IOException {
				sendMsg(EVENT_SUCCESS, url, arg1.body().string(), reqData);
			}
		});
	}

	/**
	 * 发起get请求
	 * @param url 请求rul
	 */
	protected void doGet(final String url){
		doGet(url, null);
	}

	/**
	 * 发起get请求
	 * @param url 请求rul
	 * @param params 请求参数
	 */
	protected void doGet(final String url, HttpParams params){
		doGet(url, params, null);
	}

	/**
	 * 发起get请求
	 * @param url 请求rul
	 * @param params 请求参数
	 * @param reqData 请求数据
	 */
	protected void doGet(final String url, HttpParams params, final Object reqData){
		MyApplication.getInstance().getHttpRequest().doPost(url, params, new Callback() {

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				sendMsg(EVENT_FAILED, url, arg1.toString(), reqData);
			}

			@Override
			public void onResponse(Call arg0, Response arg1)
					throws IOException {
				sendMsg(EVENT_SUCCESS, url, arg1.body().string(), reqData);
			}
		});
	}
}
