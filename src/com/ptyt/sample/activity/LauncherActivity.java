package com.ptyt.sample.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.GridView;

import com.ptyt.sample.adapter.LauncherAdapter;
import com.ptyt.sample.annotation.ViewInject;
import com.ptyt.sample.widget.ScrollLayout;

public class LauncherActivity extends BaseActivity {
	@ViewInject(id=R.id.sl_srcoll_layout)
	private ScrollLayout sLayout;
	@ViewInject(id=R.id.gv_launcher1)
	private GridView mLauncherGridView1;
	@ViewInject(id=R.id.gv_launcher2)
	private GridView mLauncherGridView2;
	@ViewInject(id=R.id.gv_launcher3)
	private GridView mLauncherGridView3;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setShowActionBar(false);
		setContentView(R.layout.launcher_activity);
		sLayout.setToScreen(1);
		List<String> list = new ArrayList<String>();
		for(int i=0;i<10;i++){
			list.add("App"+i);
		}
		mLauncherGridView1.setAdapter(new LauncherAdapter(LauncherActivity.this,list));
		mLauncherGridView2.setAdapter(new LauncherAdapter(LauncherActivity.this,list));
		mLauncherGridView3.setAdapter(new LauncherAdapter(LauncherActivity.this,list));
	}
	
}
