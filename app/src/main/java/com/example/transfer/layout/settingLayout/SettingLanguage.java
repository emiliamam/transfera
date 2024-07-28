package com.example.transfer.layout.settingLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.transfer.R;
import com.example.transfer.adapter.AdapterLanguage;
import com.example.transfer.helper.LocaleHelper;
import com.example.transfer.simpleClass.Language;

import java.util.ArrayList;

public class SettingLanguage extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Language> languages;
    private AdapterLanguage adapterLanguage;
    private Button customButton;
    private ImageButton arrowBack;
    private String[] language;

    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_language);

        listView = findViewById(R.id.listView);
        customButton = findViewById(R.id.buttonTitle);
        arrowBack = findViewById(R.id.arrow_back);

        customButton.setText(R.string.save);

        language = new String[]{"ru", "en", "zh", "es", "ar", "bn", "pt"};

        languages = new ArrayList<>();
        languages.add(new Language("Русский", "Russian"));
        languages.add(new Language("English", "English"));
        languages.add(new Language("中國人", "Chinese"));
        languages.add(new Language("Español", "Spanish"));
        languages.add(new Language("عرب", "Arabian"));
        languages.add(new Language("বাংলা", "Bengali"));
        languages.add(new Language("Português", "Portuguese"));

        adapterLanguage = new AdapterLanguage(this, languages);
        listView.setAdapter(adapterLanguage);

        customButton.setOnClickListener(v -> {
            int selectedPosition = adapterLanguage.getSelectedPosition();
            if (selectedPosition >= 0 && selectedPosition < language.length) {
                String selectedLanguageCode = language[selectedPosition];
                Log.i("SelectLanguage", "Selected language code: " + selectedLanguageCode);
                LocaleHelper.setLocale(SettingLanguage.this, selectedLanguageCode); // Сохранить и применить новый язык
                restartActivity();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Language selectedLanguage = languages.get(adapterLanguage.getSelectedPosition());
            Log.i("SelectLanguage", selectedLanguage.getTitle());
        });

        arrowBack.setOnTouchListener((v, event) -> {
            finish();
            return false;
        });
    }

    @SuppressLint("UnsafeIntentLaunch")
    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
