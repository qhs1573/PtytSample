package com.ptyt.sample.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.ptyt.sample.adapter.MsgAdapter;
import com.ptyt.sample.annotation.ViewInject;
import com.ptyt.sample.bean.ChatMsgEntity;
import com.ptyt.sample.utils.ToastUtil;

public class BubbleChartActivity extends BaseActivity {
	@ViewInject(id=R.id.bubblechart_activity_lv_mesgList)
	private ListView mListView;
	@ViewInject(id=R.id.bubblechart_activity_tv_title)
	private TextView mTitle;
	@ViewInject(id=R.id.bubblechart_activity_et_message)
	private EditText mEditContent;
	@ViewInject(id=R.id.bubblechart_activity_btn_takephotos,click="onClickCamera")
	private Button mTakePhotos;
	@ViewInject(id=R.id.bubblechart_activity_btn_send,click="onClickSend")
	private Button mSend;
	private MsgAdapter mMsgAdapter;
	private List<ChatMsgEntity> msgList = new ArrayList<ChatMsgEntity>(); 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setShowActionBar(false);
		setContentView(R.layout.bubblechart_activity);
		for(int i=0;i<10;i++){
			if(i%2 == 0){
				msgList.add(new ChatMsgEntity("����", getTime(), "�����У�һλվ�ŵ��и������������ŵ�����˵���㲻֪���һ�������ֻ�����Ӻܽ��ŵ�˵�������Ӳ����ҵģ�����", true));
			}else{
				msgList.add(new ChatMsgEntity("����", getTime(), "�õ�׼ʱ��ȥ����ʱ�����"+i, false));
			}
			
		}
		
		msgList.add(new ChatMsgEntity("����", getTime(),"���ĿΣ���ʦ����һ��˯ͬѧ�ش����⣬��ͬѧ���Ժ���ɶҲ˵����������ʦ˵������᲻��ѽ������Ҳ֨һ����������ͬѧ����֨����",false));
		mMsgAdapter = new MsgAdapter(this, msgList);
		mListView.setAdapter(mMsgAdapter);
	}
	
	public String getTime(){
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-mm-dd hh:MM");
		return sFormat.format(new Date());
	}
	
	public void onClickCamera(View v){
		ToastUtil.getToast().showMessage(this,"Camera",-1);
	}
	
	public void onClickSend(View v){
		ToastUtil.getToast().showMessage(this,"Send",-1);
	}
}
