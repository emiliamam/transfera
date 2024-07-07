package com.example.transfer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.transfer.R;
import com.example.transfer.simpleClass.Language;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Language> {
    private int selectedPosition = -1;
    public Adapter(Context context, ArrayList<Language> languages) {
        super(context, 0, languages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Language language = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewSubtitle = convertView.findViewById(R.id.textViewSubtitle);
        RadioButton radioButton = convertView.findViewById(R.id.radioButton);

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
