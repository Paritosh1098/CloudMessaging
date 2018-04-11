 package com.example.cloudmessaging;

import java.io.IOException;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Config;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
final Activity activity = this;
	 String regId ;
	 String userName;
	 String URL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences shrdPref = getSharedPreferences(MainActivity.class.getSimpleName(),Context.MODE_PRIVATE);

		regId = shrdPref.getString("reg_id","1000");
		
		if(!regId.equals("1000"))
		{
		   
			com.example.cloudmessaging.Config.userName = shrdPref.getString("userName","");
			Intent intent = new Intent(MainActivity.this,StartActivity.class);
			startActivity(intent);
			//start second activity
	      this.finish();
		}
	
	}

	private void createRegId() {
		// TODO Auto-generated method stub
		final GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		
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
				Log.d("helloworld","helloworld");
	        	 try {
	    			Log.d("registeredbefore","registeredbefore");
	        		 regId = gcm.register(com.example.cloudmessaging.Config.projectKey);
	        		 com.example.cloudmessaging.Config.regId = regId;
	        		 com.example.cloudmessaging.Config.userName =userName;
	    			 Log.d("registered","registered");
	    		
	    			Log.d("registration id","regId = "+regId);
	    		userName =	((TextView)findViewById(R.id.userName)).getText().toString();
	    	   SharedPreferences shrdPref = getSharedPreferences(MainActivity.class.getSimpleName(),Context.MODE_PRIVATE);
	    		Editor editor =  shrdPref.edit();
	    		editor.putString("reg_id",regId);
	    		editor.putString("userName",userName);
	    		editor.commit();
	    	    sendRegIdToServer();
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    			Log.d("registration id","NOT REGISTERED");
	    		}
	    	
	        	
	        	
	        	return null;
			}
			private void sendRegIdToServer() {
				// TODO Auto-generated method stub
				Log.d("server","server send");
				OutputStream out=null;
				
				Object params = null;
				try {
					
					new AsyncTask()
					{
					
						 protected void onPreExecute() {
						        super.onPreExecute();
						        Log.e("AsyncTask for send request to server", "onPreExecute");
						    }
							@Override
							protected Object doInBackground(Object... arg0)
							{
					
								try
								{
	
									HttpClient httpclient = new DefaultHttpClient();
									StringBuffer sb = new StringBuffer("");
									sb.append(com.example.cloudmessaging.Config.app_server_url);
									sb.append("?regId="+regId+"&userName="+userName);
									Log.d("url","url"+sb.toString());
						             HttpPost httppost = new HttpPost(sb.toString());
						             
						             httpclient.execute(httppost);
						             httpclient.getConnectionManager().shutdown();
		com.example.cloudmessaging.Config.userName = userName;
					Log.d("connected","connected");
					
					Intent intent = new Intent(MainActivity.this,StartActivity.class);
					startActivity(intent);
					
					activity.finish();
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
							return null;
							}
					}.execute(params);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
			}
		
			@Override
	        protected void onPostExecute(Object object) {
	           super.onPostExecute(object);
	         
	        }
		}.execute(params);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickLogin(View view)
	{
	
final GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		
		Log.d("helloindia","helloindia");
		SharedPreferences shrdPref = getSharedPreferences(MainActivity.class.getSimpleName(),Context.MODE_PRIVATE);
		regId = shrdPref.getString("reg_id","1000");
		userName = ((TextView)findViewById(R.id.userName)).getText().toString();
		if(regId.equals("1000")) 
		createRegId();
	      
		
		
	}

}
