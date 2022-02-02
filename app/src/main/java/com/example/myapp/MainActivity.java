package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.ApiCallback;
import com.example.myapplication.Cam;
import com.example.myapplication.CameraCallback;
import com.example.myapplication.Hello;

public class MainActivity extends AppCompatActivity implements ApiCallback {

    Button btn,btn1,btn2,btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.btn1);
        btn1=findViewById(R.id.btn2);
        btn2=findViewById(R.id.btn3);
        btn3=findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hello.add(1, 2, new ApiCallback() {
                    @Override
                    public void response(int result) {
                        Toast.makeText(MainActivity.this, "" +result, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Hello.add(20,30,apiCallback1);


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Hello.add(50,50,MainActivity.this);

            }
        });
        String path = null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cam.capture(path,camera1);


            }
        });
    }
    CameraCallback camera1= new CameraCallback() {
        @Override
        public void responsecam(String path) {
            Toast.makeText(MainActivity.this, ""+path, Toast.LENGTH_SHORT).show();


        }
    };



    ApiCallback apiCallback1 = new ApiCallback() {
        @Override
        public void response(int result) {
            Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public void response(int result) {
        Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();



    }
}