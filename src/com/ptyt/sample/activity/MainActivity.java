package com.ptyt.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.ptyt.sample.adapter.SampleAdapter;
import com.ptyt.sample.annotation.ViewInject;
import com.ptyt.sample.bean.Person;

public class MainActivity extends BaseActivity implements OnItemClickListener,
		OnItemLongClickListener {
	@ViewInject(id = R.id.lv_mList)
	private ListView mListView;
	private SampleAdapter mSampleAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		getmTextView().setText(R.string.string_sample_list);
		mSampleAdapter = new SampleAdapter(this);
		mListView.setAdapter(mSampleAdapter);
		mListView.setOnItemClickListener(this);
		mListView.setOnItemLongClickListener(this);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent ();
		switch (arg2) {
		case 0:

			break;

		case 1:
			intent.setClass(this, BubbleChartActivity.class);
			startActivity(intent);
			break;
			
		case 2:
			intent.setClass(this, MessageActivity.class);
			startActivity(intent);
			break;
			
		case 3:
			intent.setClass(this, DateActivity.class);
			startActivity(intent);
			break;
			
		case 4:
			intent.setClass(this, ListViewTestActivity.class);
			Person perso = new Person();
			perso.setId(0);
			perso.setName("zhangsan");
			intent.putExtra("person", perso);
			intent.putExtra("str", "str");
			startActivity(intent);
			break;
			
		case 5:
			intent.setClass(this, GpsActivity.class);
			startActivity(intent);
			break;
			
		case 6:
			intent.setClass(this, HandlerActivity.class);
			startActivity(intent);
			break;
			
		case 7:
			intent.setClass(this, ListViewSelectActivity.class);
			startActivity(intent);
			break;
			
		case 8:
			intent.setClass(this, AidlActivity.class);
			startActivity(intent);
			break;
			
		case 9:
			intent.setClass(this, LauncherActivity.class);
			startActivity(intent);
			break;
		case 10:
			intent.setClass(this, LayoutAnimationsByDefault.class);
			startActivity(intent);
			break;
		case 11:
			intent.setClass(this, CustomCameraActivity.class);
			startActivity(intent);
			break;
			
		case 12:
			intent.setClass(this, CustomCameraActivity.class);
			startActivity(intent);
			break;
		
		case 13:
			intent.setClass(this, MemInfoActivity.class);
			startActivity(intent);
			break;
			
		case 14:
			intent.setClass(this, TopActivity.class);
			startActivity(intent);
			break;
		}

	}
}
