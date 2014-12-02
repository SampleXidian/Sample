package com.example.tool;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.service.MainService;

public class MyServiceConnection implements ServiceConnection{

	MainService myservice;
	
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		System.out.println("getService!!");
		myservice = ((MainService.mBinder) service).getService();
	}

	@Override
	public void onServiceDisconnected(ComponentName arg0) {
		myservice = null;
	}
	
	public MainService getService(){
		return myservice;
	}

}
