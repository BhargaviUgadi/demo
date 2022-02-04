package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.ApiCallback;
import com.example.myapplication.AppMessage;
import com.example.myapplication.Cam;
import com.example.myapplication.CameraCallback;
import com.example.myapplication.Hello;
import com.example.myapplication.MessToa;

import java.io.File;
import java.nio.file.Path;

public class MainActivity extends AppCompatActivity implements ApiCallback {

    Button btn,btn1,btn2,btn3,btn4;
//    String path;
    File file;
    String path1,message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.btn1);
        btn1=findViewById(R.id.btn2);
        btn2=findViewById(R.id.btn3);
        btn3=findViewById(R.id.btn);
        btn4=findViewById(R.id.button);

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
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                path1="/storage/emulated/0/Android/data/com.example.myapp/files";
                Cam.capture(path1,camera1);
//             Log.d("Path",path);


            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message="hello";
                AppMessage.string(message,messToa);
            }
        });
    }
    MessToa messToa= new MessToa() {
        @Override
        public void result(String msg) {
            Toast.makeText(MainActivity.this,""+ msg, Toast.LENGTH_SHORT).show();

        }
    };
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