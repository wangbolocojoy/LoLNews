package com.rhino.ui.utils;

import android.app.Activity;
import android.os.Bundle;

import java.util.Stack;

/**
 * Created by wb
 * on 2017/11/21
 * on ActivityManager
 */
public class ActivityManager {

	private Stack<ActivityData> activityStack;
	
	private static ActivityManager instance;
	public static ActivityManager getInstance() {
		if (instance == null) {
			instance = new ActivityManager();
		}
		return instance;
	}

	private ActivityManager() {
		activityStack = new Stack<>();
	}

	/**
	 * add activity to stack
	 * @param activity the activity
	 * @param bundle the bundle data
	 */
	public void addActivity(Activity activity, Bundle bundle, long createTime) {
		activityStack.add(new ActivityData(activity, bundle, createTime));
	}

	/**
	 * exit app and finish all activity
	 */
	public void Exit() {
		if (activityStack.size() > 0) {
			finishAllActivity();
		}
	}

	/**
	 * finish all activity
	 */
	public void finishAllActivity() {
		if (activityStack.empty()) {
			return;
		}
		for (int i = 0; i < activityStack.size(); i++) {
			ActivityData data = activityStack.get(i);
			if (null != data) {
				data.finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * get current activity
	 * @return may be null
	 */
	public Activity getCurrentActivity() {
		if (activityStack.empty()) {
			return null;
		}

		Activity activity = activityStack.lastElement().activity;
		return activity;
	}

	/**
	 * finish current activity
	 * @param createTime the create time of activity
	 */
	public void finishActivity(long createTime) {
		if (activityStack.empty()) {
			return;
		}
		Activity activity = activityStack.lastElement().activity;
		finishActivity(activity, createTime);
	}
	
	/**
	 * finish activity
	 * @param cls the activity class
	 */
	public void finishActivity(Class<?> cls){
		if (activityStack.empty()) {
			return;
		}
		for (ActivityData data : activityStack) {
			if (data.activity.getClass().equals(cls)) {
				data.activity.finish();
				break;
			}
		}
	}

	/**
	 * finish the activity
	 * @param activity the activity
	 * @param createTime the create time of activity
	 */
	public boolean finishActivity(Activity activity, long createTime) {
		if (activity != null) {
			removeActivity(activity, createTime);
			activity.finish();
			activity = null;
			return true;
		}
		return false;
	}

	/**
	 * finish other activity except this
	 * @param activity this activity
	 * @param createTime the create time of activity
	 */
	public void finishOtherActivity(Activity activity, long createTime) {
		if (null == activity || activityStack.empty()) {
			return;
		}
		ActivityData bak = null;
		for (ActivityData data : activityStack) {
			if (!data.activity.getClass().equals(activity.getClass()) || createTime != data.createTime) {
				data.activity.finish();
			} else {
				bak = data;
			}
		}
		activityStack.clear();
		activityStack.add(bak);
	}

	/**
	 * remove the activity
	 * @param activity the activity
	 * @param createTime the create time of activity
	 */
	public void removeActivity(Activity activity, long createTime) {
		if (null == activity || activityStack.empty()) {
			return;
		}
		for (int i = 0; i < activityStack.size(); i++) {
			if (null != activityStack.get(i)
					&& null != activityStack.get(i).activity
					&& activityStack.get(i).activity.getClass().getName()
							.equals(activity.getClass().getName())
					&& createTime == activityStack.get(i).createTime) {
				activityStack.remove(i);
				break;
			}
		}
		activity = null;
	}

	/**
	 * find the activity in activityStack
	 * @param activity activity
	 * @return ActivityData
	 */
	public ActivityData findActivityData(Activity activity){
		if (activity == null || activityStack.empty()) {
			return null;
		}
		for (int i = 0; i < activityStack.size(); i++) {
			if (null != activityStack.get(i)
					&& null != activityStack.get(i).activity
					&& activityStack.get(i).activity.getClass().getName()
					.equals(activity.getClass().getName())) {
				return activityStack.get(i);
			}
		}
		return null;
	}

	public static class ActivityData {
		public long createTime;
		public Activity activity;
		public Bundle bundle;

		public ActivityData(Activity activity, Bundle bundle, long createTime) {
			this.activity = activity;
			this.bundle = bundle;
			this.createTime = createTime;
		}

		public boolean finish(){
			if(null != activity){
				activity.finish();
				return true;
			}
			return false;
		}
	}
}
