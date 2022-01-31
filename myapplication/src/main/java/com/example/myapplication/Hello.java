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

public class Hello extends AppCompatActivity {

    EditText editText,editText1;
    Button button;

    int x, y;
    int z;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.hello);


        editText=findViewById(R.id.edit);
        editText1=findViewById(R.id.edit1);
        button=findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x=Integer.parseInt(editText.getText().toString());
                y=Integer.parseInt(editText1.getText().toString());
                z=x+y;
                Toast.makeText(Hello.this, z, Toast.LENGTH_SHORT).show();


            }
        });


    }
}
