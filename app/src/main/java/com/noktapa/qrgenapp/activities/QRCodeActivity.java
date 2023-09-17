package com.noktapa.qrgenapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.noktapa.qrgenapp.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

public class QRCodeActivity extends AppCompatActivity {

    private String PERMISSION;

    ImageView imageQR;
    EditText editText;
    Button btnGenerate;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PERMISSION = Manifest.permission.READ_MEDIA_IMAGES;
        }
        else {
            PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE;
        }

        editText = findViewById(R.id.edtText);
        imageQR = findViewById(R.id.imageQR);
        btnGenerate = findViewById(R.id.btnGenerate);

        btnGenerate.setOnClickListener(view -> {
            if (editText.getText().toString().trim().length() == 0)
            {
                editText.requestFocus();
                editText.setError("Required");
            }
            else
            {
                generateQRCode();
            }
        });
    }

    private void generateQRCode() {
        String inputValue = editText.getText().toString().trim();

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = Math.min(width, height);
        smallerDimension = smallerDimension * 3 / 4;

        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
        QRGEncoder qrgEncoder = new QRGEncoder(inputValue, null, QRGContents.Type.TEXT, smallerDimension);
        qrgEncoder.setColorBlack(Color.WHITE);
        qrgEncoder.setColorWhite(Color.BLACK);

        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.getBitmap();
            // Setting Bitmap to ImageView
            imageQR.setImageBitmap(bitmap);

            if (ContextCompat.checkSelfPermission(getApplicationContext(), PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                try {
                    String savePath = "/storage/emulated/0/Documents/QRCode/";

                    File folder = new File(savePath);
                    if (!folder.exists()) {
                        folder.mkdirs();
                    }

                    boolean save = new QRGSaver().save(savePath, getCurrentDateTime(), bitmap, QRGContents.ImageType.IMAGE_JPEG);
                    String result = save ? "QR Code Generated and Saved Successfully" : "Failed to Save QR Code";
                    Toast.makeText(QRCodeActivity.this, result, Toast.LENGTH_LONG).show();
                    editText.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ActivityCompat.requestPermissions(QRCodeActivity.this, new String[]{PERMISSION}, 0);
            }
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        return sdf.format(new Date());
    }
}