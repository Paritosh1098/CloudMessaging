package com.example.cloudmessaging;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class GCMBroadcastReciever extends WakefulBroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String message = intent.getStringExtra("message");
		Log.d("message from intent.getStringExtra",""+message);
		Log.d("sender name",""+intent.getStringExtra("senderName"));
		Log.d("senderNmae",""+intent.getStringExtra("senderName"));
       Log.d("recieve","####recieve recieve recieve recieve");
       Log.d("intent",""+intent.getExtras().toString());
		Bundle extras = intent.getExtras();
		Log.d("extras.getString" ,""+extras.getString("message"));
		Log.d("senderName",""+extras.getString("senderName"));
	//    Log.d("intent",intent.getStringExtra("message"));
	
	//	Log.d("tag",""+extras.getString("message"));
		Config.message = (String)extras.get("message");
		Config.senderName =(String)extras.getString("senderName");
		ComponentName comp = new ComponentName(context.getPackageName(),
                GcmIntentService.class.getName());
        // Start the service, keeping the device awake while it is launching.
        startWakefulService(context, (intent.setComponent(comp)));
		setResultCode(Activity.RESULT_OK);
	/*	GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
		if(gcm.getMessageType(intent).equals(GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE))
		{
		Config.message = (String)extras.get("message");
		Log.d("message","message: "+Config.message);
		Config.senderName =(String)extras.getString("senderName");
		Log.d("senderName","senderName: "+Config.senderName);
		}
		else
		{
			Log.d("messageType",gcm.getMessageType(intent));
		}
		*/
		
		
	}

}
