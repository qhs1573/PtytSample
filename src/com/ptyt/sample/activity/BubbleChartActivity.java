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
				msgList.add(new ChatMsgEntity("张三", getTime(), "公车中，一位站着的孕妇对她身旁坐着的男子说：你不知道我怀孕了吗？只见男子很紧张地说：“孩子不是我的！！”", true));
			}else{
				msgList.add(new ChatMsgEntity("李四", getTime(), "好的准时过去，到时候见。"+i, false));
			}
			
		}
		
		msgList.add(new ChatMsgEntity("李四", getTime(),"语文课，老师叫起一昏睡同学回答问题，该同学迷迷糊糊啥也说不出……老师说：“你会不会呀？不会也吱一声啊！”该同学：“吱。”",false));
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
