package com.nhl.containing;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class Constants {
	
	/**
	 * Gets a string from the Storage
	 * @param context A context to use for connecting
	 * @param key The key of the storage
	 * @return The String
	 */
	public static String getStorage(Context context, String key) {
		System.out.println("Constants.getStorage( " + key + " )");
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String value = sharedPreferences.getString(key, null);

		return value;
	}

	/**
	 * Stores a value in the main storage
	 * @param context A context to use for connecting
	 * @param key The key in which the data should be stored
	 * @param value The value to store
	 */
	public static void setStorage(Context context, String key, String value) {
		System.out.println("Constants.setStorage( " + key + ", " + value + " )");
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
}