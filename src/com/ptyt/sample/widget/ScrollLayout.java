package com.ptyt.sample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 仿Launcher中的WorkSapce，可以左右滑动切换屏幕的类 该类继承ViewGroup容器类以实现自己需要的布局显示
 */
public class ScrollLayout extends ViewGroup {
	// 用来平滑过渡各个页面之间的切换
	private Scroller mScroller;
	// 用来跟踪触摸速度的类
	private VelocityTracker mVelocityTracker;

	private int mCurScreen;
	private int mDefaultScreen = 0;

	// 手指离开屏幕状态
	private static final int TOUCH_STATE_REST = 0;
	private static final int TOUCH_STATE_SCROLLING = 1;

	private static final int SNAP_VELOCITY = 600;

	// 手指与屏幕是否接触状态
	private int mTouchState = TOUCH_STATE_REST;
	private int mTouchSlop;

	// 横坐标的最终位置
	private float mLastMotionX;
	// 页数标志
	// private View mScrollIndicator;

	private OnPageChangeListener mPageChangeListener;

	public ScrollLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ScrollLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// 使用缺省的持续时间和动画插入器创建一个Scroller
		mScroller = new Scroller(context);
		mCurScreen = mDefaultScreen;
		// 是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
	}

	/**
	 * 为每一个子view指定size和position
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// if (changed) {//加来这个判断刷新的时候就没有效果了
		// 子view离父view左边的距离
		int childLeft = 0;
		// 获取子view数目
		final int childCount = getChildCount();

		for (int i = 0; i < childCount; i++) {
			// 获取子view的宽度
			final View childView = getChildAt(i);

			if (childView.getVisibility() != View.GONE) {// 如果子view可见的话
				// 获取子view的宽度
				final int childWidth = childView.getMeasuredWidth();
				// 为子view设置大小和位置
				childView.layout(childLeft, 0, childLeft + childWidth,
						childView.getMeasuredHeight());
				// 左边距自加子view宽度，从而得到下一个子view的x坐标
				childLeft += childWidth;
			}
		}
		// }
	}

	/**
	 * 指明控件可获得的空间以及关于这个空间描述的元数据
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		/**
		 * int specMode = MeasureSpec.getMode(measureSpec); int specSize =
		 * MeasureSpec.getSize(measureSpec); 依据specMode的值，如果是AT_MOST，specSize
		 * 代表的是最大可获得的空间； 如果是EXACTLY，specSize 代表的是精确的尺寸；
		 * 如果是UNSPECIFIED，对于控件尺寸来说，没有任何参考意义。
		 */
		final int width = MeasureSpec.getSize(widthMeasureSpec);
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		if (widthMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException(
					"ScrollLayout only canmCurScreen run at EXACTLY mode!");
		}

		/**
		 * wrap_content 传进去的是AT_MOST 固定数值或fill_parent 传入的模式是EXACTLY
		 */
		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (heightMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException(
					"ScrollLayout only can run at EXACTLY mode!");
		}

		// 给每一个子view给予相同的空间
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
		}
		// 滚动到目标坐标
		scrollTo(mCurScreen * width, 0);
	}

	/**
	 * 根据当前布局的位置，滚动到目的页面
	 */
	public void snapToDestination() {
		// 获取view的宽度
		final int screenWidth = getWidth();
		// getScrollX():获得滚动后view的横坐标
		final int destScreen = (getScrollX() + screenWidth / 2) / screenWidth;
		snapToScreen(destScreen);
	}

	public void snapToScreen(int whichScreen) {
		// 获取有效页面
		whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
		if (getScrollX() != (whichScreen * getWidth())) {

			final int delta = whichScreen * getWidth() - getScrollX();
			mScroller.startScroll(getScrollX(), 0, delta, 0,
					Math.abs(delta) * 2);
			mCurScreen = whichScreen;
			invalidate(); // 使view重画
		}
	}

	public void setToScreen(int whichScreen) {
		whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
		mCurScreen = whichScreen;
		scrollTo(whichScreen * getWidth(), 0);
	}

	/**
	 * 获得当前页码
	 */
	public int getCurScreen() {
		return mCurScreen;
	}


	/**
	 * 由父视图调用，用于通知子视图在必要时更新 mScrollX 和 mScrollY 的值 该操作主要用于子视图使用 Scroller
	 * 进行动画滚动时。
	 */
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {// 返回true，表示动画仍在进行，还没有停止
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());// 滚动到目标坐标
			postInvalidate();// 使view重画
		}
	}

	/**
	 * 触摸监听事件
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (mVelocityTracker == null) {
			// 获取mVelocityTracker实例对象
			mVelocityTracker = VelocityTracker.obtain();
		}
		// 将当前的移动事件传递给mVelocityTracker对象
		mVelocityTracker.addMovement(event);

		// 获取当前触摸动作
		final int action = event.getAction();
		final float x = event.getX();
		// final float y = event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:// 当向下按时
			if (!mScroller.isFinished()) {
				mScroller.abortAnimation();
			}
			mLastMotionX = x;
			break;
		case MotionEvent.ACTION_MOVE:// 当手指滑动时
			int deltaX = (int) (mLastMotionX - x);
			mLastMotionX = x;
			// scrollBy是将view的内容移动多大的距离 deltaX:指移动的距离
			scrollBy(deltaX, 0);
			break;
		case MotionEvent.ACTION_UP:// 当手指抬起时
			final VelocityTracker velocityTracker = mVelocityTracker;
			// 计算当前速度
			velocityTracker.computeCurrentVelocity(1000);
			// 获取当前x方向的速度
			int velocityX = (int) velocityTracker.getXVelocity();

			if (velocityX > SNAP_VELOCITY && mCurScreen > 0) {// 向右滑动并且手指滑动速度大于指定的速度(此时速度的方向为正)
				// Fling enough to move left
				snapToScreen(mCurScreen - 1);
			} else if (velocityX < -SNAP_VELOCITY
					&& mCurScreen < getChildCount() - 1) {// 向左滑动时并且手指滑动的速度也大于指定的速度(此时速度方向为负)
				// Fling enough to move right
				snapToScreen(mCurScreen + 1);// 滑到后一个页面
			} else {
				snapToDestination();
			}
			if (mVelocityTracker != null) {// 释放VelocityTracker对象
				mVelocityTracker.recycle();
				mVelocityTracker = null;
			}
			mTouchState = TOUCH_STATE_REST;
			break;
		case MotionEvent.ACTION_CANCEL:
			mTouchState = TOUCH_STATE_REST;
			break;
		}
		return true;
	}

	/**
	 * 该方法是用于拦截手势事件的，每个手势事件都会先调用 此方法返回false，则手势事件会向子控件传递
	 * 返回true，则调用onTouchEvent方法
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		if ((action == MotionEvent.ACTION_MOVE)
				&& (mTouchState != TOUCH_STATE_REST)) {
			return true;
		}

		final float x = ev.getX();
		// final float y = ev.getY();

		switch (action) {
		case MotionEvent.ACTION_MOVE:
			final int xDiff = (int) Math.abs(mLastMotionX - x);
			if (xDiff > mTouchSlop) {
				mTouchState = TOUCH_STATE_SCROLLING;// 视图还在移动状态
			}
			break;
		case MotionEvent.ACTION_DOWN:
			mLastMotionX = x;
			// mLastMotionY = y;
			mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST
					: TOUCH_STATE_SCROLLING;
			break;

		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mTouchState = TOUCH_STATE_REST;
			break;
		}
		return mTouchState != TOUCH_STATE_REST;
	}

	public void setPageChangeListener(OnPageChangeListener pageListener) {
		this.mPageChangeListener = pageListener;
	}

	public interface OnPageChangeListener {
		void onPageScrollStateChanged(int page);
	}
}