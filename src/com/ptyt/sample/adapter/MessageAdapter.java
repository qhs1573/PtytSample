package com.ptyt.sample.adapter;

import java.util.List;

import android.app.Activity;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ptyt.sample.activity.R;

public class MessageAdapter extends BaseAdapter {
	private Activity act;
	private List mList;
	private LayoutInflater mInflater;
	public MessageAdapter(Activity act,List list){
		this.act = act;
		this.mList = list;
		mInflater = LayoutInflater.from(act);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList!=null?mList.size():0;
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
		TextView mUsername;
		TextView mTime;
		TextView mMsgContent;
	} 

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HoldView hv;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_message_list, null);
			hv = new HoldView();
			hv.mUsername = (TextView) convertView.findViewById(R.id.item_message_list_tv_username);
			hv.mTime = (TextView) convertView.findViewById(R.id.item_message_list_tv_time);
			hv.mMsgContent = (TextView) convertView.findViewById(R.id.item_message_list_tv_msgcontent);
			convertView.setTag(hv);
		} else {
			hv = (HoldView) convertView.getTag();
		}
		hv.mUsername.setText("李连杰");
		int flags = DateUtils.FORMAT_ABBREV_RELATIVE;
		hv.mTime.setText(DateUtils.getRelativeTimeSpanString(System.currentTimeMillis()-(1000*60*60*24)*2,System.currentTimeMillis(),DateUtils.MINUTE_IN_MILLIS, flags));
		
		
//		hv.mTime.setText("昨天");
		hv.mMsgContent.setText("公车中，一位站着的孕妇对她身旁坐着的男子说：你不知道我怀孕了吗？只见男子很紧张地说：“孩子不是我的！！");
		return convertView;
	}

}
