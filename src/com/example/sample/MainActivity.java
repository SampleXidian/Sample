package com.example.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.service.MainService;
import com.example.tool.MyServiceConnection;
import com.example.tool.Recorder;

public class MainActivity extends Activity implements OnClickListener {

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
		Intent i = new Intent(MainActivity.this, MainService.class);
		startService(i);
		bindService(new Intent(MainActivity.this, MainService.class), conn,
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
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(conn);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
