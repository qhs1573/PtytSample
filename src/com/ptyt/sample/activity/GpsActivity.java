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
	 
	 public static final String DCIM2 =Environment.getRootDirectory().toString();//��ȡ�ֻ���Ŀ¼
	 public static final String DCIM3 =Environment.getExternalStorageDirectory().toString();//��ȡSD����Ŀ¼
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
		    double latitude = location.getLatitude();     //����   
		    double longitude = location.getLongitude(); //γ��   
		    double altitude =  location.getAltitude();     //����  
		    tv1.setText("latitude"+latitude+"longitude"+longitude+"altitude"+altitude);
		    PrintLog.v("tag", "latitude " + latitude + "  longitude:" + longitude + " altitude:" + altitude);
	    }
	
	}
	
	private void openGPSSettings() {
	        LocationManager alm = (LocationManager) this
	                .getSystemService(Context.LOCATION_SERVICE);
	        if (alm
	                .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
	            Toast.makeText(this, "GPSģ������", Toast.LENGTH_SHORT).show();
	            return;
	        }

	        Toast.makeText(this, "�뿪��GPS��", Toast.LENGTH_SHORT).show();
	        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
	        startActivityForResult(intent,0); //��Ϊ������ɺ󷵻ص���ȡ����

	    }
	
	private void getLocation()
    {
        // ��ȡλ�ù������
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) this.getSystemService(serviceName);
        // ���ҵ�������Ϣ
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // �߾���
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // �͹���

        String provider = locationManager.getBestProvider(criteria, true); // ��ȡGPS��Ϣ
        Location location = locationManager.getLastKnownLocation(provider); // ͨ��GPS��ȡλ��
        updateToNewLocation(location);
        // ���ü��������Զ����µ���Сʱ��Ϊ���N��(1��Ϊ1*1000������д��ҪΪ�˷���)����Сλ�Ʊ仯����N��
        locationManager.requestLocationUpdates(provider, 100 * 1000, 500,this);    }
	

	private void updateToNewLocation(Location location) {

	        if (location != null) {
	            double  latitude = location.getLatitude();
	            double longitude= location.getLongitude();
	            tv1.setText("ά�ȣ�" +  latitude+ "\n����" + longitude);
	        } else {
	            tv1.setText("�޷���ȡ������Ϣ");
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
