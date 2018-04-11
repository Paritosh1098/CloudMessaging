package com.example.cloudmessaging;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class GcmIntentService extends IntentService {

	public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;


	
	public GcmIntentService() {
		super("GcmIntentService");
		// TODO Auto-generated constructor stub
		
		
	}

	
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);
        Log.d("messageType",""+messageType);
        Log.d("onHandle Intent","onHandleIntent");
        Log.d("intent","intent.getstringExtra : "+intent.getStringExtra("message"));
    
        if (!extras.isEmpty()) {  // has effect of unparcelling Bundle
            /*
             * Filter messages based on message type. Since it is likely that GCM
             * will be extended in the future with new message types, just ignore
             * any message types you're not interested in, or that you don't
             * recognize.
             */
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
              Log.d("sending error","Sending error");
              sendNotification("Send error: " + extras.toString());
            } 
            else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                
            // If it's a regular GCM message, do some work.
            	Log.d("message type deleted","message type deleted");
            	 sendNotification("Deleted messages on server: " +
                         extras.toString());
            } 
            
            else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                // This loop represents the service doing some work.
            
            	Log.d("message : ",extras.toString());
            	sendNotification("Received: " + extras.toString());
       
            }
            else
            {
            	Log.d("else","else");
            }
        }
	}
	private void sendNotification(String msg) {
		// TODO Auto-generated method stub
		
		mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,new Intent(this,RecieveMessage.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this).setContentTitle("GCM Notification").setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).setContentText(msg).setSmallIcon(R.drawable.ic_launcher);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
		
	}


	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method1qw1 stub
		return null;
	}

}       
		
		
	
	


