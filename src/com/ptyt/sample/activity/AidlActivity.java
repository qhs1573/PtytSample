package com.ptyt.sample.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import com.ptyt.sample.aidl.ForActivity;
import com.ptyt.sample.aidl.ForService;
import com.ptyt.sample.annotation.ViewInject;
import com.ptyt.sample.utils.ToastUtil;

public class AidlActivity extends BaseActivity {
	private ForService mService;
	@ViewInject(id=R.id.btn_mok,click="onClickOk")
	private Button mOk;
	@ViewInject(id=R.id.btn_mcancel,click="onClickCancel")
	private Button mCancel;
	@ViewInject(id=R.id.btn_mcall_back,click="onClickCallBack")
	private Button mCallBack;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aidl_activity);
		getmTextView().setText(R.string.string_aidl_title);
	}

	private final ForActivity.Stub forActivity = new ForActivity.Stub() {

		@Override
		public void performAction() throws RemoteException {
			ToastUtil.getToast().showMessage(AidlActivity.this,"performAction", -1);
		}
	};
	
	public void onClickOk(View v){
		Bundle args = new Bundle();  
        Intent intent = new Intent(AidlActivity.this, AIDLService.class);  
        intent.putExtras(args);  
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);  
        startService(intent);  
	}
	
	public void onClickCancel(View v){
		unbindService(mConnection);
	}
	
	public void onClickCallBack(View v){
		try {
			mService.invokCallBack();
		} catch (RemoteException e) {
			e.printStackTrace();
		}  
	}
	
	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			mService = ForService.Stub.asInterface(service);
			try {
				mService.registerTestCall(forActivity);
			} catch (RemoteException e) {

			}
		}

		public void onServiceDisconnected(ComponentName className) {
			mService = null;
		}
	};
}
