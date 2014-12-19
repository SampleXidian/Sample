package com.example.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.sample.service.MainService;
import com.example.sample.tool.MyServiceConnection;
import com.example.sample.tool.Recorder;

public class TestActivity extends Activity implements OnClickListener {

	MainService myservice;
	Recorder recorder;
	Button start, stop, play;
	MyServiceConnection conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		conn = new MyServiceConnection();
		Intent i = new Intent(TestActivity.this, MainService.class);
		startService(i);
		bindService(new Intent(TestActivity.this, MainService.class), conn,
				Context.BIND_AUTO_CREATE);

		start = (Button) findViewById(R.id.startrecord);
		stop = (Button) findViewById(R.id.stoprecord);
		play = (Button) findViewById(R.id.startplay);
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		play.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(conn);
	}

	@Override
	public void onClick(View view) {
		if (myservice == null)
			myservice = conn.getService();
		if (recorder == null)
			recorder = myservice.getRecorder();
		switch (view.getId()) {
		case R.id.startrecord:
			recorder.startRecord();
			break;
		case R.id.stoprecord:
			recorder.stopRecord();
			break;
		case R.id.startplay:
			recorder.startPlay();
			break;
		}

	}

}
