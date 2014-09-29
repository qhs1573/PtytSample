package com.ptyt.sample.widget;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.ptyt.sample.activity.R;
import com.ptyt.sample.adapter.LauncherAdapter;
import com.ptyt.sample.utils.PrintLog;

public class LauncherView extends LinearLayout {
	private static final String TAG = LauncherView.class.getSimpleName();
	private Context mContext;
	private GridView mGridView;
	private LauncherAdapter mLauncherAdapter;
	private int currentPage=-1;
	public LauncherView(Context context) {
		this(context,null,-1);
	}

	public LauncherView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext=context;
	}

	public LauncherView(Context context, AttributeSet attrs) {
		this(context,attrs,-1);
	}
	
	public void showGridView(List<String> list){
		PrintLog.d(TAG, "list size:::"+list.size());
		mGridView = new GridView(mContext);
		mGridView.setSelector(R.anim.grid_light);
		mGridView.setNumColumns(3);
		mGridView.setHorizontalSpacing(0);
		mGridView.setVerticalSpacing(0);
		mLauncherAdapter = new LauncherAdapter(mContext, list);
		mGridView.setAdapter(mLauncherAdapter);
		removeAllViews();
		addView(mGridView);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
