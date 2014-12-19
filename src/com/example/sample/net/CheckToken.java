package com.example.sample.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.sample.Config;
import com.example.sample.net.MyHttpConnection.FailCallback;
import com.example.sample.net.MyHttpConnection.SuccessCallback;

public class CheckToken {
	private String email;
	private String token;
	private MyHttpConnection con;
	public boolean isOutOfDate;

	public CheckToken(String email, String token) {
		this.email = email;
		this.token = token;
		isOutOfDate = true;
		init();
	}

	private void init(){
		con = new MyHttpConnection(Config.SERVER_URL, null, HttpMethod.GET,
				Config.ACTION_CHECK_TOKEN, Config.KEY_EMAIL, email,
				Config.KEY_TOKEN, token);
		con.setSuccessCallback(new SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				try {
					JSONObject obj = new JSONObject(result);
					
					//处理返回信息
					
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		con.setFailCallback(new FailCallback() {
			
			@Override
			public void onFail() {
				isOutOfDate = true;
				
				//请求出现问题时操作
			}
		});
	}
	
	public void check() {
		con.execute();
	}
}
