package com.example.transfer.layout.settingLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.transfer.R;
import com.example.transfer.adapter.Adapter;
import com.example.transfer.simpleClass.Language;
import com.example.transfer.ui.settings.SettingsFragment;

import java.util.ArrayList;

public class SettingLanguage extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Language> languages;
    private Adapter adapter;
    private Button customButton;
    private ImageButton arrowBack;
    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_language);

        listView = findViewById(R.id.listView);
        customButton = findViewById(R.id.buttonTitle);
        arrowBack = findViewById(R.id.arrow_back);
        customButton.setOnClickListener(v -> {
            customButton.setText("Clicked!");
        });
        customButton.setText(R.string.save);
        languages = new ArrayList<>();
        languages.add(new Language("Русский", "Russian"));
        languages.add(new Language("English", "English"));
        languages.add(new Language("中國人", "Chinese"));
        languages.add(new Language("Español", "Spanish"));
        languages.add(new Language("عرب", "Arabian"));
        languages.add(new Language("বাংলা", "Bengali"));
        languages.add(new Language("Português", "Portuguese"));


        adapter = new Adapter(this, languages);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Language selectedLanguage = languages.get(adapter.getSelectedPosition());
//            Log.i("SelectLanguage", adapter.getSelectedPosition());
//            selectedTextView.setText("Selected: " + selectedLanguage.getTitle());
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
