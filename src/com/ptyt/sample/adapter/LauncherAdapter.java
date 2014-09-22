package com.ptyt.sample.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptyt.sample.activity.R;

public class LauncherAdapter extends BaseAdapter {
	private Context context;
	private List<String> dataList;

	public LauncherAdapter(Context context,List list) {
		this.context = context;
		this.dataList = list;
	}

	@Override
	public int getCount() {
		return dataList==null?0:dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(R.layout.item_launcher, null);
		ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_app_logo);
		TextView mTextView = (TextView) convertView.findViewById(R.id.tv_app_name);
		mTextView.setText(dataList.get(position));
		return convertView;
	}

}
