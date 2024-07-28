package com.example.transfer.layout.initialLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.transfer.R;
import com.example.transfer.databinding.InputApiBinding;
import com.example.transfer.layout.Basic_Interface;

public class InputApi extends AppCompatActivity {
    private InputApiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = InputApiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupUI();
    }

    private void setupUI() {
        binding.customButton.buttonTitle.setText(R.string.next);

        binding.linearLayoutApi.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
            startActivity(browserIntent);
        });

        binding.customButton.buttonTitle.setOnClickListener(v -> {
            Intent intent = new Intent(InputApi.this, Basic_Interface.class);
            startActivity(intent);
        });
    }
}
