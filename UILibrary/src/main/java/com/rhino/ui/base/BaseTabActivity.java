package com.rhino.ui.base;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhino.u.R;
import com.rhino.ui.widget.CustomTabHost;
import com.rhino.ui.widget.CustomTabHost.onTabsChangedListener;


/**
 * Created by wb
 * on 2017/11/21 17:40
 *
 */
public abstract class BaseTabActivity extends BaseActivity implements
		onTabsChangedListener {

	/**
	 * the custom tabHost
	 */
	private  CustomTabHost tabHost;
	/**
	 * the line closed the tab
	 */
	private View tabLine;
	/**
	 * the array of tabs, it will be added when add tab.
	 */
	private ArrayList<TabHolder> tabHolders;
	/**
	 * the color of unselect, the default color is Color.DKGRAY, it can be
	 * changed in derived class by setNormalColor().
	 **/
	private int normalColor;
	/**
	 * the color of select, the default color is Color.BLUE, it can be changed
	 * in derived class by setSelectColor().
	 **/
	private int selectColor;
	/**
	 * the tab's index what selected, the default index is 0, it can be changed
	 * in derived class by setCurrentTab().
	 **/
	private  int currentTab = -1;
	/**
	 * the fragmentManager
	 */
	private FragmentManager fragmentManager;
	/**
	 * the tab icon id，NORMAL is the icon id when not be selected, SELECT is the
	 * icon id when selected.
	 */
	private enum ICON_ID {
		NORMAL, SELECT
	};
	/**
	 * the resources
	 */
	protected Resources mResources;
	/**
	 * the color of theme and theme light
	 */
	protected int themeColor, themeColorLight;
	
	
	/**
	 * init the tabs, by addTab(String tabId, int[] icons, Fragment fragment)
	 */
	protected abstract void initTabs();
	
	
	@Override
	protected void setContent() {
		setContentView(R.layout.layout_page_base_tab_activity);
	}

	@Override
	protected boolean initData() {
		initResources();
		return true;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	protected void initView() {
		tabLine = findSubViewById(R.id.tab_line);
		tabHost = findSubViewById(R.id.tab_host);

		tabHost.setup(getApplicationContext(), fragmentManager,
				R.id.tab_content);
		tabHost.setOnTabsChangedListener(this);
		initTabs();
		setCurrentTab(0);
	}
	
	@Override
	public void onTabsChanged(String tabId) {
		switchTab(tabId);
	}
	
	/**
	 * init the resources
	 */
	private void initResources() {
		mResources = getResources();
		themeColor = mResources.getColor(R.color.HotPink);
		themeColorLight = mResources.getColor(R.color.Violet);

		normalColor = mResources.getColor(R.color.black_40);
		selectColor = themeColor;

		tabHolders = new ArrayList<TabHolder>();

		fragmentManager = getSupportFragmentManager();
	}
	
	/**
	 * switch the tab by color or icon
	 * 
	 * @param tabId
	 *            the String of tabId
	 */
	private void switchTab(String tabId) {
		for (TabHolder holder : tabHolders) {
			if (holder.tabId.getText().equals(tabId)) {
				if (1 < holder.icons.length) {
					holder.icon.setImageResource(holder.icons[ICON_ID.SELECT
							.ordinal()]);
				} else {
					holder.icon.setColorFilter(selectColor);
				}
				holder.tabId.setTextColor(selectColor);
			} else {
				if (1 < holder.icons.length) {
					holder.icon.setImageResource(holder.icons[ICON_ID.NORMAL
							.ordinal()]);
				} else {
					holder.icon.setColorFilter(normalColor);
				}
				holder.tabId.setTextColor(normalColor);
			}
		}
	}

	/**
	 * add tab, called in derived class.
	 * 
	 * @param tabId
	 *            the string of tabId
	 * @param icons
	 *            the tab icon's array. if the change by color, the length will
	 *            be one; but by selected icon and normal icon, the length will
	 *            be two.
	 * @param fragment
	 *            the content fragment of tab
	 */
	public void addTab(String tabId, @DrawableRes int[] icons, Fragment fragment) {
		TabHolder holder = createTabView(tabId, icons);
		tabHost.addTab(tabId, holder.view, fragment);
		tabHolders.add(holder);
	}
	
	/**
	 * clear the tab select
	 */
	public void clearTabSelect() {
		switchTab("");
	}
	
	/**
	 * set the new flag
	 * @param index the tab index
	 * @param num the flag num，will not show num when num is zero
	 * @param show true show
	 */
	public void setNewFlag(int index, int num, boolean show){
		int visible = show ? View.VISIBLE : View.INVISIBLE;
		if(index > 0 && index < tabHolders.size()){
			tabHolders.get(index).newFlag.setVisibility(visible);
			if(0 >= num || 99 < num){
				tabHolders.get(index).newFlag.setText("");
			} else {
				tabHolders.get(index).newFlag.setText(String.valueOf(num));
			}
		}
	}

	/**
	 * init the current tab index, called in derived class.
	 * 
	 * @param id
	 *            the current tab index
	 */
	public  void setCurrentTab(int id) {
		currentTab = id;
		tabHost.setCurrentTab(currentTab);
	}

	/**
	 * get the current select tab tag
	 * @return
	 * 			the current tab tag
	 */
	public String getCurrentSelectTag(){
		if(null == tabHolders){
			return null;
		}

		int currentTab = tabHost.getCurrentTab();
		if(0 <= currentTab && currentTab < tabHolders.size()
				&& null != tabHolders.get(currentTab)){
			return tabHolders.get(currentTab).tabId.getText().toString();
		}
		return null;
	}

	/**
	 * get the fragment
	 * @param tag the tag
	 * @return the fragment
	 */
	public Fragment getFragment(String tag){
		ArrayList<CustomTabHost.Tabs> tabs = tabHost.getTabs();
		if(null != tabs){
			for(CustomTabHost.Tabs t : tabs){
				if(null != t && null != t.tabId && t.tabId.equals(tag)){
					return t.fragment;
				}
			}
		}
		return null;
	}

	/**
	 * init the normalColor, called in derived class.
	 * 
	 * @param normalColor
	 *            the color when not be selected.
	 */
	public void setNormalColor(int normalColor) {
		this.normalColor = normalColor;
	}

	/**
	 * init the selectColor, called in derived class.
	 * 
	 * @param selectColor
	 *            the color when selected.
	 */
	public void setSelectColor(int selectColor) {
		this.selectColor = selectColor;
	}

	/**
	 * set the line's visibility what closed the tab
	 * 
	 * @param show
	 */
	public void setTabLineVisibale(boolean show) {
		int visible = show ? View.VISIBLE : View.INVISIBLE;
		tabLine.setVisibility(visible);
	}

	/**
	 * set the line's color what closed the tab
	 * 
	 * @param color
	 *            the tab line color
	 */
	public void setTabLineColor(int color) {
		tabLine.setBackgroundColor(color);
	}

	/**
	 * set the tab's color
	 * 
	 * @param color
	 *            the tab color
	 */
	public void setTabBackgroundColor(int color) {
		tabHost.setBackgroundColor(color);
	}
	
	/**
	 * create the tab holder
	 * 
	 * @param tabId
	 *            the string of tabId
	 * @param icons
	 *            the tab icon's array. if the change by color, the length will
	 *            be one; but by selected icon and normal icon, the length will
	 *            be two.
	 * @return the tab holder
	 */
	private TabHolder createTabView(String tabId, int[] icons) {
		TabHolder holder = new TabHolder();
		holder.tabId.setText(tabId);
		holder.icon.setImageResource(icons[ICON_ID.NORMAL.ordinal()]);
		holder.icons = icons;
		return holder;
	}

	public class TabHolder {

		/** the tab view **/
		public View view;
		/** the description of tab **/
		public TextView tabId;
		/** the tab icon **/
		public ImageView icon;
		/**
		 * the tab icon's array. if the change by color, the length will be one;
		 * but by selected icon and normal icon, the length will be two.
		 */
		public int[] icons;
		/**
		 * the new flag
		 */
		public TextView newFlag;

		public TabHolder() {
			view = LayoutInflater.from(BaseTabActivity.this).inflate(
					R.layout.include_tab_item, null);
			tabId = findSubViewById(R.id.tab_item_text, view);
			icon = findSubViewById(R.id.tab_item_icon, view);
			newFlag = findSubViewById(R.id.tab_item_new_flag, view);
		}
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
