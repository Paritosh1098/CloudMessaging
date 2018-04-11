package com.example.cloudmessaging;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;    
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SendMessage extends Activity {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sendmessage);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendMessage(View view)
	{
		String recieverName = ((EditText)findViewById(R.id.RecieverName)).getText().toString();
		String message = ((TextView)findViewById(R.id.message)).getText().toString();
		
		String URL = Config.send_message_url;
		StringBuffer stringBuffer = new StringBuffer("");
		stringBuffer.append(URL);
		stringBuffer.append("?recieverName="+recieverName+"&message="+message+"&senderName="+Config.userName);
		final HttpClient httpClient = new DefaultHttpClient();
		final HttpPost httpPost = new HttpPost(stringBuffer.toString());
		
		Object params = null;
		new AsyncTask() {
		       
			@Override
		    protected void onPreExecute() {
		        super.onPreExecute();
		        Log.e("AsyncTask", "onPreExecute");
		    }
			@Override
			protected Object doInBackground(Object... arg0) {
				// TODO Auto-generated method stub
		try {
			httpClient.execute(httpPost);
	        Log.d("messageSend","messageSend");
	        httpClient.getConnectionManager().shutdown();
		
		}
		catch (Exception e) {
			
		e.printStackTrace();
		}
		return null; 
			}
		
	}.execute(params);
	}
	
}
