package com.ptyt.sample.activity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;

import com.ptyt.sample.annotation.ViewInject;

public class MemInfoActivity  extends BaseActivity{
	@ViewInject(id=R.id.tv_shengyu_memory)
	private TextView shengyuMrmory;
	@ViewInject(id=R.id.tv_total_memory)
	private TextView totalMemroy;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mem_info);
		shengyuMrmory.setText("可用内存"+getAvailMemory());
		totalMemroy.setText("总内存"+getTotalMemory());
	}
	
	 private String getAvailMemory(){  
	        //获取android当前可用内存大小  
	        ActivityManager am=(ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);  
	        MemoryInfo mi=new MemoryInfo();  
	        am.getMemoryInfo(mi);  
	        //mi.avaiMem;当前系统可用内存  
	        return Formatter.formatFileSize(getBaseContext(), mi.availMem);  
	        //将获得的内存大小规格化  
	          
	    }  
	      
	    private String getTotalMemory(){  
	        String str1="/proc/meminfo";//系统内存信息文件  
	        String str2;  
	        String[] arrayOfString;  
	        long initial_memory=0;  
	          
	        try{  
	            FileReader localFileReader=new FileReader(str1);  
	            BufferedReader localBufferedReader=new BufferedReader(localFileReader,8192);  
	            str2=localBufferedReader.readLine();//读取meminfo第一行，系统内存大小  
	            arrayOfString=str2.split("\\s+");  
	            for(String num:arrayOfString){  
	                Log.i(str2,num+"\t");  
	            }  
	            initial_memory=Integer.valueOf(arrayOfString[1]).intValue()*1024;//获得系统总内存，单位KB  
	            localBufferedReader.close();  
	        }catch(IOException e){  
	              
	        }  
	        return Formatter.formatFileSize(getBaseContext(), initial_memory);  
	        //Byte转位KB或MB  
	          
	    }  
}