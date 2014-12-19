package com.example.sample.net;

import com.example.sample.Config;
import com.example.sample.net.MyHttpConnection.FailCallback;
import com.example.sample.net.MyHttpConnection.SuccessCallback;
import com.example.sample.tool.ChipList;
import com.example.sample.tool.Recorder;

public class GetChipList {
	private ChipList chipList;
	private MyHttpConnection con;

	public GetChipList() {
		init();
	}
	private void init(){
		chipList = new ChipList();
		if (Recorder.LOCAL_PATH != null){
			con = new MyHttpConnection(Config.SERVER_URL, Recorder.LOCAL_PATH,
					HttpMethod.POST, Config.ACTION_GET_LIST);
			con.setSuccessCallback(new SuccessCallback() {
				@Override
				public void onSuccess(String result) {
					
				}
			});
			con.setFailCallback(new FailCallback() {
				
				@Override
				public void onFail() {
					
				}
			});
		}
		//如果recorder不存在。
		else
			;
	}

	public ChipList getList() {
		
		return chipList;
	}
}
