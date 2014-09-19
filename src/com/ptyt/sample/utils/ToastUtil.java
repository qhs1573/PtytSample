package com.ptyt.sample.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtil {
	/**
	 * 提示公共�?
	 */
	private static ToastUtil toastUtil = null;
	private static Toast mToast;

	/**
	 * @return ToastUtil
	 */
	public static ToastUtil getToast() {
		if (null == toastUtil) {
			toastUtil = new ToastUtil();
		}
		return toastUtil;
	}

	public synchronized void showMessage(final Activity act, final String str,
			final int strId) {
		act.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mToast == null) {
					mToast = Toast.makeText(act, "", Toast.LENGTH_SHORT);
				}
				if (strId == -1) {
					mToast.setText(str);
				} else {
					mToast.setText(strId);
				}
				mToast.show();
			}
		});
	}

	public synchronized void showMessage(final Context act, final String str,
			final int strId) {
				if (mToast == null) {
					mToast = Toast.makeText(act,"", Toast.LENGTH_SHORT);
				}
				if (strId == -1) {
					mToast.setText(str);
				} else {
					mToast.setText(strId);
				}
				mToast.show();
			}
}
