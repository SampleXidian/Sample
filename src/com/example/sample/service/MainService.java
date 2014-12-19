package com.example.sample.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Binder;
import android.os.IBinder;

import com.example.sample.tool.ChipList;
import com.example.sample.tool.Player;
import com.example.sample.tool.Recorder;

public class MainService extends Service {

	private MediaPlayer mPlayer;
	private MediaRecorder mRecorder;
	private Recorder recorder;
	private Player player;
	private ChipList chipList;

	@Override
	public IBinder onBind(Intent arg0) {
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
		System.out.println("starting...");
		super.onCreate();
		init();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private void init() {
		mPlayer = new MediaPlayer();
		mRecorder = new MediaRecorder();
		recorder = new Recorder(mRecorder, mPlayer);
		player = new Player(mPlayer);
	}

	public Recorder getRecorder() {
		return recorder;
	}

	public void test() {
		System.out.println("test!!!");
	}
}