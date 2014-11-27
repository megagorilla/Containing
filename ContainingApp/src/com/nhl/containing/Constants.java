package com.nhl.containing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class Constants {
	
	public static String getStorage(Context context, String key) {
		System.out.println("Constants.getStorage( " + key + " )");
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String value = sharedPreferences.getString(key, null);

		return value;
	}

	public static void setStorage(Context context, String key, String value) {
		System.out.println("Constants.setStorage( " + key + ", " + value + " )");
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
}