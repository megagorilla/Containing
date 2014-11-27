package com.nhl.containing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class DataGrabber {
	
	public static Context _view;
	private String url;
	
	public DataGrabber(String _url, Context view) {
		this.url = _url;
		this._view = view;
	}
	
	class RetrieveDataTask extends AsyncTask<String, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(String... params) {
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
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}

		protected void onPostExecute(JSONObject result) {
			if (result != null) {
				try {
					Constants.setStorage(DataGrabber._view, "Train", result.getString("Train"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
	    }
	}
}