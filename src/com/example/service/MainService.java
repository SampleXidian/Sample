package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Binder;
import android.os.IBinder;

import com.example.tool.Recorder;

public class MainService extends Service {

	private MediaPlayer mPlayer;
	private MediaRecorder mRecorder; 
	private Recorder recorder;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		System.out.println("binding...");
		return new mBinder(); 
	}

	public class mBinder extends Binder {
		public MainService getService() {
			return MainService.this;
		}
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		System.out.println("starting...");
		super.onCreate();
		init();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	private void init() {
		mPlayer = new MediaPlayer();
		mRecorder = new MediaRecorder();
		recorder = new Recorder(mRecorder,mPlayer);
		
	}
	
	public Recorder getRecorder(){
		return recorder;
	}
	
	public void test(){
		System.out.println("test!!!");
	}
}