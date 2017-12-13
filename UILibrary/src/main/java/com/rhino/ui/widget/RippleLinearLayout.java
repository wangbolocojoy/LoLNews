package com.rhino.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import com.rhino.u.R;

/**
 * <p>the custom click ripple effect</p>
 *
 * 
 * @attr ref clickColor
 * @attr ref radiusGap
 **/
/**
 * Created by wb
 * on 2017/11/21
 * on RippleLinearLayout
 */
public class RippleLinearLayout extends LinearLayout implements Runnable {

	/**
	 * the default ripple spread speed(dp)
	 */
	private static final int DF_CLICK_GRP = 8;
	/**
	 * the default invalidate duration
	 */
	private static final int DF_INVALIDATE_DURATION = 20;
	/**
	 * the paint
	 */
	private Paint mPaint;
	/**
	 * the click center x and y
	 */
	private float mCenterX, mCenterY;
	/**
	 * the view location
	 */
	private int[] mLocation = new int[2];
	/**
	 * the ripple radius
	 */
	private int mRadius;
	/**
	 * the ripple gap
	 */
	private int mRadiusGap;
	/**
	 * the ripple max radius
	 */
	private int mMaxRadius;
	/**
	 * the click view
	 */
	private View mTargetView;
	/**
	 * the click view height and width
	 */
	private int mTargetHeight, mTargetWidth;
	/**
	 * the up event dispatch
	 */
	private DispatchUpEventRunnable mDispatchUpEventRunnable;
	/**
	 * whether pressed
	 */
	private boolean mIsPressed;
	/**
	 * whether show animation
	 */
	private boolean mShouldDoAnimation;
	/**
	 * the click gap
	 */
	private int clickGap;
	/**
	 * the invalidate duration
	 */
	private int delayTime;
	

	public RippleLinearLayout(Context context) {
		super(context);
		init(context, null);
	}

