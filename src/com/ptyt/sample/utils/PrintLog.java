package com.ptyt.sample.utils;


import android.util.Log;

/**��־����
 * [ͳһ������־���������ּ������־]
 */
public class PrintLog {
	private static final String TAG = "SyncTeaching";
	//��ӡ��־�Ŀ���
	private static boolean LOG_VERBOSE = true;
	private static boolean LOG_DEBUG = true;
	private static boolean LOG_INFO = true;
	private static boolean LOG_WARN = true;
	private static boolean LOG_ERROR = true;
	
    /**
     * ��ӡverbose�������־
     * 
     * @param tag ģ���ӡ���
     * @param msg ��־����
     */
	public static void v(String tag, String msg) {
		if (LOG_VERBOSE) {
			Log.v(TAG, "[" + tag + "] " + msg);
		}
	}

    /**
     * ��ӡverbose�������־
     * 
     * @param tag ģ���ӡ���
     * @param msg ��־����
     * @param tr ��ӡ�쳣����־
     */
	public static void v(String tag, String msg, Throwable tr) {
		if (LOG_VERBOSE) {
			Log.v(TAG, "[" + tag + "] " + msg, tr);
		}
	}
	
    /**
     * ��ӡdebug�������־
     * 
     * @param tag ģ���ӡ���
     * @param msg ��־����
     */
	public static void d(String tag, String msg) {
		if (LOG_DEBUG) {
			Log.d(TAG, "[" + tag + "] " + msg);
		}
	}

    /**
     * ��ӡdebug�������־
     * 
     * @param tag ģ���ӡ���
     * @param msg ��־����
     * @param tr ��ӡ�쳣����־
     */
	public static void d(String tag, String msg, Throwable tr) {
		if (LOG_DEBUG) {
			Log.d(TAG, "[" + tag + "] " + msg, tr);
		}
	}
    

    /**
     * ��ӡinfo�������־
     * 
     * @param tag ģ���ӡ���
     * @param msg ��־����
     */
	public static void i(String tag, String msg) {
		if (LOG_INFO) {
			Log.i(TAG, "[" + tag + "] " + msg);
		}
	}

    /**
     * ��ӡinfo�������־
     * 
     * @param tag ģ���ӡ���
     * @param msg ��־����
     * @param tr ��ӡ�쳣����־
     */
	public static void i(String tag, String msg, Throwable tr) {
		if (LOG_INFO) {
			Log.i(TAG, "[" + tag + "] " + msg, tr);
		}
	}

    /**
     * ��ӡwarning�������־
     * 
     * @param tag ģ���ӡ���
     * @param msg ��־����
     */
	public static void w(String tag, String msg) {
		if (LOG_WARN) {
			Log.w(TAG, "[" + tag + "] " + msg);
		}
	}

    /**
     * ��ӡwarning�������־
     * 
     * @param tag ģ���ӡ���
     * @param msg ��־����
     * @param tr ��ӡ�쳣����־
     */
	public static void w(String tag, String msg, Throwable tr) {
		if (LOG_WARN) {
			Log.w(TAG, "[" + tag + "] " + msg, tr);
		}
	}

    /**
     * ��ӡerror�������־
     * 
     * @param tag ģ���ӡ���
     * @param msg ��־����
     */
	public static void e(String tag, String msg) {
		if (LOG_ERROR) {
			Log.e(TAG, "[" + tag + "] " + msg);
		}
	}

    /**
     * ��ӡerror�������־
     * 
     * @param tag ģ���ӡ���
     * @param msg ��־����
     * @param tr ��ӡ�쳣����־
     */
	public static void e(String tag, String msg, Throwable tr) {
		if (LOG_ERROR) {
			Log.e(TAG, "[" + tag + "] " + msg, tr);
		}
	}

}