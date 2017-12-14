package com.shop.newshop_application.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.NetworkUtils;
import com.rhino.ui.base.BaseSimpleTitleFragment;
import com.rhino.ui.utils.AlertToast;
import com.rhino.ui.widget.LoadingDialog;
import com.shop.newshop_application.utils.UiUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Unir|Superman
 * on 2017/12/14 14:13.
 * on Administrator
 * on NewShop_Application
 */

public abstract class LazyLoadFragment extends BaseSimpleTitleFragment{
    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;
    Unbinder unbinder;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        isInit = true;
        /**初始化的时候去加载数据**/
        isCanLoadData();
        return rootView;
    }
    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }

        if (getUserVisibleHint()) {
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }
    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    public abstract void lazyLoad();
    /**
     * 联网获取数据
     */
    public abstract void getInfo();
    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {
    }
    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isCanLoadData();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        isInit = false;
        isLoad = false;
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




}
