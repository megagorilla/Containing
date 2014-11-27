package com.nhl.containing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class APIHandler {
	
	String _url;
	JSONObject _obj;
	
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
	public JSONArray getData() {
			
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
		JSONObject response = this.doPOST(_url, params);
		
		System.out.println("Output data:" + response);
		
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
	}
	
	/**
	 * This method posts the data to the server and returns the data from the server
	 * @param url The URL which the data is sent to
	 * @param params The parameters which are posted to the URL
	 * @return The data which the server returned
	 */
	public JSONObject doPOST(String url, ArrayList<NameValuePair> params) {
		AsyncTask output = new RetrieveFeedTask(this).execute();
		return this._obj;
	}
	
	public void setData(JSONObject obj) {
		this._obj = obj;
	}
	
	class RetrieveFeedTask extends AsyncTask<Void, Void, JSONObject> {
		
		JSONObject returnvalue;
		APIHandler _api;
		
		public RetrieveFeedTask(APIHandler api) {
			_api = api;
		}

	    protected void onPostExecute(JSONObject result) {
	    	this.returnvalue = result;
	    }

		@Override
		protected JSONObject doInBackground(Void... params) {
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://feenstraim.com/api.php");
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream is = entity.getContent();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String read = br.readLine();
					JSONObject output = new JSONObject(read);
					is.close();
					return output;
				} else {
					System.out.println("Network: Entity = null");
					return null;
				}
			} catch (Exception e) {
				Log.e("HTTP", "Error in http connection " + e);
				return null;
			}
		}
		
		public void getReturnValue() {
			_api.setData(returnvalue);
		}
	}
}