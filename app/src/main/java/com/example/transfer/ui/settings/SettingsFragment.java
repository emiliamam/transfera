package com.example.transfer.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.transfer.R;
import com.example.transfer.databinding.FragmentSettingsBinding;
import com.example.transfer.layout.Basic_Interface;
import com.example.transfer.layout.settingLayout.SettingApi;
import com.example.transfer.layout.settingLayout.SettingLanguage;
import com.example.transfer.layout.settingLayout.SettingLogs;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView item_language = binding.uiSettingsItemLanguage.textViewTitle;
        final TextView item_api = binding.uiSettingsItemApi.textViewTitle;
        final TextView item_logs = binding.uiSettingsItemLogs.textViewTitle;
        final TextView item_energo = binding.uiSettingsItemEnergo.textViewTitle;

        final RelativeLayout layout_language = binding.uiSettingsItemLanguage.item;
        final RelativeLayout layout_api = binding.uiSettingsItemApi.item;
        final RelativeLayout layout_logs = binding.uiSettingsItemLogs.item;

        item_language.setText(R.string.setting_frame_language);
        item_api.setText(R.string.setting_frame_api);
        item_logs.setText(R.string.setting_logs);
        item_energo.setText(R.string.energo);

        layout_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingLanguage.class);
                startActivity(intent);
            }
        });
        layout_api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingApi.class);
                startActivity(intent);
            }
        });
        layout_logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingLogs.class);
                startActivity(intent);
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}