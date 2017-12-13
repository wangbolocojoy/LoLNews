package com.rhino.ui.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

import com.rhino.u.R;


/**
 * 自定义横向进度条，支持显示分段点实心圆。</br>
 * <p>attr ref android.R.styleable#CustomSeekBar_progress_height</p>
 * <p>attr ref android.R.styleable#CustomSeekBar_progress_corner</p>
 * <p>attr ref android.R.styleable#CustomSeekBar_section_radius</p>
 * <p>attr ref android.R.styleable#CustomSeekBar_section_enable</p>
 * <p>attr ref android.R.styleable#CustomSeekBar_thumb_radius</p>
 * <p>attr ref android.R.styleable#CustomSeekBar_thumb_point</p>
 * <p>attr ref android.R.styleable#CustomSeekBar_background_color</p>
 * <p>attr ref android.R.styleable#CustomSeekBar_progress_color</p>
 * <p>attr ref android.R.styleable#CustomSeekBar_min_value</p>
 * <p>attr ref android.R.styleable#CustomSeekBar_max_value</p>
 * <p>attr ref android.R.styleable#CustomSeekBar_overspread_enable</p>
 * 
 **/
/**
 * Created by wb
 * on 2017/11/21 
 * on CustomSeekBarSection 
 */
public class CustomSeekBarSection extends View {

    /**
     * 默认进度条背景颜色
     **/
    private static final int DEFAULT_PROGRESS_BACKGROUND_COLOR = 0xFFDDDDDD;
    /**
     * 默认进度条颜色
     **/
    private static final int DEFAULT_PROGRESS_COLOR = 0xFF28AAE5;
    /**
     * 默认进度条分段点实心圆半径
     **/
    private static final int DEFAULT_SECTION_POINT_RADIUS = 6;
    /**
     * 默认不显示分段点实心圆
     **/
    private static final boolean DEFAULT_OVERSPREAD_ENABLE = false;
    /**
     * 默认进度条滑块半径
     **/
    private static final int DEFAULT_THUMB_RADIUS = 18;
    /**
     * 默认进度条最小值
     **/
    private static final int DEFAULT_MIN_PROGRESS = 0;
    /**
     * 默认进度条最大值
     **/
    private static final int DEFAULT_MAX_PROGRESS = 100;
    /**
     * 默认圆弧边角
     **/
    private static final int DEFAULT_PROGRESS_CORNER = 2;
    /**
     * 默认进度条高度
     **/
    private static final int DEFAULT_PROGRESS_HEIGHT = 2;
    /**
     * 默认进度条不铺满控件
     **/
    private static final boolean DEFAULT_SECTION_POINT_ENABLE = false;

    /**
     * 进度条背景颜色
     **/
    private int mProgressBackgroundColor = DEFAULT_PROGRESS_BACKGROUND_COLOR;
    /**
     * 进度条颜色
     **/
    private int mProgressColor = DEFAULT_PROGRESS_COLOR;
    /**
     * 进度条分段点实心圆的半径
     **/
    private int mSectionPointRadius = DEFAULT_SECTION_POINT_RADIUS;
    /**
     * 是否显示分段点实心圆，默认不显示
     **/
    private boolean mSectionPointEnable = DEFAULT_SECTION_POINT_ENABLE;
    /**
     * 进度条滑块的半径
     **/
    private int mThumbRadius = DEFAULT_THUMB_RADIUS;
    /**
     * 进度条最小值
     **/
    private int mMinProgress = DEFAULT_MIN_PROGRESS;
    /**
     * 进度条最大值
     **/
    private int mMaxProgress = DEFAULT_MAX_PROGRESS;
    /**
     * 圆弧边角弧度
     **/
    private int mProgressCorner = DEFAULT_PROGRESS_CORNER;
    /**
     * 进度条宽度
     **/
    private int mProgressHeight = DEFAULT_PROGRESS_HEIGHT;
    /**
     * 进度条是否铺满控件
     **/
    private boolean mOverspreadEnable = DEFAULT_OVERSPREAD_ENABLE;
    /**
     * 进度条宽度
     **/
    private int mProgressWidth;
    /**
     * 增加滑块的点击范围
     **/
    private int mThumbTouchOffset;
    /**
     * 进度条当前值
     **/
    private int mCurrProgress;
    /**
     * 进度条上一个值
     **/
    private int mLastProgress;

