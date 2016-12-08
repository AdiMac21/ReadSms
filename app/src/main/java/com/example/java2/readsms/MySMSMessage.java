package com.example.java2.readsms;

import android.content.ContentResolver;
import android.net.Uri;

import java.util.List;

/**
 * Created by java2 on 12/8/2016.
 */

public class MySMSMessage {
    public String Id;
    public String Address;
    public String Body;
    public boolean ReadState;
    public String Time;
    public boolean Inbox;

    public MySMSMessage() {


    }

    @Override
    public String toString() {
        return "MySMSMessage{" +
                "Id='" + Id + '\'' +
                ", Address='" + Address + '\'' +
                ", Body='" + Body + '\'' +
                ", ReadState=" + ReadState +
                ", Time='" + Time + '\'' +
                ", Inbox=" + Inbox +
                '}';
    }
}
