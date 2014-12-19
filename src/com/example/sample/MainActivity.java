package com.example.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.sample.activity.LoginActivity;
import com.example.sample.activity.TimelineActivity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String token = Config.getCachedToken(this);
		String email = Config.getCachedEmailAddress(this);
		
		if (token!=null&&email!=null) {
			Intent i =new Intent(this, TimelineActivity.class);
			i.putExtra(Config.KEY_TOKEN, token);
			i.putExtra(Config.KEY_EMAIL, email);
			startActivity(i);
		}else{
			startActivity(new Intent(this, LoginActivity.class));
		}
		
		finish();
	}
	
	
}
