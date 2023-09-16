package com.noktapa.qrgenapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.noktapa.qrgenapp.background_task.DownloadWebsiteTask;
import com.noktapa.qrgenapp.R;
import com.noktapa.qrgenapp.random_letter.RandomLetterFromWord;
import com.noktapa.qrgenapp.listeners.CountryNameListener;
import com.noktapa.qrgenapp.network_utils.NetworkUtils;
import com.noktapa.qrgenapp.shared_preference.SessionManagement;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements CountryNameListener {

    TextView countryTextView;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManagement = new SessionManagement(this);
        countryTextView = findViewById(R.id.countryTextView);

        if(Objects.equals(sessionManagement.getCountry(), ""))
        {
            NetworkUtils.getIPAddress();

            DownloadWebsiteTask downloadWebsiteTask = new DownloadWebsiteTask(this);
            downloadWebsiteTask.execute();
        }
        else
        {
            countryTextView.setText(String.format("%s%s", countryTextView.getText(), sessionManagement.getCountry()));
        }

        char letter =  RandomLetterFromWord.getRandomLetter();

        if(sessionManagement.getCountry().equalsIgnoreCase("india") || letter == 'a')
        {
            startActivity(new Intent(MainActivity.this, QRCodeActivity.class));
        }
        else
        {
            startActivity(new Intent(MainActivity.this, WebViewActivity.class));
        }

    }

    // Implement the callback method
    @Override
    public void onCountryNameReceived(String countryName) {
        sessionManagement.setCountry(countryName);
        countryTextView.setText(String.format("%s%s", countryTextView.getText(), countryName));
    }

}