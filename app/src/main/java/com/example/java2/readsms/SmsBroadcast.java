package com.example.java2.readsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by java2 on 12/8/2016.
 */

public class SmsBroadcast extends BroadcastReceiver {
    private static final String TAG = "MySmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getExtras() != null) {
            Object[] smsextras = (Object[]) intent.getExtras().get("pdus");
            for (int tr = 0; tr < smsextras.length; tr++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) smsextras[tr]);
                String text = smsMessage.getMessageBody().toString();
                String number = smsMessage.getOriginatingAddress();
                Toast.makeText(context, "no= " + number + "txt= " + text, Toast.LENGTH_LONG).show();

            }
        }
    }
}
