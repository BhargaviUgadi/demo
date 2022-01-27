package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

public class Hello {

    public static void send(Context context,String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
