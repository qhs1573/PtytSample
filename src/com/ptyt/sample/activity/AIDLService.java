package com.ptyt.sample.activity;

import com.ptyt.sample.aidl.ForActivity;
import com.ptyt.sample.aidl.ForService;
import com.ptyt.sample.utils.PrintLog;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AIDLService extends Service {
	private static final String TAG = AIDLService.class.getSimpleName();
	private ForActivity foActivity;
	@Override
	public void onCreate() {
		super.onCreate();
		PrintLog.d(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		PrintLog.d(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		PrintLog.d(TAG, "onDestroy");
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		PrintLog.d(TAG, "onBind");
		return forService;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		PrintLog.d(TAG, "onUnbind");
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		PrintLog.d(TAG, "onRebind");
		super.onRebind(intent);
	}
	
	private final ForService.Stub forService = new ForService.Stub() {
		
		@Override
		public void registerTestCall(ForActivity cb) throws RemoteException {
			foActivity = cb;
			PrintLog.d(TAG, "registerTestCall");
		}
		
		@Override
		public void invokCallBack() throws RemoteException {
			PrintLog.d(TAG, "invokCallBack");
			foActivity.performAction();
		}
	};
}
