package com.example.transfer.layout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.transfer.R;

public class InputApi extends AppCompatActivity {
    private Button customButton;
    private LinearLayout linearLayout_api;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_api);

        customButton = findViewById(R.id.buttonTitle);
        linearLayout_api = findViewById(R.id.linearLayout_api);

        customButton.setText(R.string.next);



        linearLayout_api.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent browserIntent = new
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
                startActivity(browserIntent);
                return false;
            }
        });

        customButton.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent i = new Intent(InputApi.this, Basic_Interface.class);
                InputApi.this.startActivity(i);
                return false;
            }
        });

    }
}
