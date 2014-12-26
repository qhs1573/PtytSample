package com.ptyt.sample.activity;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;

public class BluetoothHFPProxy
{
	private Context mContext;
	private BluetoothSocket mSocket;
	private BluetoothDevice mBluetoothDevice;
	OutputStream mOutputStream = null;
	private UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private BluetoothAdapter adapter;

	public BluetoothHFPProxy ( Context context )
	{
		mContext = context;
	}

	public void onBluetoothHFPsend()
	{
		if ( mSocket == null )
		{
			try
			{
				adapter = BluetoothAdapter.getDefaultAdapter();
				Set<BluetoothDevice> sets = adapter.getBondedDevices();
				Iterator<BluetoothDevice> iterator = sets.iterator();
				while (iterator.hasNext())
				{
					BluetoothDevice device = iterator.next();
					//if(mUtils.isConnected(device))
						mBluetoothDevice = device;
						mSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(mUUID);
						if ( mSocket == null )
						{
							continue;
						}
				}
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try
		{
			if ( !mSocket.isConnected()){
				mSocket.connect();
			}
			String str = adapter.getName();
			System.out.println(str);
			mOutputStream = mSocket.getOutputStream();
			mOutputStream.write(1);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if ( mOutputStream != null )
				{
					mOutputStream.flush();
					mOutputStream.close();
					mOutputStream = null;
				}
				if ( mSocket != null )
				{
					mSocket.close();
//					mSocket = null;
				}
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
