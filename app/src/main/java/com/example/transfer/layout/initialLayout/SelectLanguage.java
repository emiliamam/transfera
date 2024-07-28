package com.example.transfer.layout.initialLayout;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.transfer.R;
import com.example.transfer.adapter.AdapterLanguage;
import com.example.transfer.databinding.SelectLanguageBinding;
import com.example.transfer.helper.LocaleHelper;
import com.example.transfer.layout.Basic_Interface;
import com.example.transfer.simpleClass.Language;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class SelectLanguage extends AppCompatActivity {
    private ExecutorService executor;
    private ListView listView;
    private ArrayList<Language> languages;
    private AdapterLanguage adapterLanguage;
    private Button customButton;
    private SelectLanguageBinding binding;
    private static final String[] LANGUAGE_CODES = {"ru", "en", "zh", "es", "ar", "bn", "pt"};
    private static final String PREFERENCES_NAME = "app_preferences";
    private static final String IS_FIRST_RUN_KEY = "is_first_run";

    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LocaleHelper.setLocale(this, LocaleHelper.getLanguage(this));
        Log.i("LNG", "Setlng" + LocaleHelper.getLanguage(this));
        super.onCreate(savedInstanceState);
        binding = SelectLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        if (isFirstRun()) {
//            setFirstRun();
//            if (!isNotificationServiceEnabled()) {
//                requestNotificationAccess();
//            }
//            proceedToMainActivity();
//        } else {
//            navigateToMainActivity();
//        }

        initializeViews();
        setupLanguages();
        setupAdapter();
        setupListeners();
    }

    private void initializeViews() {
        listView = binding.listView;
        customButton = binding.customButton.buttonTitle;
        customButton.setText(R.string.next);
    }

    private void setFirstRun() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_FIRST_RUN_KEY, false);
        editor.apply();
    }

    private boolean isNotificationServiceEnabled() {
        String pkgName = getPackageName();
        String flat = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (flat != null) {
            for (String name : flat.split(":")) {
                ComponentName cn = ComponentName.unflattenFromString(name);
                if (cn != null && pkgName.equals(cn.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isFirstRun() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        return preferences.getBoolean(IS_FIRST_RUN_KEY, true);
    }

    private void setupLanguages() {
        languages = new ArrayList<>();
        languages.add(new Language("Русский", "Russian"));
        languages.add(new Language("English", "English"));
        languages.add(new Language("中國人", "Chinese"));
        languages.add(new Language("Español", "Spanish"));
        languages.add(new Language("عرب", "Arabian"));
        languages.add(new Language("বাংলা", "Bengali"));
        languages.add(new Language("Português", "Portuguese"));
    }

    private void proceedToMainActivity() {
        Intent intent = new Intent(SelectLanguage.this, Basic_Interface.class);
        intent.putExtra("notifications_enabled", isNotificationServiceEnabled());
        startActivity(intent);
        finish();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(SelectLanguage.this, Basic_Interface.class);
        startActivity(intent);
        finish();
    }

    private void setupAdapter() {
        adapterLanguage = new AdapterLanguage(this, languages);
        listView.setAdapter(adapterLanguage);
        listView.setSelection(0);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupListeners() {
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Language selectedLanguage = languages.get(adapterLanguage.getSelectedPosition());
            Log.i("SelectLanguage", selectedLanguage.getTitle());

            String selectedLanguageCode = LANGUAGE_CODES[position];
            LocaleHelper.setLocale(SelectLanguage.this, selectedLanguageCode);
            navigateToInputApi();
        });

        customButton.setOnTouchListener((v, event) -> {
            Language selectedLanguage = languages.get(adapterLanguage.getSelectedPosition());
            Log.i("SelectLanguage", LANGUAGE_CODES[adapterLanguage.getSelectedPosition()]);

            LocaleHelper.setLocale(SelectLanguage.this, LANGUAGE_CODES[adapterLanguage.getSelectedPosition()]);
            navigateToInputApi();
            return false;
        });
    }

    private void requestNotificationAccess() {
        Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
        startActivity(intent);
    }

    private void navigateToInputApi() {
        Intent intent = new Intent(this, InputApi.class);
        startActivity(intent);
    }
}
