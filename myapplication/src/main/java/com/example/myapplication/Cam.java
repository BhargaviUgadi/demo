package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Cam extends AppCompatActivity {

    private Executor executor= Executors.newSingleThreadExecutor();
    private static int REQUEST_CODE=1001;
    private static final String[] Requires_Permission= new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    PreviewView previewView,previewView1;
    ImageView captureimage;
    String contentValues,path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        previewView=findViewById(R.id.camera);

        captureimage=findViewById(R.id.captureImg);





}
    public void capture(String path1, Camera camera){

        path1=path;
        camera.responsecam(path1);




        if(allPermissionGranted()){
            startCamera();

        }
        else {
            ActivityCompat.requestPermissions(Cam.this,Requires_Permission,REQUEST_CODE);
        }


    }

    private void startCamera() {

        final ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture= ProcessCameraProvider.getInstance(this);

        cameraProviderListenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderListenableFuture.get();
                    bindPreview(cameraProvider);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, ContextCompat.getMainExecutor(Cam.this));



    }

    private void bindPreview(ProcessCameraProvider cameraProvider) {

        Preview preview= new Preview.Builder().build();


        CameraSelector cameraSelector= new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();

        ImageAnalysis imageAnalysis= new ImageAnalysis.Builder().build();
        ImageCapture imageCapture= new ImageCapture.Builder().build();

        ImageCapture.Builder builder= new ImageCapture.Builder();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        androidx.camera.core.Camera camera= cameraProvider.bindToLifecycle((LifecycleOwner) this,cameraSelector,imageAnalysis,imageCapture,preview);
        captureimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long times= System.currentTimeMillis();
                ContentValues contentValues= new ContentValues();
//                contentValues=contentValues;
                path="images/jpeg";
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME,times);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE,path);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    imageCapture.takePicture(
                            new ImageCapture.OutputFileOptions.Builder(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues).build(), getMainExecutor(), new ImageCapture.OnImageSavedCallback() {
                                @Override
                                public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                                    Toast.makeText(Cam.this, "Photo Saved Succesfully", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onError(@NonNull ImageCaptureException exception) {
                                    Toast.makeText(Cam.this,"Error in Saving Photo"+ exception.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                    );
                }
            }
        });


    }



    private boolean allPermissionGranted() {
        for(String permission:Requires_Permission){
            if(ContextCompat.checkSelfPermission(Cam.this,permission)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE){
            if(allPermissionGranted()){
                startCamera();
            }
            else {
                Toast.makeText(Cam.this, "Permission Not Grant By the User", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }
}