package com.example.java2.readsms;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<MySMSMessage> listSms = new ArrayList<>();
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permis();
        linkUi();
        setListner();
        getAllSms();
        print();


    }

    private void print() {
        for (int i = 0; i <listSms.size(); i++) {
            System.out.println(listSms.get(i).toString());

        }
    }

    public void getAllSms() {

        listSms = new ArrayList<MySMSMessage>();
        MySMSMessage obj = new MySMSMessage();
        Uri message = Uri.parse("content://sms/");
        ContentResolver cr = this.getContentResolver();
        Cursor c = cr.query(message, null, null, null, null);
        this.startManagingCursor(c);
        int totalSMSMessages = c.getCount();
        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMSMessages; i++) {
                obj = new MySMSMessage();
                obj.Id = c.getString(c.getColumnIndexOrThrow("_id"));
                obj.Address = c.getString(c.getColumnIndexOrThrow("address"));
                obj.Body = c.getString(c.getColumnIndexOrThrow("body"));
                obj.ReadState = c.getString(c.getColumnIndex("read")).equals("1");
                obj.Time = c.getString(c.getColumnIndexOrThrow("date"));
                obj.Inbox = c.getString(c.getColumnIndexOrThrow("type")).contains("1");
                listSms.add(obj);
                c.moveToNext();
            }
        }
        c.close();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void permis() {
        int GET_MY_PERMISSION = 1;
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.SEND_SMS)) {
            /* do nothing*/
            } else {

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, GET_MY_PERMISSION);
            }
        }
    }

    private void setListner() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsManagerSend();
            }
        });
    }

    private void linkUi() {
        button1 = (Button) findViewById(R.id.buttonsms);
    }

    public void sendSMS() {
        Uri uri = Uri.parse("smsto:12346556");
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", "Bla bla bla bla");
        startActivity(it);
    }

    private void smsManagerSend() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+40745452463", null, "Mesaj", null, null);
        Toast.makeText(MainActivity.this, "SMS send", Toast.LENGTH_SHORT).show();
    }
}
