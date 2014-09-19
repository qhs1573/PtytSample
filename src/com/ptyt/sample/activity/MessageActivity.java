package com.ptyt.sample.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ptyt.sample.adapter.MessageAdapter;
import com.ptyt.sample.annotation.ViewInject;
import com.ptyt.sample.utils.ToastUtil;

public class MessageActivity extends BaseActivity {
	@ViewInject(id=R.id.message_activity_ll_new,click="onClickNew")
	private LinearLayout mNew;
	@ViewInject(id=R.id.message_activity_lv_msglist)
	private ListView mMessageList;
	private MessageAdapter mMsgeAdapter;
	private List list = new ArrayList();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setShowActionBar(false);
		setContentView(R.layout.message_activity);
		mNew = (LinearLayout) findViewById(R.id.message_activity_ll_new);
		for(int i=0;i<10;i++){
			list.add(" ");
		}
		mMsgeAdapter = new MessageAdapter(this, list);
		mMessageList.setAdapter(mMsgeAdapter);
		
		mNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ToastUtil.getToast().showMessage(MessageActivity.this, "新建",-1);
			}
		});
	}
	
	public void onClickNew(View view){
		ToastUtil.getToast().showMessage(this, "新建",-1);
		//新建
	}
			
}
