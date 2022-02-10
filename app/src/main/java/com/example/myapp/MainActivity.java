package com.example.myapp;

//import static com.example.myapplication.getapi.getresponse1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Api;
import com.example.myapplication.ApiCallback;
import com.example.myapplication.AppMessage;
import com.example.myapplication.Cam;
import com.example.myapplication.CameraCallback;
import com.example.myapplication.Hello;
import com.example.myapplication.MessToa;
import com.example.myapplication.NetworkHelper;
import com.example.myapplication.getapi;
import com.example.myapplication.getresponse;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button btn, btn1, btn2, btn3, btn4,btn5,btn6;
    Cam cam;
    //    String path;
    File file;
    String path1, message;
    TextView textView;
    boolean cameraUse;
    String qqq="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn1);
        btn1 = findViewById(R.id.btn2);
        btn2 = findViewById(R.id.btn3);
        btn3 = findViewById(R.id.btn);
        btn4 = findViewById(R.id.button);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        textView=findViewById(R.id.resultPeview);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hello.add(1, 2, new ApiCallback() {
                    @Override
                    public void response(int result) {
                        Toast.makeText(MainActivity.this, "" + result, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Hello.add(20, 30, apiCallback1);


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Hello.add(50, 50, apiCallback1);

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "hello";
                AppMessage.string(message, messToa);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                JSONObject params= new JSONObject();
//                Intent intent = new Intent(MainActivity.this,Cam.class);
//                startActivity(intent);
//                textView.setText(path1);

                Cam.capture(MainActivity.this,camera1);


            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getapi.getres(MainActivity.this,getresponse1);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("tagggg",e.getMessage());
                }
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             try {
                 NetworkHelper.display(MainActivity.this,api);

             } catch (Exception e) {
                 e.printStackTrace();
                 Log.d("sdsd",e.getMessage());
             }
            }
        });
    }
    getresponse getresponse1= new getresponse() {
        @Override
        public void resultofresponse(String response) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(response);
                    Log.d("text",response);

                }
            });
        }
    };
    Api api= new Api() {
        @Override
        public void onResult(final String jsonObjectresult) {
//            try {
//                textView.setText(jsonObjectresult);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.d("wewe",e.getMessage());
//            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(jsonObjectresult);
                    Log.d("wewe",jsonObjectresult);

                }
            });
            //            Log.d("message",jsonObjectresult);
        }
    };

        MessToa messToa = new MessToa() {
        @Override
        public void result(String msg) {
            Toast.makeText(MainActivity.this, "" + msg, Toast.LENGTH_SHORT).show();

        }
    };
    CameraCallback camera1 = new CameraCallback() {
        @Override
        public void responsecam(final String path) {
            TextView textView1= new TextView(MainActivity.this);
            textView1=findViewById(R.id.resultPeview);
//            textView.setText(path);

            textView1.setText(path);

            Toast.makeText(MainActivity.this, path, Toast.LENGTH_SHORT).show();
//            finish();
//            Toast.makeText(MainActivity.this, ""+path, Toast.LENGTH_SHORT).show();
//            if(path!=null){
//                textView.setText(path);
////                Cam.capture(path1,camera1)
//                                Toast.makeText(getApplicationContext(),path,Toast.LENGTH_SHORT).show();
//
//
//
//            }

//            return path;
        }
    };


    ApiCallback apiCallback1 = new ApiCallback() {
        @Override
        public void response(int result) {
            Toast.makeText(MainActivity.this, "" + result, Toast.LENGTH_SHORT).show();
        }
    };
    ApiCallback apiCallback2 = new ApiCallback() {

        @Override
        public void response(int result) {
            Toast.makeText(MainActivity.this, "" + result, Toast.LENGTH_SHORT).show();


        }
    };
}