package com.ptyt.sample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ptyt.sample.activity.R;

public class ListViewSelect extends BaseAdapter {
	private Context mContext;
	private LinearLayout mRootView;
	private ImageView mUserIcon;
	private TextView mUserName;
	private TextView mPhoneNumber;
	private ImageView mContactMenu;
	public ListViewSelect(Context context) {
		this.mContext = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 8;
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(mContext).inflate(R.layout.item_contact_list,null);
		mRootView = (LinearLayout) convertView.findViewById(R.id.ll_contact_root_view);
		mUserIcon = (ImageView) convertView.findViewById(R.id.tv_contact_user_icon);
		mUserName = (TextView) convertView.findViewById(R.id.tv_contact_username);
		mPhoneNumber = (TextView) convertView.findViewById(R.id.tv_contact_phone_number);
		mContactMenu = (ImageView) convertView.findViewById(R.id.iv_btn_menu);
		mRootView.setSelected(true);
		mRootView.setPressed(true);
		return convertView;
	}

}
