package com.example.cloudmessaging;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class RecieveMessage extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recievemessage);
	TextView senderName = 	(TextView)findViewById(R.id.senderName);
	senderName.setText(Config.senderName);
	
	TextView message = (TextView)findViewById(R.id.message);
	message.setText(Config.message);
	
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
}
