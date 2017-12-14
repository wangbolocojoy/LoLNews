package com.shop.newshop_application.base;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.blankj.utilcode.util.NetworkUtils;
import com.lzy.okgo.model.Response;
import com.rhino.ui.base.BaseSimpleTitleFragment;
import com.rhino.ui.utils.AlertToast;
import com.rhino.ui.widget.LoadingDialog;
import com.rhino.ui.widget.SunLodingDialog;
import com.shop.newshop_application.utils.UiUtils;
import java.io.File;

import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Headers;


/**
 * Create by LuoLin on 2016年12月6日
 */
public abstract class BaseHttpFragment extends BaseSimpleTitleFragment {

	/** 加载对话框 **/
	protected LoadingDialog mLoadingDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mLoadingDialog = new LoadingDialog(getActivity());
		mLoadingDialog.setCanceledOnTouchOutside(false);
		mLoadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialogInterface) {
				onLoadingDialogCancel(dialogInterface);
			}
		});
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mLoadingDialog = null;

	}

	/**
	 * 页面跳转
	 * @param cls activity
	 */
	protected void gotoActivity(Class<?> cls){
		UiUtils.showPage(getActivity(), cls, null);
	}

	/**
	 * 页面跳转
	 * @param cls activity
	 * @param extras bundle
	 */
	protected void gotoActivity(Class<?> cls, Bundle extras){
		UiUtils.showPage(getActivity(), cls, extras);
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
		if(null != getActivity()){
			AlertToast.show(getActivity(), msg);
		}
	}
	
	/**
	 * 检查网络状态
	 * @return true 网络正常
	 */
	protected boolean checkNetWork(){
		if (NetworkUtils.isConnected()&&NetworkUtils.isAvailableByPing()) {
			showAlert("网络不可用");
			return false;
		}
		return true;
	}
	



	protected <T> void handleResponse(T data) {
		Response<T> response = new Response<>();
		response.setBody(data);
		handleResponse(response);
	}

	protected <T> void handleResponse(Response<T> response) {
		StringBuilder sb;
		Call call = response.getRawCall();
		if (call != null) {

			Headers requestHeadersString = call.request().headers();
			Set<String> requestNames = requestHeadersString.names();
			sb = new StringBuilder();
			for (String name : requestNames) {
				sb.append(name).append(" ： ").append(requestHeadersString.get(name)).append("\n");
			}

		} else {

		}
		T body = response.body();
		if (body == null) {

		} else {
			if (body instanceof String) {

			} else if (body instanceof List) {
				sb = new StringBuilder();
				List list = (List) body;
				for (Object obj : list) {
					sb.append(obj.toString()).append("\n");
				}

			} else if (body instanceof Set) {
				sb = new StringBuilder();
				Set set = (Set) body;
				for (Object obj : set) {
					sb.append(obj.toString()).append("\n");
				}

			} else if (body instanceof Map) {
				sb = new StringBuilder();
				Map map = (Map) body;
				Set keySet = map.keySet();
				for (Object key : keySet) {
					sb.append(key.toString()).append(" ： ").append(map.get(key)).append("\n");
				}

			} else if (body instanceof File) {
				File file = (File) body;

			} else if (body instanceof Bitmap) {

			} else {

			}
		}

		okhttp3.Response rawResponse = response.getRawResponse();
		if (rawResponse != null) {
			Headers responseHeadersString = rawResponse.headers();
			Set<String> names = responseHeadersString.names();
			sb = new StringBuilder();
			sb.append("url ： ").append(rawResponse.request().url()).append("\n\n");
			sb.append("stateCode ： ").append(rawResponse.code()).append("\n");
			for (String name : names) {
				sb.append(name).append(" ： ").append(responseHeadersString.get(name)).append("\n");
			}

		} else {

		}
	}

	protected <T> void handleError() {
		Response<T> response = new Response<>();
		handleResponse(response);
	}

	protected <T> void handleError(Response<T> response) {
		if (response == null){
			return;
		}
		if (response.getException() != null) {
			response.getException().printStackTrace();
		}
		StringBuilder sb;
		Call call = response.getRawCall();
		if (call != null) {

			Headers requestHeadersString = call.request().headers();
			Set<String> requestNames = requestHeadersString.names();
			sb = new StringBuilder();
			for (String name : requestNames) {
				sb.append(name).append(" ： ").append(requestHeadersString.get(name)).append("\n");
			}

		} else {

		}


		okhttp3.Response rawResponse = response.getRawResponse();
		if (rawResponse != null) {
			Headers responseHeadersString = rawResponse.headers();
			Set<String> names = responseHeadersString.names();
			sb = new StringBuilder();
			sb.append("stateCode ： ").append(rawResponse.code()).append("\n");
			for (String name : names) {
				sb.append(name).append(" ： ").append(responseHeadersString.get(name)).append("\n");
			}

		} else {

		}
	}
}
