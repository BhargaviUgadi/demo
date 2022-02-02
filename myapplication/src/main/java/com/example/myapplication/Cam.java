package com.example.myapplication;

import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
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

import java.io.File;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Cam extends AppCompatActivity {

    private Executor executor = Executors.newSingleThreadExecutor();
    private int REQUEST_CODE = 1001;
    private final String[] Requires_Permission = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    PreviewView previewView, previewView1;
    Uri imageCaptureUri;
    ImageView captureimage;
    String contentValues;
    String path;
    static String file;
    ImageCapture.OutputFileOptions outputFileOptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam);
        previewView = findViewById(R.id.camera);

        captureimage = findViewById(R.id.captureImg);

        if (allPermissionGranted()) {
            startCamera();

        } else {
            ActivityCompat.requestPermissions(Cam.this, Requires_Permission, REQUEST_CODE);
        }

    }

    private boolean allPermissionGranted() {
        for (String permission : Requires_Permission) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void startCamera() {
        final ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture = ProcessCameraProvider.getInstance(this);

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
        }, ContextCompat.getMainExecutor(this));


    }

    private void bindPreview(ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder().build();


        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder().build();
        ImageCapture imageCapture = new ImageCapture.Builder().build();

        ImageCapture.Builder builder = new ImageCapture.Builder();

        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, imageAnalysis, imageCapture, preview);
        captureimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long times = System.currentTimeMillis();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, times);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

                     file = String.valueOf(new File(getBatchDirectoryName() + ".jpg"));
                    outputFileOptions = new ImageCapture.OutputFileOptions.Builder(new File(file)).build();

                    imageCapture.takePicture(outputFileOptions, executor, new ImageCapture.OnImageSavedCallback() {
                        @Override
                        public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                            Toast.makeText(Cam.this, "Photo saved successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(@NonNull ImageCaptureException exception) {

                            Toast.makeText(Cam.this, "Error" + exception, Toast.LENGTH_SHORT).show();

                        }
                    });


                }


            }

            private String getBatchDirectoryName() {


                String app_folder_path = "";
                app_folder_path = Environment.getExternalStorageDirectory().toString() + "/images";
                File dir = new File(app_folder_path);
                if (!dir.exists() && !dir.mkdirs()) {

                }

                return app_folder_path;

            }
        });
    }
    public static void capture(String path,CameraCallback cameraCallback){
        path=file;
        capture(path, cameraCallback);

    }
}