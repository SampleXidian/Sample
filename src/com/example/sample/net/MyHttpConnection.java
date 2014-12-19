package com.example.sample.net;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

import com.example.sample.Config;
import com.example.sample.tool.Recorder;

public class MyHttpConnection {
	private HttpClient client;
	private MultipartEntity entity;
	private HttpPost post;
	private HttpGet get;
	private String localPath;
	private String uploadUrl;
	private String[] info;
	private String action;
	private SuccessCallback successCallback;
	private FailCallback failCallback;
	private HttpMethod method;
	private HttpRequestThread request;

	/*
	 * 1、上传文件 2、上传请求（非文件）
	 */
	public MyHttpConnection(String serverUrl, String localPath,
			HttpMethod method, String action, String... kvs) {
		info = kvs;
		this.action = action;
		if (serverUrl != null)
			uploadUrl = serverUrl;
		if (localPath != null)
			this.localPath = localPath;
		this.method = method;
		init();
		request = new HttpRequestThread();
	}

	private void init() {
		if (uploadUrl == null)
			uploadUrl = Config.SERVER_URL;
		if (Recorder.LOCAL_PATH != null && localPath == null)
			localPath = Recorder.LOCAL_PATH;
		entity = new MultipartEntity();
	}

	public void execute() {
		request.execute();
	}

	public void setSuccessCallback(SuccessCallback sc) {
		successCallback = sc;
	}

	public void setFailCallback(FailCallback fc) {
		failCallback = fc;
	}

	private class HttpRequestThread extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... arg0) {
			String result = null;
			if (method == HttpMethod.GET) {
				String body = "?action=" + action;

				for (int i = 0; i < info.length; i += 2) {
					body += info[i] + "=" + info[i + 1] + "&";
				}
				get = new HttpGet(uploadUrl + body);

				HttpResponse response;
				try {
					response = client.execute(get);
					HttpEntity resEntity = response.getEntity();
					result = EntityUtils.toString(resEntity, "utf-8");
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result;

			} else {

				post = new HttpPost(uploadUrl);
				if (action.equals(Config.ACTION_SEND_VOICE)) {
					File file = new File(localPath);

					// 如果文件不存在
					if (!file.exists())
						;
					ContentBody cbFile = new FileBody(file);
					entity.addPart("user_voice", cbFile);
				}

				try {
					entity.addPart(
							"action",
							new StringBody(
									action,
									Charset.forName(org.apache.http.protocol.HTTP.UTF_8)));

					for (int i = 0; i < info.length; i += 2) {
						entity.addPart(
								info[i],
								new StringBody(
										info[i + 1],
										Charset.forName(org.apache.http.protocol.HTTP.UTF_8)));
					}
					post.setEntity(entity);

					HttpResponse response = client.execute(post);
					HttpEntity resEntity = response.getEntity();
					result = EntityUtils.toString(resEntity, "utf-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return result;
			}

		}

		@Override
		protected void onPostExecute(String result) {

			if (result != null) {
				if (successCallback != null) {
					successCallback.onSuccess(result);
				}
			} else {
				if (failCallback != null) {
					failCallback.onFail();
				}
			}

			super.onPostExecute(result);
		}
	}

	public static interface SuccessCallback {
		void onSuccess(String result);
	}

	public static interface FailCallback {
		void onFail();
	}
}
