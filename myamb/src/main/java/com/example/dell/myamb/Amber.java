package com.example.dell.myamb;

import android.content.Context;

import com.example.dell.myamb.hub.Connect;

/**
 * Created by  vikas bajpayee on 10-08-2016.
 */
public class Amber {
    volatile static Connect mconnect =  null;
    public static Connect with(Context context){
        mconnect = new Connect(context);
        mconnect.setInstance(mconnect);
        return mconnect;
    }
}