    /**
     * 视图高度
     **/
    private int mViewHeight;
    /**
     * 视图宽度
     **/
    private int mViewWidth;
    /**
     * 进度条背景
     **/
    private GradientDrawable mProgressBgDrawable;
    /**
     * 进度条
     **/
    private GradientDrawable mProgressDrawable;
    /**
     * 进度条分段点实心圆
     **/
    private Paint mSectionPointPaint;
    /**
     * 滑块
     **/
    private Paint mThumbPaint;
    /**
     * 进度条背景
     **/
    private Rect mProgressBackgroundRect;
    /**
     * 进度条
     **/
    private Rect mProgressRect;
    /**
     * 进度条分段点实心圆
     **/
    private Rect mSectionPointRect;
    /**
     * 进度条滑动块
     **/
    private Rect mThumbSrcRect;
    /**
     * 进度条滑动块目标位置
     **/
    private Rect mThumbDestRect;
    /**
     * 滑块Drawable
     **/
    private Drawable mThumbDrawable = null;
    /**
     * 滑块Bitmap
     **/
    private Bitmap mThumbBitmap;

    /**
     * 是否点击滑块
     **/
    private boolean mIsClickOnThumb = false;
    /**
     * 是否点击在进度条上
     **/
    private boolean mIsClickOnProgress = false;
    /**
     * 是否滑出控件之外
     **/
    private boolean mIsMovedOut = false;
    /**
     * 是否进度改变来自用户
     **/
    private boolean mIsFromUser = false;
    /**
     * 是否可以调节
     **/
    private boolean mIsThumbEnable = true;
    /**
     * 事件监听
     **/
    private OnProgressChangedListener mOnProgressListener;


    public CustomSeekBarSection(Context context) {
        super(context);
        init();
    }

