package com.ptyt.sample.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.ptyt.sample.annotation.ViewInject;
import com.ptyt.sample.bean.PageSupport;
import com.ptyt.sample.widget.LauncherView;
import com.ptyt.sample.widget.ScrollLayout;

public class LauncherActivity extends BaseActivity {
	@ViewInject(id=R.id.sl_srcoll_layout)
	private ScrollLayout sLayout;
	private LauncherView[] launcerView;
	private View pointView[];
	@ViewInject(id=R.id.ll_launcher_currentpage)
	private LinearLayout mPoint;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setShowActionBar(false);
		setContentView(R.layout.launcher_activity);
		initPointView(6);
		List<String> list = new ArrayList<String>();
		for(int i=0;i<12;i++){
			list.add("App"+i);
		}
		
		List<PageSupport> list1 = new ArrayList<PageSupport>();
		for(int i=0;i<6;i++){
			PageSupport pageSupport = new PageSupport();
			pageSupport.setCurrentPageNo(i);
			pageSupport.setResult(list);
			pageSupport.setTotalRecordCount(list.size());
			list1.add(pageSupport);
		}
		
		launcerView = new LauncherView[list1.size()];
		for(int i=0;i<list1.size();i++){
			launcerView[i] = new LauncherView(this);
			launcerView[i].setCurrentPage(list1.get(i).getCurrentPageNo());
			sLayout.addView(launcerView[i], WindowManager.LayoutParams.MATCH_PARENT,  WindowManager.LayoutParams.MATCH_PARENT);
		}
		
		for(int s=0;s<list1.size();s++){
			launcerView[s].showGridView((List<String>) list1.get(s).getResult());
		}
	}
	
	private void initPointView(int viewSize){
		pointView = new View[viewSize];
		LinearLayout.LayoutParams ll_params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		ll_params.leftMargin=10;
		ll_params.topMargin=200;
		ll_params.width=10;
		ll_params.height=10;
		ll_params.gravity=Gravity.CENTER;
		for(int i=0;i<viewSize;i++){
			pointView[i] = new View(this,null,R.style.style_launcher_point);
			pointView[i].setBackgroundResource(android.R.color.holo_orange_light);
			mPoint.addView(pointView[i],ll_params);
		}
	}
	
}


