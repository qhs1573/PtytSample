package com.ptyt.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import com.ptyt.sample.utils.PrintLog;

public class GpsActivity extends BaseActivity implements LocationListener{
	private static final String TAG = "GpsActivity";
	 TextView tv1;
	 public static final String DCIM =Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
	 public static final String DCIM1 = Environment.getExternalStorageDirectory().getAbsolutePath();
	 
	 public static final String DCIM2 =Environment.getRootDirectory().toString();//获取手机根目录
	 public static final String DCIM3 =Environment.getExternalStorageDirectory().toString();//获取SD卡根目录
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps_activity);
		getmTextView().setText(R.string.string_jps_time);
		tv1 = (TextView) this.findViewById(R.id.tv_jps);
		
//		tv1.setText(DCIM2+"\r\n"+DCIM3);
		openGPSSettings();
		getLocation();
	
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
		   
	    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	    if(location!=null){
		    double latitude = location.getLatitude();     //经度   
		    double longitude = location.getLongitude(); //纬度   
		    double altitude =  location.getAltitude();     //海拔  
		    tv1.setText("latitude"+latitude+"longitude"+longitude+"altitude"+altitude);
		    PrintLog.v("tag", "latitude " + latitude + "  longitude:" + longitude + " altitude:" + altitude);
	    }
	
	}
	
	private void openGPSSettings() {
	        LocationManager alm = (LocationManager) this
	                .getSystemService(Context.LOCATION_SERVICE);
	        if (alm
	                .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
	            Toast.makeText(this, "GPS模块正常", Toast.LENGTH_SHORT).show();
	            return;
	        }

	        Toast.makeText(this, "请开启GPS！", Toast.LENGTH_SHORT).show();
	        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
	        startActivityForResult(intent,0); //此为设置完成后返回到获取界面

	    }
	
	private void getLocation()
    {
        // 获取位置管理服务
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) this.getSystemService(serviceName);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗

        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        Location location = locationManager.getLastKnownLocation(provider); // 通过GPS获取位置
        updateToNewLocation(location);
        // 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
        locationManager.requestLocationUpdates(provider, 100 * 1000, 500,this);    }
	

	private void updateToNewLocation(Location location) {

	        if (location != null) {
	            double  latitude = location.getLatitude();
	            double longitude= location.getLongitude();
	            tv1.setText("维度：" +  latitude+ "\n经度" + longitude);
	        } else {
	            tv1.setText("无法获取地理信息");
	        }

	    }

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		PrintLog.d(TAG, "onLocationChanged"+location);
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		PrintLog.d(TAG, "onProviderDisabled"+provider);
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		PrintLog.d(TAG, "onProviderEnabled"+provider);
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		PrintLog.d(TAG, "onStatusChanged"+provider+"status"+status+"extras"+extras);
	}
}