    public CustomSeekBarSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs, 0);
        init();
    }

    public CustomSeekBarSection(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs, defStyle);
        init();
    }

    /**
     * 资源初始化
     **/
    private void initAttrs(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomSeekBarSection, defStyle, 0);
        mProgressHeight = a.getDimensionPixelSize(R.styleable.CustomSeekBarSection_progress_height,
                dip2px(context, DEFAULT_PROGRESS_HEIGHT));
        mProgressCorner = a.getDimensionPixelSize(R.styleable.CustomSeekBarSection_progress_corner,
                dip2px(context, DEFAULT_PROGRESS_CORNER));
        mThumbRadius = a.getDimensionPixelSize(R.styleable.CustomSeekBarSection_thumb_radius,
                dip2px(context, DEFAULT_THUMB_RADIUS));
        mThumbDrawable = a.getDrawable(R.styleable.CustomSeekBarSection_thumb_point);
        mSectionPointRadius = a.getDimensionPixelSize(R.styleable.CustomSeekBarSection_section_radius,
                dip2px(context, DEFAULT_SECTION_POINT_RADIUS));
        mSectionPointEnable = a.getBoolean(R.styleable.CustomSeekBarSection_section_enable,
                DEFAULT_SECTION_POINT_ENABLE);
        mProgressBackgroundColor = a.getColor(R.styleable.CustomSeekBarSection_background_color,
                DEFAULT_PROGRESS_BACKGROUND_COLOR);
        mProgressColor = a.getColor(R.styleable.CustomSeekBarSection_progress_color,
                DEFAULT_PROGRESS_COLOR);
        mMinProgress = a.getInt(R.styleable.CustomSeekBarSection_min_value,
                DEFAULT_MIN_PROGRESS);
        mMaxProgress = a.getInt(R.styleable.CustomSeekBarSection_max_value,
                DEFAULT_MAX_PROGRESS);
        mOverspreadEnable = a.getBoolean(R.styleable.CustomSeekBarSection_overspread_enable,
                DEFAULT_OVERSPREAD_ENABLE);
        a.recycle();
    }

    /**
     * 资源初始化
     **/
    private void init() {
        mSectionPointPaint = new Paint();
        mSectionPointPaint.setStyle(Paint.Style.FILL);
        mSectionPointPaint.setColor(mProgressColor);
        mSectionPointPaint.setAntiAlias(true);

        mThumbPaint = new Paint();
        mThumbPaint.setStyle(Paint.Style.FILL);
        mThumbPaint.setAntiAlias(true);

        mProgressBgDrawable = new GradientDrawable();
        mProgressBgDrawable.setShape(GradientDrawable.RECTANGLE);
        mProgressBgDrawable.setColor(mProgressBackgroundColor);

        mProgressDrawable = new GradientDrawable();
        mProgressDrawable.setShape(GradientDrawable.RECTANGLE);
        mProgressDrawable.setColor(mProgressColor);

        mProgressBackgroundRect = new Rect();
        mProgressRect = new Rect();
        mSectionPointRect = new Rect();
        mThumbSrcRect = new Rect();
        mThumbDestRect = new Rect();

        mCurrProgress = mMinProgress;
    }

    /**
     * 初始化视图
     *
     * @param width  控件宽度
     * @param height 控件高度
     */
    private void initViewSize(int width, int height) {
        if (width <= 0 || height <= 0) {
            return;
        }

        mProgressWidth = width - 2 * mThumbRadius;
        mThumbTouchOffset = mThumbRadius;

        mProgressBackgroundRect.top = -mProgressHeight;
        mProgressBackgroundRect.bottom = mProgressHeight;
        mProgressBackgroundRect.left = mOverspreadEnable ? -width / 2 : -mProgressWidth / 2;
        mProgressBackgroundRect.right = mOverspreadEnable ? width / 2 : mProgressWidth / 2;

        mProgressRect.top = -mProgressHeight;
        mProgressRect.bottom = mProgressHeight;
        mProgressRect.left = mOverspreadEnable ? -width / 2 : -mProgressWidth / 2;
        mProgressRect.right = -mProgressWidth/2;

        mSectionPointRect.top = -mSectionPointRadius;
        mSectionPointRect.bottom = mSectionPointRadius;
        mSectionPointRect.left = -mSectionPointRadius;
        mSectionPointRect.right = mSectionPointRadius;

        mThumbSrcRect.top = -mThumbRadius;
        mThumbSrcRect.bottom = mThumbRadius;
        mThumbSrcRect.left = -mThumbRadius;
        mThumbSrcRect.right = mThumbRadius;

        mThumbDestRect.top = -mThumbRadius;
        mThumbDestRect.bottom = mThumbRadius;
        mThumbDestRect.left = -mProgressWidth/2 - mThumbRadius;
        mThumbDestRect.right = -mProgressWidth/2 + mThumbRadius;

        setThumbDrawable(mThumbDrawable);
        setProgress(mCurrProgress);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mViewWidth = widthSize;
        } else {
            mViewWidth = getWidth();
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mViewHeight = heightSize;
        } else {
            mViewHeight = getHeight();
        }

        initViewSize(mViewWidth, mViewHeight);
        setMeasuredDimension(mViewWidth, mViewHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX() - mViewWidth / 2;
        float y = event.getY() - mViewHeight / 2;
        ViewParent parent = getParent();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!mIsThumbEnable){
                    return super.onTouchEvent(event);
                }
                if (clickOnThumb(x, y)) {
                    mIsClickOnThumb = true;
                    mIsClickOnProgress = true;
                    if (null != parent) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                } else if (clickOnProgress(x, y)) {
                    mIsClickOnProgress = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsClickOnThumb) {
                    moveToPoint(x);
                    onProgressChanged(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                mIsClickOnThumb = false;
                if (mIsClickOnProgress) {
                    mIsClickOnProgress = false;
                    moveToPoint(x);
                    onProgressChanged(true);
                }
                if (null != parent) {
                    parent.requestDisallowInterceptTouchEvent(false);
                }
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();

        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        drawProgressBackground(canvas);
        drawProgress(canvas);
        if (mSectionPointEnable) {
            drawProgressSectionPoint(canvas);
        }
        drawThumb(canvas);

        canvas.restore();
    }

    /**
     * 进度条背景
     **/
    private void drawProgressBackground(Canvas canvas) {
        canvas.save();
        mProgressBgDrawable.setBounds(mProgressBackgroundRect);
        mProgressBgDrawable.setCornerRadius(mProgressCorner);
        mProgressBgDrawable.draw(canvas);
        canvas.restore();
    }

    /**
     * 进度条
     **/
    private void drawProgress(Canvas canvas) {
        canvas.save();
        mProgressDrawable.setBounds(mProgressRect);
        mProgressDrawable.setCornerRadius(mProgressCorner);
        mProgressDrawable.draw(canvas);
        canvas.restore();
    }

    /**
     * 进度条上每刻进度的位置画一个实心圆
     **/
    private void drawProgressSectionPoint(Canvas canvas) {
        canvas.save();
        for (int i = mMinProgress; i <= mMaxProgress; i++) {
            float sectionX = progress2XCoord(i);
            mSectionPointRect.left = (int) (sectionX - mSectionPointRadius);
            mSectionPointRect.right = (int) (sectionX + mSectionPointRadius);
            if (sectionX <= mProgressRect.right) {
                mSectionPointPaint.setColor(mProgressColor);
            } else {
                mSectionPointPaint.setColor(mProgressBackgroundColor);
            }
            canvas.drawCircle(mSectionPointRect.centerX(),
                    mSectionPointRect.centerY(), mSectionPointRect.width() / 2,
                    mSectionPointPaint);
        }
        canvas.restore();
    }

    /**
     * 进度条滑块
     **/
    private void drawThumb(Canvas canvas) {
        canvas.save();
        if (null != mThumbBitmap) {
            canvas.drawBitmap(mThumbBitmap, mThumbSrcRect,
                    mThumbDestRect, mThumbPaint);
        } else {
            mThumbPaint.setColor(mProgressColor);
            canvas.drawCircle(mThumbDestRect.centerX(), mThumbDestRect.centerY(),
                    mThumbDestRect.width() / 2, mThumbPaint);
        }
        canvas.restore();
    }

    /**
     * 是否点击滑动按钮
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @return true点击 false未点击
     */
    private boolean clickOnThumb(float x, float y) {
        return mThumbDestRect.left < mThumbDestRect.right
                && mThumbDestRect.top < mThumbDestRect.bottom
                && x >= mThumbDestRect.left - mThumbTouchOffset
                && x <= mThumbDestRect.right + mThumbTouchOffset
                && y >= mThumbDestRect.top - mThumbTouchOffset
                && y <= mThumbDestRect.bottom + mThumbTouchOffset;
    }

    /**
     * 是否点击滑动按钮
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @return true点击 false未点击
     */
    private boolean clickOnProgress(float x, float y) {
        return mProgressBackgroundRect.left < mProgressBackgroundRect.right
                && mProgressBackgroundRect.top < mProgressBackgroundRect.bottom
                && x >= mProgressBackgroundRect.left - mThumbTouchOffset
                && x <= mProgressBackgroundRect.right + mThumbTouchOffset
                && y >= mProgressBackgroundRect.top - mThumbTouchOffset
                && y <= mProgressBackgroundRect.bottom + mThumbTouchOffset;
    }

    /**
     * 事件监听
     *
     * @param isFinished 是否完成滑动
     */
    private void onProgressChanged(boolean isFinished) {
        if (!mIsMovedOut) {
            mCurrProgress = xCoord2Progress(mThumbDestRect.centerX());
        }
        if (null != mOnProgressListener) {
            if (mLastProgress != mCurrProgress || isFinished) {
                mIsFromUser = true;
                mOnProgressListener.onChanged(this, mIsFromUser, isFinished);
                mIsFromUser = false;
                if (isFinished) { // 滑动结束后修正滑块的位置
                    float x = progress2XCoord(mCurrProgress);
                    moveToPoint(x);
                }
            }
            mLastProgress = mCurrProgress;
        }
        mIsMovedOut = false;
    }

    /**
     * 开始滑动
     *
     * @param x 横坐标
     */
    private void moveToPoint(float x) {
        float halfWidth = mProgressWidth / 2;
        if (x > halfWidth) { // 滑至最右边
            x = halfWidth;
            mIsMovedOut = true;
            mCurrProgress = mMaxProgress;
        } else if (x < -halfWidth) { // 滑至最左边
            x = -halfWidth;
            mIsMovedOut = true;
            mCurrProgress = mMinProgress;
        }
        mThumbDestRect.left = (int) (x - mThumbRadius);
        mThumbDestRect.right = (int) (x + mThumbRadius);
        mProgressRect.right = (int) x;

        invalidate();
    }

    /**
     * 进度值转换为x坐标
     *
     * @param progress 进度值
     * @return x坐标
     */
    private float progress2XCoord(int progress) {
        return (float) mProgressWidth * (progress - mMinProgress)
                / (mMaxProgress - mMinProgress) - mProgressWidth / 2f;
    }

    /**
     * x坐标转换为进度值
     *
     * @param x 横坐标
     * @return 进度值
     */
    private int xCoord2Progress(int x) {
        return Math.round((x + mProgressWidth / 2f)
                * (mMaxProgress - mMinProgress) / mProgressWidth)
                + mMinProgress;
    }

    /**
     * 是否进度改变来自用户
     * @return true 是
     */
    public boolean isFromUser(){
        return mIsFromUser;
    }

    /**
     * 是否完成控制
     * @return true 是
     */
    public boolean isFinished(){
        return !mIsClickOnProgress && !mIsClickOnThumb;
    }

    /**
     * 获取上一次进度值
     *
     * @return 上一次进度值
     */
    public int getLastProgress() {
        return mLastProgress;
    }

    /**
     * 获取当前进度值
     *
     * @return 当前进度值
     */
    public int getProgress() {
        return mCurrProgress;
    }

    /**
     * 设置进度值
     *
     * @param progress 进度
     */
    public void setProgress(int progress) {
        if(!mIsThumbEnable || !isFinished()){
            return; // 手动调节时不支持外部控制
        }
        if(progress <= mMinProgress){
            progress = mMinProgress;
        } else if(progress >= mMaxProgress){
            progress = mMaxProgress;
        }
        mCurrProgress = progress;
        float x = progress2XCoord(mCurrProgress);
        moveToPoint(x);
        if (null != mOnProgressListener) {
            if (mLastProgress != mCurrProgress) {
                mIsFromUser = false;
                mOnProgressListener.onChanged(this, false, true);
            }
            mLastProgress = mCurrProgress;
        }
    }

    /**
     * 设置进度条最小值
     *
     * @param minProgress 最小值
     */
    public void setMinProgress(int minProgress) {
        this.mMinProgress = minProgress;
        if (mCurrProgress < mMinProgress) {
            this.mCurrProgress = mMinProgress;
        }
    }

    /***
     * 设置进度条最大值
     *
     * @param maxProgress 最大值
     */
    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }
    public int getMaxProgress(){
        int s=mMaxProgress;
        return s;
    }
    /**
     * 设置进度条背景颜色
     *
     * @param color 颜色值
     */
    public void setProgressBackgroundColor(@ColorInt int color) {
        this.mProgressBackgroundColor = color;
        mProgressBgDrawable.setColor(mProgressBackgroundColor);
    }

    /**
     * 设置进度条颜色
     *
     * @param color 颜色值
     */
    public void setProgressColor(@ColorInt int color) {
        this.mProgressColor = color;
        mProgressDrawable.setColor(mProgressColor);
    }

    /**
     * 设置滑动图片
     *
     * @param bitmap Bitmap
     */
    public void setThumbBitmap(Bitmap bitmap) {
        if (null == bitmap) {
            return;
        }
        mThumbBitmap = zoomImage(bitmap, mThumbRadius,
                mThumbRadius);
    }

    /**
     * 设置滑块图片
     *
     * @param drawable Drawable
     */
    public void setThumbDrawable(Drawable drawable) {
        if (null == drawable) {
            return;
        }
        mThumbDrawable = drawable;
        setThumbBitmap(drawable2Bitmap(mThumbDrawable));
    }

    /**
     * 设置是否显示分段点实心圆
     *
     * @param enable true 显示
     */
    public void setSectionEnable(boolean enable) {
        this.mSectionPointEnable = enable;
    }

    /**
     * 设置是否可调节
     * @param enable true 可以调节
     */
    public void setThumbEnable(boolean enable){
        this.mIsThumbEnable = enable;
    }

    /**
     * 设置监听
     *
     * @param listener 事件监听
     */
    public void setOnProgressChangedListener(OnProgressChangedListener listener) {
        mOnProgressListener = listener;
    }

    public interface OnProgressChangedListener {
        void onChanged(CustomSeekBarSection seekBar, boolean fromUser,
                       boolean isFinished);
    }

    /**
     * dip转px
     *
     * @param ctx     上下文
     * @param dpValue dip
     * @return the px
     */
    private int dip2px(Context ctx, float dpValue) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Drawable转换为bitmap
     *
     * @param drawable Drawable
     * @return bitmap
     */
    private Bitmap drawable2Bitmap(Drawable drawable) {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        return bd.getBitmap();
    }

    /**
     * 缩放Bitmap
     *
     * @param srcBitmap 源Bitmap
     * @param newHeight 新的height
     * @param newWidth  新的width
     * @return 新的Bitmap
     */
    private Bitmap zoomImage(Bitmap srcBitmap, int newHeight, int newWidth) {
        if (null == srcBitmap) {
            return null;
        }
        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleHeight = (float) newHeight / height;
        float scaleWidth = (float) newWidth / width;
        matrix.setScale(scaleWidth, scaleHeight);
        srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, width, height,
                matrix, true);
        return srcBitmap;
    }
}
