package com.example.sample;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class Config {
	public static final String SERVER_URL = "";

	public static final String ACTION_SEND_VOICE = "send_voice";
	public static final String ACTION_CHECK_TOKEN = "check_token";
	public static final String ACTION_GET_LIST = "get_list";

	public static final String APP_ID = "com.example.sample";

	public static final String KEY_TOKEN = "token";
	public static final String KEY_PHONE_NUM = "phone";
	public static final String KEY_EMAIL = "email";

	public static String getCachedToken(Context context) {
		return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
				.getString(KEY_TOKEN, null);
	}

	public static void cacheToken(Context context, String token) {
		Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
				.edit();
		e.putString(KEY_TOKEN, token);
		e.commit();
	}

	public static String getCachedEmailAddress(Context context) {
		return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
				.getString(KEY_TOKEN, null);
	}

	public static void cacheEmailAddress(Context context, String token) {
		Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
				.edit();
		e.putString(KEY_TOKEN, token);
		e.commit();
	}

	// public static String getCachedPhoneNum(Context context) {
	// return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
	// .getString(KEY_PHONE_NUM, null);
	// }
	//
	// public static void cachePhoneNum(Context context, String phoneNum) {
	// Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE)
	// .edit();
	// e.putString(KEY_PHONE_NUM, phoneNum);
	// e.commit();
	// }

}
