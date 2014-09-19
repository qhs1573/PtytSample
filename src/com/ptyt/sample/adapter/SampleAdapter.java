package com.ptyt.sample.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.CursorJoiner.Result;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ptyt.sample.activity.R;
import com.ptyt.sample.bean.SampleBean;

public class SampleAdapter extends BaseAdapter {
	private Activity act;
	private List<SampleBean> mList = new ArrayList<SampleBean>();
	private int [] data=new int[]{
			R.array.sample_test,
			R.array.sample_bubblechart,
			R.array.sample_message,
			R.array.sample_date,
			R.array.sample_listView,
			R.array.sample_get_jpsinfo,
			R.array.sample_remover_handler,
			R.array.sample_listviewselect_info,
			R.array.sample_aidl_info
	};
	
	public SampleAdapter(Activity act){
		this.act = act;
		new LoadSampleList().execute();
	}
	
	private class LoadSampleList extends AsyncTask<Object, Integer, Result>{

		@Override
		protected Result doInBackground(Object... params) {
			for(int i=0;i<data.length;i++){
				String[] strItem = act.getResources().getStringArray(data[i]);
					SampleBean sampleBean=new SampleBean();
					sampleBean.setSampleTitle(strItem[0]);
					sampleBean.setSampleDateTime(strItem[1]);
					sampleBean.setSampleDescription(strItem[2]);
					mList.add(sampleBean);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Result result) {
			super.onPostExecute(result);
			notifyDataSetChanged();
		}
	}
	
	@Override
	public int getCount() {
		return mList!=null?mList.size():0;
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	class HoldView{
		TextView sampleTitle;
		TextView sampleDateTime;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SampleBean sampleBean = mList.get(position);
		HoldView hv;
		if(convertView == null){
			convertView = LayoutInflater.from(act).inflate(R.layout.item_samplelist,null);
			hv = new HoldView();
			hv.sampleTitle = (TextView) convertView.findViewById(R.id.tv_sample_title);
			hv.sampleDateTime = (TextView) convertView.findViewById(R.id.tv_sample_datetime);
			convertView.setTag(hv);
		}else{
			hv = (HoldView) convertView.getTag();
		}
		hv.sampleTitle.setText(sampleBean.getSampleTitle());
		hv.sampleDateTime.setText(sampleBean.getSampleDateTime());
		return convertView;
	}

}
