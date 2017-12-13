package com.rhino.ui.base;

import com.jaeger.library.StatusBarUtil;
import com.rhino.u.R;
import com.rhino.ui.utils.ActivityManager;
import com.rhino.ui.utils.SystemBarTintManager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;



/**
 * Created by wb
 * on 2017/11/21 17:37
 *
 */
public abstract class BaseActivity extends FragmentActivity{

    /**
     * whether the activity is alive
     */
    private boolean mIsPageAlive;
    /**
     * the bundle data
     */
    protected Bundle mExtras;
    /**
     * the create time of activity
     */
    protected long mCreateTime;
    /**
     * the OnClickListener
     */
    private View.OnClickListener mBaseOnClickListener;
    /**
     * the OnLongClickListener
     */
    private View.OnLongClickListener mBaseOnLongClickListener;

    /**
     * set the parent view
     * {@link #setContentView(int)}
     * {@link #setContentView(View)}}
     */
    protected abstract void setContent();

    /**
     * init the data
     * @return true success false failed
     */
    protected abstract boolean initData();

    /**
     * init the view
     */
    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initExtraData();
//        StatusBarUtil.setTransparent(this);
        initSystemBarTint();
        mCreateTime = System.currentTimeMillis();
        ActivityManager.getInstance().addActivity(this, mExtras, mCreateTime);
        if(!initData()){
            finish();
        }else{
            mIsPageAlive = true;
            setContent();
            initView();
        }
    }
    
    @Override
	protected void onDestroy() {
        mIsPageAlive = false;
        ActivityManager.getInstance().removeActivity(this, mCreateTime);
        super.onDestroy();
	}

    /**
     * Set the activity full screen.
     */
    protected void setFullScreen(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    /** 设置状态栏颜色 */
    protected void initSystemBarTint() {
        Window window = getWindow();
        if (translucentStatusBar()) {
            // 设置状态栏全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            return;
        }
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(setStatusBarColor());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0使用三方工具类，有些4.4的手机有问题，这里为演示方便，不使用沉浸式
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(setStatusBarColor());
        }
    }
    /** 子类可以重写决定是否使用透明状态栏 */
    protected boolean translucentStatusBar() {
        return false;
    }
    /** 子类可以重写改变状态栏颜色 */
    protected int setStatusBarColor() {
        return getColorPrimary();
    }
    /** 获取主题色 */
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }
    /** 获取深主题色 */
    public int getDarkColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }
        /**
         * whether the activity is alive
         * @return true activity is alive
         */
    protected boolean isPageAlive() {
        return mIsPageAlive;
    }

    /**
     * find the view by view id
     * @param id view id
     * @return the view
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findSubViewById(@IdRes int id) {
        return (T) findViewById(id);
    }
    
    /**
	 * find the view by id
	 * 
	 * @param id view id
	 * @param parent the parent view
	 * @return the view
	 */
	@SuppressWarnings("unchecked")
	protected <T extends View> T findSubViewById(int id, View parent) {
		return (T) parent.findViewById(id);
	}

    /**
     * Get the bundle data
     */
    protected void initExtraData() {
        mExtras = getIntent().getExtras();
        if (mExtras == null) {
            mExtras = new Bundle();
        }
    }

    /**
     * Register a callback to be invoked when this view is clicked. If this view is not
     * clickable, it becomes clickable.
     * @param views the view
     */
    protected void setBaseOnClickListener(View... views) {
        if (views != null) {
            for (View v : views) {
                if(null != v) {
                    v.setOnClickListener(getBaseOnClickListener());
                }
            }
        }
    }

    /**
     * Register a callback to be invoked when this view is clicked and held. If this view is not
     * long clickable, it becomes long clickable.
     * @param views the view
     */
    protected void setBaseOnLongClickListener(View... views) {
        if (views != null) {
            for (View v : views) {
                if(null != v) {
                    v.setOnLongClickListener(getBaseOnLongClickListener());
                }
            }
        }
    }

    /**
     * get the OnClickListener
     * @return the OnClickListener
     */
    protected View.OnClickListener getBaseOnClickListener() {
        if (mBaseOnClickListener == null) {
            mBaseOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseOnClickListener(v);
                }
            };
        }
        return mBaseOnClickListener;
    }

    /**
     * get the OnLongClickListener
     * @return the OnLongClickListener
     */
    protected View.OnLongClickListener getBaseOnLongClickListener() {
        if (mBaseOnLongClickListener == null) {
            mBaseOnLongClickListener = new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return baseOnLongClickListener(v);
                }
            };
        }
        return mBaseOnLongClickListener;
    }

    /**
     * deal the click listener
     * @param v the click view
     */
    protected void baseOnClickListener(View v) {
    }

    /**
     * deal the long click listener
     * @param v the long click view
     */
    protected boolean baseOnLongClickListener(View v) {
        return false;
    }
}
