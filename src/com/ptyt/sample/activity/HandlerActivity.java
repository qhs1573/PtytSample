package com.ptyt.sample.activity;

import com.ptyt.sample.utils.PrintLog;
import com.ptyt.sample.utils.ToastUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
/**
 * 
 * @author ygc
 *���Է�������Ϣ��ᱨsending message to a Handler on a dead thread�쳣������
 */
public class HandlerActivity extends BaseActivity {
	private static final String TAG = "HandlerActivity";
	private MyHandler handler;
	private MyThread myThread;
	private boolean isStop = true;
	private int position = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler_activity);
		getmTextView().setText(R.string.string_handler_remover_title);
	}

	public void stopHandler(View v) {
		isStop = false;
		if (handler != null) {
			handler.removeMessages(position);
		}
		if (myThread != null) {
			myThread.quit();
		}
		//��ֵ���ֻ����handler.removeMessages(position);���ᱨ�쳣���ǵ���handler.removeMessages(position); handler.getLooper().quit();�ͻ��sending message to a Handler on a dead thread���쳣
		//�����������������쳣����ԭ��û������������looper����һ�µ��µ�
		PrintLog.d(TAG, "hand message::::::"+myThread);
		/*
		 *���ַ�ʽ�˳��ᱨ�쳣 
		 * handler.removeMessages(position); handler.getLooper().quit();
		 */

	}

	public void startHandler(View v) {
		myThread = new MyThread("myThread");
		myThread.start();
		isStop = true;
		new Thread() {
			public void run() {
				while (isStop) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					position++;
					if (handler != null) {
						handler.sendEmptyMessage(position);
					}
				}
			}
		}.start();
	}
	
	public void sendMessage(View view){
		handler.sendEmptyMessage(0);
	}

	class MyThread extends Thread {

		public MyThread(String threadName) {
			super(threadName);
		}

		@Override
		public void run() {
			try {
				Looper.prepare();
				handler = new MyHandler();
				Looper.loop();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				ToastUtil.getToast().showMessage(HandlerActivity.this, "finally", -1);
			}
		}

		public void quit() {
			if (handler != null) {
				handler.getLooper().quit();
				handler = null;
//				myThread=null;
			}
		}
	};

	class MyHandler extends Handler {
		public MyHandler() {
			super();
		}

		public MyHandler(Looper looper) {
			super(looper);
		}

		public Looper getooper() {
			return getLooper();
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			PrintLog.d(TAG, "hand message------>>>" + msg.what);
		}
	}
}
