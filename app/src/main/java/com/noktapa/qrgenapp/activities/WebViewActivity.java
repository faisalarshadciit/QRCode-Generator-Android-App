package com.noktapa.qrgenapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.noktapa.qrgenapp.R;
import com.noktapa.qrgenapp.shared_preference.SessionManagement;
import com.willy.ratingbar.ScaleRatingBar;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        sessionManagement = new SessionManagement(this);
        webView = findViewById(R.id.webView);

        webView.loadUrl("file:///android_asset/index.html");

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        if(sessionManagement.getIsFirstTime())
        {
            sessionManagement.setIsFirstTime(false);
        }
        else
        {
            if(!sessionManagement.getIsRated())
            {
                showRatingDialog();
            }
        }
    }

    private void showRatingDialog(){

        Dialog dialog = new Dialog(WebViewActivity.this, R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rating_dialog, null, false);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100, 0, 100, 0);
        dialog.setContentView(view, layoutParams);

        final Window window = dialog.getWindow();
        if (window != null)
        {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.CENTER);
        }

        ScaleRatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
        TextView btnRateUs = dialog.findViewById(R.id.btnRateUs);
        TextView btnClose = dialog.findViewById(R.id.btnClose);

        btnRateUs.setOnClickListener(view1 -> {
            if(ratingBar.getRating() > 0)
            {
                sessionManagement.setIsRated(true);
                dialog.dismiss();
                Toast.makeText(this, "Thank You for Rating Our App.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Please select atleast one star to Rate Us.", Toast.LENGTH_SHORT).show();
            }
        });
        btnClose.setOnClickListener(view1 -> dialog.dismiss());

        dialog.show();
    }

}