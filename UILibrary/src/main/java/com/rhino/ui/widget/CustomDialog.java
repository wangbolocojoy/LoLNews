package com.rhino.ui.widget;

import com.rhino.u.R;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by wb
 * on 2017/11/21 
 * on CustomDialog 
 */
public class CustomDialog extends Dialog {

	/**
	 * the activity
	 */
	private Activity mActivity;
	/**
	 * the root view
	 */
	private View mRootView;
	/**
	 * the TextView of title
	 */
	private TextView mTvTitle;
	/**
	 * the message container
	 */
    private LinearLayout mLlMsgContainer;
    /**
     * the TextView of message
     */
    private TextView mTvMsgContent;
    /**
	 * the edit container
	 */
    private LinearLayout mLlEditContainer;
    /**
     * the EditText of content
     */
    private EditText mEdContent;
    /**
     * the line bottom content
     */
    private View mViewEdBottomLine;
    /**
     * the line above key
     */
    private View mViewKeyTopLine;
    /**
     * the TextView of positive key
     */
    private TextView mTvPositiveKey;
    /**
     * the TextView of negative key
     */
    private TextView mTvNegativeKey;
    /**
     * the line center key
     */
    private View mViewKeyCenterLine;
    /**
     * the positive key listener
     */
    private OnKeyClickListener mPositiveListener;
    /**
     * the negative key listener
     */
    private OnKeyClickListener mNegativeListener;
    
    
    public CustomDialog(Activity context) {
		this(context, R.style.CustomDialog);
	}

	public CustomDialog(Activity activity, int theme) {
		super(activity, theme);
		mActivity = activity;
        LayoutInflater inflater = LayoutInflater.from(activity);
        mRootView = inflater.inflate(R.layout.widget_custom_dialog, null);
        setContentView(mRootView);
        initView();
	}
	
	@Override
	public void dismiss() {
		if(null != mActivity && !mActivity.isFinishing()
				&& isShowing()){
			super.dismiss();
			if(null != mEdContent){
				mEdContent.setText("");
			}
		}
	}
	
	@Override
	public void show() {
		if(null != mActivity && !mActivity.isFinishing() 
				&& !isShowing()){
            prepareBeforeShow();
			super.show();
		}
	}
	
	/**
	 * create the message dialog
	 * @param activity the activity
	 * @return the message dialog
	 */
	public static CustomDialog MsgDialog(Activity activity){
		CustomDialog dialog = new CustomDialog(activity);
		dialog.mLlMsgContainer.setVisibility(View.VISIBLE);
		dialog.mLlEditContainer.setVisibility(View.GONE);
		return dialog;
	}
	
	/**
	 * create the edit dialog
	 * @param activity the activity
	 * @return the edit dialog
	 */
	public static CustomDialog EditDialog(Activity activity){
		CustomDialog dialog = new CustomDialog(activity);
		dialog.mLlMsgContainer.setVisibility(View.GONE);
		dialog.mLlEditContainer.setVisibility(View.VISIBLE);
		return dialog;
	}

	/**
	 * set the title text
	 * @param title the title text
	 * @return dialog
	 */
    public CustomDialog setTitle(@NonNull String title) {
        mTvTitle.setText(title);
        return this;
    }

    /**
	 * set the message text
	 * @param msg the message text
	 * @return dialog
	 */
    public CustomDialog setMessage(@NonNull String msg) {
    	mTvMsgContent.setText(msg);
        return this;
    }
    
    /**
     * get the edit text
     * @return the edit text
     */
    public String getEditText(){
    	return mEdContent.getText().toString();
    }
    
    /**
     * set the positive key click listener
     * @param text the positive text
     * @param listener the listener
     * @return dialog
     */
    public CustomDialog setPositiveClick(@NonNull String text, @Nullable OnKeyClickListener listener) {
        this.mPositiveListener = listener;
        mTvPositiveKey.setText(text);
        mTvPositiveKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPositiveListener != null) {
                	mPositiveListener.onClick(CustomDialog.this);
                } else {
                    dismiss();
                }
            }
        });
        return this;
    }
    
    /**
     * set the negative key click listener
     * @param text the negative text
     * @param listener the listener
     * @return dialog
     */
    public CustomDialog setNegativeClick(@NonNull String text, @Nullable OnKeyClickListener listener) {
        this.mNegativeListener = listener;
        mTvNegativeKey.setText(text);
        mTvNegativeKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNegativeListener != null) {
                	mNegativeListener.onClick(CustomDialog.this);
                } else {
                    dismiss();
                }
            }
        });
        return this;
    }

    /**
     * init the view
     */
    private void initView() {
        mTvTitle = findSubViewById(R.id.custom_dialog_title);
        mLlMsgContainer = findSubViewById(R.id.custom_dialog_msg_ll);
        mTvMsgContent = findSubViewById(R.id.custom_dialog_msg);
        
        mLlEditContainer = findSubViewById(R.id.custom_dialog_edit_ll);
        mEdContent = findSubViewById(R.id.custom_dialog_edit_text);
        mViewEdBottomLine = findSubViewById(R.id.custom_dialog_edit_bottom_line);
        
        mViewKeyTopLine = findSubViewById(R.id.custom_dialog_key_top_line);
        mTvPositiveKey = findSubViewById(R.id.custom_dialog_key_ok);
        mTvNegativeKey = findSubViewById(R.id.custom_dialog_key_cancel);
        mViewKeyCenterLine = findSubViewById(R.id.custom_dialog_key_center_line);
    }

    /**
     * prepare before show
     */
    private void prepareBeforeShow(){
        String positiveKey = mTvPositiveKey.getText().toString();
        String negativeKey = mTvNegativeKey.getText().toString();
        if(TextUtils.isEmpty(positiveKey) || TextUtils.isEmpty(negativeKey)){
            mViewKeyCenterLine.setVisibility(View.GONE);
        } else {
            mViewKeyCenterLine.setVisibility(View.VISIBLE);
        }

        int visibility = TextUtils.isEmpty(positiveKey) || TextUtils.isEmpty(negativeKey) ? View.GONE : View.VISIBLE;
        mViewKeyCenterLine.setVisibility(visibility);

        visibility = TextUtils.isEmpty(positiveKey) ? View.GONE : View.VISIBLE;
        mTvPositiveKey.setVisibility(visibility);

        visibility = TextUtils.isEmpty(negativeKey) ? View.GONE : View.VISIBLE;
        mTvNegativeKey.setVisibility(visibility);
    }
    
    /**
     * find the view by view id
     * @param id view id
     * @return the view
     */
    @SuppressWarnings("all")
    private <T extends View> T findSubViewById(int id) {
        return (T)mRootView.findViewById(id);
    }

    public interface OnKeyClickListener {
        void onClick(CustomDialog dialog);
    }
    
}
