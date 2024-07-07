package com.example.transfer.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.transfer.R;
import com.example.transfer.adapter.Adapter;
import com.example.transfer.simpleClass.Language;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Locale;

public class SelectLanguage extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Language> languages;
    private Adapter adapter;
    private Button customButton;

    @SuppressLint({"ResourceAsColor", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_language);

        listView = findViewById(R.id.listView);
        customButton = findViewById(R.id.buttonTitle);
        customButton.setText("Продолжить");
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
        customButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Context context = setLocale(SelectLanguage.this, "en");
                recreate();
                Intent i = new Intent(SelectLanguage.this, InputApi.class);
                SelectLanguage.this.startActivity(i);
                return false;
            }
        });
    }
    public static Context setLocale(Context context, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();

        configuration.setLocale(locale);
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        context = context.createConfigurationContext(configuration);

        return context;
    }

}
