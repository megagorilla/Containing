package com.nhl.containing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;

public class MainActivity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	
	GraphView mChart;
	
	private Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		    
	    handler = new Handler();
	    handler.postDelayed(runnable, 5000);

		// Set the chart to default
		LineChart lc = (LineChart) findViewById(R.id.chart);
		mChart = new GraphView(lc, this);
		
		lc = mChart.getChart();
	}
	
	private Runnable runnable = new Runnable() {
	   @Override
	   public void run() {
	      /* do what you need to do */
	      new RetrieveDataTask().execute("");
	      mChart.update();
	      /* and here comes the "trick" */
	      handler.postDelayed(this, 5000);
	   }
	};
	
	/**
	 * This method updates the Chart when an item from the
	 * Navigation Drawer is selected
	 * @param id the ID of the panel to show
	 */

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
		.beginTransaction()
		.replace(R.id.container,
				PlaceholderFragment.newInstance(position + 1)).commit();
		
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			mChart.setData(1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			mChart.setData(2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			mChart.setData(3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			mChart.setData(4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			mChart.setData(5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			mChart.setData(6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			mChart.setData(7);
			break;
		}
	}

	@SuppressWarnings("deprecation")
	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
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
			System.out.println("onPostExecute: "+result);
			if (result != null) {
				try {
					Constants.setStorage(MainActivity.this, "Train", result.getJSONArray("data").getString(0));
					Constants.setStorage(MainActivity.this, "Truck", result.getJSONArray("data").getString(1));
					Constants.setStorage(MainActivity.this, "Storage", result.getJSONArray("data").getString(2));
					Constants.setStorage(MainActivity.this, "Ship", result.getJSONArray("data").getString(3));
					Constants.setStorage(MainActivity.this, "Others", result.getJSONArray("data").getString(4));
					Constants.setStorage(MainActivity.this, "Seaship", result.getJSONArray("data").getString(5));
					
					//GraphView.update();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
	    }
	}
}