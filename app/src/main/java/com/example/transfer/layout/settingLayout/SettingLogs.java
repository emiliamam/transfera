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
    private LinearLayout linearLayout_logs;
    private Button customButtom;
    private EditText editText;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_logs);
        arrowBack = findViewById(R.id.arrow_back);
        linearLayout_logs = findViewById(R.id.linearLayout_logs);
        customButtom = findViewById(R.id.buttonTitle);
        editText.setHint(R.string.link);

        customButtom.setText(R.string.send);
        linearLayout_logs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
                startActivity(browserIntent);

                return false;
            }
        });

        arrowBack.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return false;
            }
        });
    }
}
