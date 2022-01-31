package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Hello  {
    public void result(Context c, int a, int b, int result) {

        result=a+b;

        Toast.makeText(c,result, Toast.LENGTH_SHORT).show();






    }


}
