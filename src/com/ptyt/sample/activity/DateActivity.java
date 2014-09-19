package com.ptyt.sample.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.style.LeadingMarginSpan;
import android.util.Log;
import android.widget.TextView;

import com.ptyt.sample.annotation.ViewInject;
import com.ptyt.sample.utils.PrintLog;

public class DateActivity extends BaseActivity {
	private static final String TAG = "DateActivity";
	@ViewInject(id = R.id.tv_show_formatdate)
	private TextView mShowDate;
	private String time = " ";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.date_activity);
		PrintLog.d(TAG, "---->>>"+getSystemTime(this));
		format();
	}

	public String test() {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}
	
	public String formatKa(String c){
		String format = "";
		if(TextUtils.isGraphic(c)){
			return "";
		}
		int size=4;
		int length = c.length();
		int len=0;
		if(length%size == 0){
			len=length/size;
		}else{
			len = (length/size)+1;
		}
		if(length<=4){
			return c;
		}
		for(int i=0;i<len;){
			String tmp= c.substring(i, i+size);
			if(i<size){
				format = tmp;
			}else{
				tmp+=tmp+" ";
				format+=tmp;
			}
			i=i+size;
		}
		return format;
	}

	public void format() {
		addTime(format(DateUtils.MINUTE_IN_MILLIS,
				DateUtils.FORMAT_ABBREV_RELATIVE));
		addTime(format(DateUtils.HOUR_IN_MILLIS, DateUtils.FORMAT_ABBREV_TIME));
		addTime(format(DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_ABBREV_WEEKDAY));
		addTime(format(DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_ALL));
		addTime(test());
	}

	public String format(long l, int flg) {
		return DateUtils.getRelativeTimeSpanString(
				System.currentTimeMillis() - (1000 * 60 * 60 * 24) * 2,
				System.currentTimeMillis(), l, flg).toString();
	}

	public void addTime(String times) {
		StringBuffer sBuffer = new StringBuffer(time);
		sBuffer.append("\r\n");
		sBuffer.append(times);
		time = sBuffer.toString();
		mShowDate.setText(time);
	}
	
	public String str(){
		System.currentTimeMillis();
		return null;
	}
	
	public long getLongTime(int day){
		long dayTime = 1000*60*60*24;
		return day*dayTime;
	}
	
	public static String getSystemTime(Context context){
		String time=null;
	    ContentResolver cv = context.getContentResolver();
	    String strTimeFormat = android.provider.Settings.System.getString(cv, android.provider.Settings.System.TIME_12_24);
	        if(strTimeFormat!=null&&strTimeFormat.length()>0){
	        	Log.i("activity",strTimeFormat);
	        	time=strTimeFormat;
	        	return time;
	        }
			return strTimeFormat;
	}

}
