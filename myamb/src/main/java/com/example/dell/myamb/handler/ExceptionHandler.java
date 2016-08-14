package com.example.dell.myamb.handler;

import android.util.Log;

/**
 * Created by vikas bajpayee on 10-08-2016.
 */
public class ExceptionHandler {

    public static void writeMessage(String message) {
        Log.d("Onyx-Message", message);
    }

    public static void writeError(String error) {
        Log.e("Onyx-Error", error);
    }
}
