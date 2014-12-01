package com.nhl.containing;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends ActionBarActivity {
	
	private Button connectButton;
	private EditText connectInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        connectButton = (Button) findViewById(R.id.button1);
        connectInput = (EditText) findViewById(R.id.editText1);
        
        connectButton.setOnClickListener(new ConnectButtonListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
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
    
    private class ConnectButtonListener implements OnClickListener
    {

		@Override
		public void onClick(View v) {
			System.out.println("Input data: " + connectInput.toString());
			if (connectInput.toString().equals("")) {
				
			} else {
				Constants.setStorage(LoginActivity.this, "conurl", connectInput.toString());
				Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(myIntent);
				finish();
			}
		}
    	
    }
}
