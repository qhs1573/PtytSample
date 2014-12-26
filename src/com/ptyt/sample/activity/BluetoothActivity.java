package com.ptyt.sample.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;


public class BluetoothActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		LinearLayout mLayout = new LinearLayout(this);
		Button btn = new Button(this);
		btn.setText("Button Button Button Button Button Button ");
		mLayout.addView(btn);
		setContentView(mLayout);
		btn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new BluetoothHFPProxy(BluetoothActivity.this).onBluetoothHFPsend();
			}
		});
	}
}
