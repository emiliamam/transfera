package com.example.transfer.layout.settingLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.transfer.R;

public class SettingLogs extends AppCompatActivity {
    private ImageButton arrowBack;
    private LinearLayout linearLayoutLogs;
    private Button customButton;
    private EditText editText;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_logs);

        initViews();
        setupListeners();
    }

    private void initViews() {
        arrowBack = findViewById(R.id.arrow_back);
        linearLayoutLogs = findViewById(R.id.linearLayout_logs);
        customButton = findViewById(R.id.buttonTitle);
        customButton.setText(R.string.send);
//        editText = findViewById(R.id.editText);
//        editText.setHint(R.string.link);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupListeners() {
        linearLayoutLogs.setOnTouchListener((v, event) -> {
            openBrowser("https://www.google.com/");
            return false;
        });

        arrowBack.setOnTouchListener((v, event) -> {
            finish();
            return false;
        });
    }

    private void openBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