	public RippleLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public RippleLinearLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		this.getLocationOnScreen(mLocation);
	}

	@Override
	public void run() {
		super.performClick();
	}

	@Override
	public boolean performClick() {
		postDelayed(this, 40);
		return true;
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);

		if (mTargetView == null || !mShouldDoAnimation || mTargetWidth <= 0) {
			return;
		}

		mRadius += mRadiusGap;
		drawClickRipple(canvas);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int x = (int) event.getRawX();
		int y = (int) event.getRawY();
		int action = event.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			View targetView = getTargetView(this, x, y);

			if (targetView != null && targetView.isEnabled()) {
				mIsPressed = true;
				mShouldDoAnimation = true;
				mTargetView = targetView;
				initTargetParam(event, targetView);
				postInvalidateDelayed(delayTime);
			}
			break;
		case MotionEvent.ACTION_UP:
			mIsPressed = false;
			postInvalidateDelayed(delayTime);
			mDispatchUpEventRunnable.event = event;
			postDelayed(mDispatchUpEventRunnable, delayTime);

			mRadiusGap = clickGap * 2;
			break;
		case MotionEvent.ACTION_CANCEL:
			mIsPressed = false;
			postInvalidateDelayed(delayTime);
			mRadiusGap = clickGap * 2;
			break;
		}
		return super.dispatchTouchEvent(event);
	}

	/**
	 * init resources
	 * 
	 * @param context
	 *            the context
	 * @param attrs
	 *            the attributeSet
	 */
	public void init(Context context, AttributeSet attrs) {
		TypedArray typedArray = getContext().obtainStyledAttributes(attrs,
				R.styleable.RippleLayout);
		int clickColor = typedArray.getColor(
				R.styleable.RippleLayout_clickColor, 0x33000000);
		delayTime = typedArray.getInteger(R.styleable.RippleLayout_radiusGap,
				DF_INVALIDATE_DURATION);
		clickGap = (int) (context.getResources().getDisplayMetrics().density * DF_CLICK_GRP);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(clickColor);
		mDispatchUpEventRunnable = new DispatchUpEventRunnable();
		setWillNotDraw(false);
	}

	/**
	 * draw the ripple
	 *
	 * @param canvas
	 *            the canvas
	 */
	private void drawClickRipple(Canvas canvas) {

		int[] location = new int[2];
		this.getLocationOnScreen(mLocation);
		mTargetView.getLocationOnScreen(location);

		int top = location[1] - mLocation[1];
		int left = location[0] - mLocation[0];
		int right = left + mTargetView.getMeasuredWidth();
		int bottom = top + mTargetView.getMeasuredHeight();

		canvas.save();
		canvas.clipRect(left, top, right, bottom);
		canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
		canvas.restore();

		if (mRadius <= mMaxRadius)
			postInvalidateDelayed(delayTime, left, top, right, bottom);
		else if (!mIsPressed) {
			mShouldDoAnimation = false;
			postInvalidateDelayed(delayTime, left, top, right, bottom);
		}
	}

	/**
	 * get the click view
	 *
	 * @param parent
	 *            the parent view
	 * @param x
	 *            the x of click point
	 * @param y
	 *            the y of click point
	 * @return the click view
	 */
	public View getTargetView(View parent, int x, int y) {
		View targetView = null;
		ArrayList<View> views = parent.getTouchables();

		for (View v : views)
			if (isTouchPointInView(v, x, y)) {
				targetView = v;
				break;
			}
		return targetView;
	}

	/**
	 * whether click on the view
	 *
	 * @param view
	 *            the view
	 * @param x
	 *            the x of click point
	 * @param y
	 *            the y of click point
	 * @return true yes, false no
	 */
	public boolean isTouchPointInView(View view, int x, int y) {
		int[] location = new int[2];
		view.getLocationOnScreen(location);

		int top = location[1];
		int left = location[0];
		int right = left + view.getMeasuredWidth();
		int bottom = top + view.getMeasuredHeight();

		if (view.isClickable() && y >= top && y <= bottom && x >= left
				&& x <= right)
			return true;
		else
			return false;
	}

	/**
	 * init the params of click view
	 *
	 * @param event
	 *            the event
	 * @param view
	 *            the click view
	 */
	public void initTargetParam(MotionEvent event, View view) {
		mCenterX = event.getX();
		mCenterY = event.getY();
		mTargetWidth = view.getMeasuredWidth();
		mTargetHeight = view.getMeasuredHeight();

		mRadius = 0;
		mRadiusGap = clickGap;

		mMaxRadius = getMaxRadius(view);
	}

	/**
	 * get the max radius
	 *
	 * @param view
	 *            the click view
	 * @return the max radius
	 */
	private int getMaxRadius(View view) {
		int[] location = new int[2];
		view.getLocationOnScreen(location);

		Point center = new Point((int) mCenterX, (int) mCenterY);
		Point top_l = new Point(location[0] - mLocation[0], location[1]
				- mLocation[1]);
		Point bottom_l = new Point(top_l.x, top_l.y + mTargetHeight);
		Point top_r = new Point(top_l.x + mTargetWidth, top_l.y);
		Point bottom_r = new Point(top_r.x, bottom_l.y);

		int dis_top_l = (int) getDistance(center, top_l);
		int dis_top_r = (int) getDistance(center, top_r);
		int dis_bottom_l = (int) getDistance(center, bottom_l);
		int dis_bottom_r = (int) getDistance(center, bottom_r);

		int max = Math.max(dis_top_l, dis_top_r);
		int max1 = Math.max(dis_bottom_l, dis_bottom_r);
		return Math.max(max, max1);
	}

	/**
	 * get the distance between two points
	 *
	 * @param start
	 *            the start point
	 * @param end
	 *            the end point
	 * @return the distance
	 */
	private double getDistance(Point start, Point end) {
		double _x = Math.abs(start.x - end.x);
		double _y = Math.abs(start.y - end.y);
		return Math.sqrt(_x * _x + _y * _y);
	}

	private class DispatchUpEventRunnable implements Runnable {
		public MotionEvent event;

		@Override
		public void run() {
			if (mTargetView.isEnabled() && mTargetView.isClickable())
				return;

			if (isTouchPointInView(mTargetView, (int) event.getRawX(),
					(int) event.getRawX()))
				mTargetView.performClick();
		}
	}
}
