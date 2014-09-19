package com.ptyt.sample.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ptyt.sample.activity.R;

public class BaseDialog extends Dialog {
	private LinearLayout mRootView;
	private Context context;
	public BaseDialog(Context context) {
		super(context,R.style.custom_dialog_style);
		this.context = context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_base);
		mRootView = (LinearLayout) findViewById(R.id.ll_rootview);
	}
	
	public View addView(int layoutResId) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(layoutResId, null);
		mRootView.removeAllViews();
		mRootView.addView(v);
		return v;
	}
	

}
