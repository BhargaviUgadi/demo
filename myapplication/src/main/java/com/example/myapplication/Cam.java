package com.example.myapplication;

import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStorageDirectory;

import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
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
import java.nio.file.Files;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Cam extends AppCompatActivity {

    private final Executor executor = Executors.newSingleThreadExecutor();
    private final int REQUEST_CODE = 1001;
    private final String[] Requires_Permission = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    PreviewView previewView, previewView1;
    Uri imageCaptureUri;
    ImageView captureimage;
    String app_folder_path = "";
    String contentValues;
    String path;
//    static File fileofimage;
    static String qqq ;
      File file;
    static File fileofImage;

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
//
//                long times = System.currentTimeMillis();
//                ContentValues contentValues = new ContentValues();
//                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, times);
//                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
//                     fileofImage = new File(String.valueOf(getExternalFilesDir(DIRECTORY_PICTURES)));


                    file = new File(getExternalFilesDir(DIRECTORY_PICTURES).getAbsolutePath() +mDateFormat.format(new Date())+ ".jpeg");

//                    Toast.makeText(Cam.this, qqq, Toast.LENGTH_SHORT).show();
                     qqq= String.valueOf(new File(file.getAbsolutePath()));






//                     file1.getAbsolutePath();

                    outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();

                    imageCapture.takePicture(outputFileOptions, getMainExecutor(), new ImageCapture.OnImageSavedCallback() {
                        @Override
                        public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                            Toast.makeText(Cam.this, "Photo saved successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(@NonNull ImageCaptureException exception) {

                            Toast.makeText(Cam.this, "Error" + exception, Toast.LENGTH_SHORT).show();
                            Log.d("eee", exception.getMessage());

                        }
                    });


                }


            }

        });
    }

    public static void capture(String path, CameraCallback cameraCallback) {

//        try {
//          String qqq=path;
//            cameraCallback.responsecam(qqq);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.d("HHH",e.getMessage());
//        }


//        path=String.valueOf(file1);
//        cameraCallback.responsecam(path);
//
//     try {
//          qqq= file.toString();
//         path=qqq;
//         cameraCallback.responsecam(path);
//     } catch (Exception e) {
//         e.printStackTrace();
//         Log.d("GG",e.getMessage());
//     }
//    try {
//        path=file.toString();
//        String qqq=path;
//        cameraCallback.responsecam(qqq);
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        Log.d("AA",e.getMessage());
//    }

//
        try {

//            Log.d("qqq",qq);
//              path=String.valueOf(fileofImage);
//            path = String.valueOf(fi);

            cameraCallback.responsecam(path);
//            Log.d("QWERT",qqq);
            Log.d("qqq",path);


        }catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
    Log.d("WWW",e.getMessage());
//}
        }
    }
}

//    }




