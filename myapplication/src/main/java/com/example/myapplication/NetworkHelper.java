package com.example.myapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class NetworkHelper extends AppCompatActivity{
    //    Response response;
    String URL="https://reqres.in/api/products/3";
    ResponseBody responseBody;
    static  Api api1;
    Response response;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OkHttpClient okHttpClient= new OkHttpClient();
        Request request= new Request.Builder().url(URL).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("error",e.getMessage());

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    responseBody=response.body();

                    api1.onResult(responseBody.string());


                }

            }
        });
    }
    public static void display(Context context,Api api){

        try {
            api1=api;
            Intent intent= new Intent(context,NetworkHelper.class);
            context.startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("sss",e.getMessage());
        }
    }
}




