package com.rhino.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wb
 * on 2017/11/21 17:37
 *
 */
public abstract class BaseFragment extends Fragment {

    /**
     * the parent view
     */
    protected View mParentView;
    /**
     * the parent view layout id
     */
    protected int mParentLayoutId;
    /**
     * the bundle data
     */
    protected Bundle mExtra;
    /**
     * the OnClickListener
     */
    private View.OnClickListener mBaseOnClickListener;
    /**
     * the OnLongClickListener
     */
    private View.OnLongClickListener mBaseOnLongClickListener;
    /**
     * whether the first refresh
     */
    private boolean isFirstRefresh = true;
    /**
     * whether init view.
     */
    private boolean isInitEnable = false;


    /**
     * set the parent view
     * {@link #setContentView(int)}
     * {@link #setContentView(View)}}
     */
    protected abstract void setContent();

    /**
     * init the data
     * @return true success, false failed
     */
    protected abstract boolean initData();

    /**
     * init the view
     */
    protected abstract void initView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initExtraData();
        if(!initData()){
            isInitEnable = false;
            finish();
        }else{
            isInitEnable = true;
            setContent();
        }
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mParentLayoutId != 0) {
            mParentView = inflater.inflate(mParentLayoutId, container, false);
        }
        return mParentView;
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);
        if(isInitEnable()){
            initView();
        }
    }

    @Override
	public void onResume() {
		super.onResume();
		if(isPageActive()){
			onUserVisible(isFirstRefresh);
            isFirstRefresh = false;
		}
	}

    @Override
    public void onHiddenChanged(boolean hidden) {
    	super.onHiddenChanged(hidden);
    	if(isPageActive()){
    		onUserVisible(isFirstRefresh);
    	}
    }
    
    /**
     * init or refresh the ui show，will be called on onResume and onHiddenChanged(true)
     */
    protected void onUserVisible(boolean isFirst){
    	
    }

    /**
     * whether init view.
     */
    protected boolean isInitEnable() {
        return isInitEnable;
    }

    /**
     * whether the fragment is alive
     * @return true fragment is alive
     * @see #getActivity()
     * @see #getView()
     */
    protected boolean isPageAlive(){
        return isInitEnable() && getActivity() != null && getView() != null;
    }
    
    /**
     * whether the fragment is active
     * @return true fragment is active
     * @see #isHidden()
     * @see #isResumed()
     * @see #getView()
     */
    protected boolean isPageActive() {
        return isPageAlive() && !isHidden() && isResumed();

    }

    /**
     * find the view by view id
     * @param id view id
     * @return the view
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findSubViewById(@IdRes int id) {
        return (T) mParentView.findViewById(id);
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
        mExtra = getArguments();
        if (mExtra == null) {
            mExtra = new Bundle();
        }
    }

    /**
     * set the parent view
     * @param layoutId the layout id
     */
    protected void setContentView(@LayoutRes int layoutId) {
        mParentLayoutId = layoutId;
    }

    /**
     * set the parent view
     * @param contentView the view
     */
    protected void setContentView(@NonNull View contentView) {
        mParentView = contentView;
    }

    /**
     * call this when your activity is done and should be closed.
     */
    final protected void finish() {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * Register a callback to be invoked when this view is clicked. If this view is not
     * clickable, it becomes clickable.
     * @param views the view
     */
    final protected void setBaseOnClickListener(View... views) {
        if (views != null) {
            for (View v : views) {
                if(null != v){
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
    final protected void setBaseOnLongClickListener(View... views) {
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
    final protected View.OnClickListener getBaseOnClickListener() {
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
    final protected View.OnLongClickListener getBaseOnLongClickListener() {
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
