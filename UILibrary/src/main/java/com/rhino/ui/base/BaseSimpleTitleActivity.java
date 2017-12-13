package com.rhino.ui.base;

import com.rhino.u.R;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

/**
 * Created by wb
 * on 2017/11/21 17:37
 *
 */
public abstract class BaseSimpleTitleActivity extends BaseActivity {
	
	/**
	 * the title view
	 */
	private View titleView;
	/**
	 * the left back key container of title
	 */
	private View titleBackContainer;
	/**
	 * the left back key of title
	 */
	@SuppressWarnings("unused")
	private ImageView titleBackKey;
	/**
	 * the title text
	 */
	private TextView titleText;
	/**
	 * the right key of title
	 */
	private LinearLayout titleRightKeyContainer;
	/**
	 * the content container
	 */
	private LinearLayout contentContainer;
	/**
	 * the resources
	 */
	protected Resources mResources;
	/**
	 * the color of theme and theme light
	 */
	protected int themeColor, themeColorLight;
	/**
	 * the title height init in dimen.xml
	 */
	protected int titleHeight;
	/**
	 * the title icon height init in demen.xml
	 */
	protected int titleIconHeight;
	
	
	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(R.layout.layout_page_base_activity);
		initResources();
		initBaseView(layoutResID);
	}
	
	/**
	 * init resources
	 */
	private void initResources() {
		mResources = getResources();
		themeColor = mResources.getColor(R.color.HotPink);
		themeColorLight = mResources.getColor(R.color.Violet);
		titleHeight = (int) mResources.getDimension(R.dimen.custom_title_height);
		titleIconHeight = (int) mResources.getDimension(R.dimen.custom_title_icon_size);
	}
	
	/**
	 * init the base view
	 * @param layoutResID the content layout id
	 */
	private void initBaseView(int layoutResID){
		View contentView = getLayoutById(layoutResID);
		contentContainer = findSubViewById(R.id.base_container);
        titleView = findSubViewById(R.id.base_title);
		titleView.setBackgroundColor(themeColor);
		titleBackContainer = findSubViewById(R.id.simple_title_left_container, titleView);
		titleBackKey = findSubViewById(R.id.simple_title_left_icon, titleView);
		titleText = findSubViewById(R.id.simple_title_center_txt, titleView);
		titleRightKeyContainer = findSubViewById(R.id.simple_title_right_container, titleView);
		
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		contentContainer.addView(contentView, lp);
		
		titleBackContainer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onBackClick();
			}
		});
	}
	
	/**
	 * the back click
	 */
	protected void onBackClick(){
		finish();
	}
	
	/**
	 * set the title back key visible
	 * 
	 * @param visible
	 *            true：visible false：invisible
	 */
	protected void setTitleBackKeyVisible(boolean visible) {
		int visibility = visible ? View.VISIBLE : View.GONE;
		titleBackContainer.setVisibility(visibility);
	}

	/**
	 * set the title text
	 * 
	 * @param title
	 */
	protected void setTitle(String title) {
		titleText.setText(title);
	}

	/**
	 * add the right title button
	 * 
	 * @param text
	 *            the text
	 * @param colorId
	 *            the color id
	 * @param listener
	 *            the click listener
	 */
	public void addTitleBtn(String text, @ColorRes int colorId,
			OnClickListener listener) {
		TextView tv = new TextView(getApplicationContext());
		tv.setText(text);
		tv.setTextColor(mResources.getColor(colorId));
		tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				mResources.getDimension(R.dimen.custom_title_txt_key_size));
		LayoutParams lp = new LayoutParams(titleHeight, titleHeight);
		tv.setLayoutParams(lp);
		tv.setGravity(Gravity.CENTER);
		tv.setOnClickListener(listener);
		titleRightKeyContainer.addView(tv);
	}

	/**
	 * add the right title button
	 * 
	 * @param text
	 *            the text
	 * @param listener
	 *            the click listener
	 */
	public void addTitleBtn(String text, OnClickListener listener) {
		addTitleBtn(text, R.color.white, listener);
	}

	/**
	 * add the right title button
	 * 
	 * @param resId
	 *            the image id
	 * @param colorId
	 *            the color id
	 * @param listener
	 *            the click listener
	 */
	public void addTitleBtn(@DrawableRes int resId, @ColorRes int colorId,
			OnClickListener listener) {
		LinearLayout ll = new LinearLayout(getApplicationContext());
		LinearLayout.LayoutParams llLp = new LinearLayout.LayoutParams(titleHeight, titleHeight);
		ll.setLayoutParams(llLp);
		ll.setGravity(Gravity.CENTER);
		
		ImageView iv = new ImageView(getApplicationContext());
		LayoutParams ivLp = new LayoutParams(titleIconHeight, titleIconHeight);
		iv.setLayoutParams(ivLp);
		iv.setScaleType(ImageView.ScaleType.FIT_XY);
		iv.setImageResource(resId);
		iv.setColorFilter(mResources.getColor(colorId));
		iv.setOnClickListener(listener);
		ll.addView(iv);
		titleRightKeyContainer.addView(ll);
	}

	/**
	 * add the right title button
	 * 
	 * @param resId
	 *            the image id
	 * @param listener
	 *            the click listener
	 */
	public void addTitleBtn(@DrawableRes int resId, OnClickListener listener) {
		addTitleBtn(resId, R.color.white, listener);
	}

	/** clear the right title button **/
	public void clearTitleBtn() {
		titleRightKeyContainer.removeAllViews();
	}

	/**
	 * set the title background image
	 * 
	 * @param resid
	 *            the image id
	 */
	public void setTitleBackgroundResource(@DrawableRes int resid) {
		titleView.setBackgroundResource(resid);
	}

	/**
	 * set the title background color
	 * 
	 * @param color
	 *            the color
	 */
	public void setTitleBackgroundColor(int color) {
		titleView.setBackgroundColor(color);
	}

	/**
	 * set the title visible
	 * 
	 * @param visible
	 *            true：visible false：invisible
	 */
	public void setTitleVisible(boolean visible) {
		setViewVisible(titleView, visible);
	}

	/**
	 * set the view visible
	 * 
	 * @param v
	 *            view the view
	 * @param visible
	 *            true：visible false：invisible
	 */
	public void setViewVisible(View v, boolean visible) {
		if (visible) {
			v.setVisibility(View.VISIBLE);
		} else {
			v.setVisibility(View.GONE);
		}
	}

	/**
	 * get the container height
	 * @return height
	 */
	final protected int getContainerHeight(){
		return contentContainer.getMeasuredHeight();
	}
	
	/**
	 * get the Resources
	 * @return Resources
	 */
	final protected Resources getBaseResources(){
		if(null == mResources){
			mResources = getResources();
		}
		return mResources;
	}

	/**
	 * get the view by layout resource id
	 * 
	 * @param layoutId the id of layout
	 * @return view
	 */
	final protected View getLayoutById(@LayoutRes int layoutId) {
		return getLayoutInflater().inflate(layoutId, null, false);
	}
	
	/**
	 * get the color by res id
	 * @param colorId the res id of color
	 * @return the color
	 */
	final protected int getColorById(@ColorRes int colorId){
		return getBaseResources().getColor(colorId);
	}
	
	/**
	 * get the drawable by res id
	 * @param drawableId the res id of drawable
	 * @return the drawable
	 */
	final protected Drawable getDrawableById(@DrawableRes int drawableId){
		return getBaseResources().getDrawable(drawableId);
	}
	
	/**
	 * get the dimension by res id
	 * @param dimenId the res id of dimension
	 * @return the dimension
	 */
	final protected float getDimen(@DimenRes int dimenId){
		return getBaseResources().getDimension(dimenId);
	}
	
	/**
	 * get the dimension by res id
	 * @param dimenId the res id of dimension
	 * @return the dimension
	 */
	final protected float getDimensionPixelOffset(@DimenRes int dimenId){
		return getBaseResources().getDimensionPixelOffset(dimenId);
	}
	
	/**
	 * get the dimension by res id
	 * @param dimenId the res id of dimension
	 * @return the dimension
	 */
	final protected float getDimensionPixelSize(@DimenRes int dimenId){
		return getBaseResources().getDimensionPixelSize(dimenId);
	}
}
