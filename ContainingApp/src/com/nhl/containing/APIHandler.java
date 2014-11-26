package com.nhl.containing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class APIHandler {
	
	String _url;
	
	public APIHandler(String url) {
		this._url = url;
	}
	
	/**
	 * This method tries to connect to the server
	 * @return A boolean, whether the connect has succeeded or not
	 */
	public boolean connect() {
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		JSONObject response = this.doPOST(_url, params);
		
		if (response != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method fetches data from the server
	 * The data must be in JSON-format
	 * @return A JSONArray containing the data that was fetched
	 */
	@SuppressWarnings("unchecked")
	public JSONArray getData() {
		ArrayList<NameValuePair> mStringArray = new ArrayList<NameValuePair>();
		mStringArray.add(new BasicNameValuePair("1", "100"));
		mStringArray.add(new BasicNameValuePair("2", "80"));
		mStringArray.add(new BasicNameValuePair("3", "50"));
		mStringArray.add(new BasicNameValuePair("4", "60"));
		mStringArray.add(new BasicNameValuePair("5", "20"));
		mStringArray.add(new BasicNameValuePair("6", "10"));
		
		JSONArray mJSONArray = new JSONArray(Arrays.asList(mStringArray));
		
		return mJSONArray;
		/*
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		JSONObject response = this.doPOST(_url, params);
		
		if (response != null) {
			JSONArray data;
			try {
				data = response.getJSONArray("data");
				
				return data;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	
		return null;
		*/
	}
	
	/**
	 * This method posts the data to the server and returns the data from the server
	 * @param url The URL which the data is sent to
	 * @param params The parameters which are posted to the URL
	 * @return The data the server outputted
	 */
	public JSONObject doPOST(String url, ArrayList<NameValuePair> params) {
		JSONObject output;

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream is = entity.getContent();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String read = br.readLine();
				output = new JSONObject(read);
				is.close();
				return output;
			} else {
				return null;
			}
		} catch (Exception e) {
			Log.e("HTTP", "Error in http connection " + e);
			return null;
		}
	}
}