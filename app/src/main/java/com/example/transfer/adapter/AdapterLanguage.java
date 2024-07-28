package com.example.transfer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.transfer.R;
import com.example.transfer.simpleClass.Language;

import java.util.ArrayList;

public class AdapterLanguage extends ArrayAdapter<Language> {
    private int selectedPosition = 0;
    public AdapterLanguage(Context context, ArrayList<Language> languages) {
        super(context, 0, languages);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Language language = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewSubtitle = convertView.findViewById(R.id.textViewSubtitle);
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);

        assert language != null;
        textViewTitle.setText(language.getTitle());
        textViewSubtitle.setText(language.getSubtitle());
        radioButton.setChecked(position == selectedPosition);

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

}
