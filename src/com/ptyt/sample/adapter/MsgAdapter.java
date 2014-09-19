package com.ptyt.sample.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ptyt.sample.activity.R;
import com.ptyt.sample.bean.ChatMsgEntity;

public class MsgAdapter extends BaseAdapter {
	private Activity act;
	private List<ChatMsgEntity> mList;
	private LayoutInflater mInflater;

	public MsgAdapter(Activity act, List<ChatMsgEntity> list) {
		this.act = act;
		this.mList = list;
		mInflater = LayoutInflater.from(act);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList != null ? mList.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	class HoldView{
		boolean isComMsg = true;
		TextView mSendTime; 
		TextView mChatContent;
		TextView mUsername;
	}
	
	public static interface IMsgViewType {
		// 对方发来的信息
		int IMVT_COM_MSG = 0;
		// 自己发出的信息
		int IMVT_TO_MSG = 1;
	}

	
	@Override
	public int getItemViewType(int position) {
		ChatMsgEntity entity = mList.get(position);
		if (entity.isComMeg()) {
			return IMsgViewType.IMVT_COM_MSG;
		} else {
			return IMsgViewType.IMVT_TO_MSG;
		}
	}
	
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HoldView hv;
		ChatMsgEntity entity = mList.get(position);
		boolean isComMsg = entity.isComMeg();
		if (convertView == null) {
			if (isComMsg) {
				// 如果是对方发来的消息，则显示的是左气泡
				convertView = mInflater.inflate(R.layout.item_msg_text_left, null);
			} else {
				// 如果是自己发出的消息，则显示的是右气泡
				convertView = mInflater.inflate(R.layout.item_msg_text_right, null);
			}
			hv = new HoldView();
			hv.mSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
			hv.mChatContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
//			hv.mUsername = (TextView) convertView.findViewById(R.id.tv_username);
			hv.isComMsg = isComMsg;
			convertView.setTag(hv);
		} else {
			hv = (HoldView) convertView.getTag();
		}
		hv.mSendTime.setText(entity.getDate());
		hv.mChatContent.setText(entity.getText());
		if(isComMsg){
			hv.mChatContent.setTextColor(act.getResources().getColor(android.R.color.black));
		}else{
			hv.mChatContent.setTextColor(act.getResources().getColor(android.R.color.white));
		}
//		hv.mUsername.setText(entity.getName());
		return convertView;
	}

}
