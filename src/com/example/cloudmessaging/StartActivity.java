package com.example.cloudmessaging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class StartActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendMessage(View view)
	{
		Intent intent = new Intent(StartActivity.this,SendMessage.class);
		startActivity(intent);
		this.finish();
		
	}
	
	public void recieveMessage(View view)
	{
		Intent intent = new Intent(StartActivity.this,RecieveMessage.class);
		startActivity(intent);
		this.finish();
		
	}
	
}
