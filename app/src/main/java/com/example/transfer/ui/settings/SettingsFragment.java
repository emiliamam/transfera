package com.example.transfer.ui.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.transfer.R;
import com.example.transfer.databinding.FragmentSettingsBinding;
import com.example.transfer.layout.settingLayout.SettingApi;
import com.example.transfer.layout.settingLayout.SettingLanguage;
import com.example.transfer.layout.settingLayout.SettingLogs;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setupUI();

        return root;
    }

    private void setupUI() {
        final TextView itemApi = binding.uiSettingsItemApi.textViewTitle;
        final TextView itemLogs = binding.uiSettingsItemLogs.textViewTitle;
        final TextView itemEnergo = binding.uiSettingsItemEnergo.textViewTitle;

        final RelativeLayout layoutApi = binding.uiSettingsItemApi.item;
        final RelativeLayout layoutLogs = binding.uiSettingsItemLogs.item;

        @SuppressLint("UseSwitchCompatOrMaterialCode") final Switch switchEnergo = binding.uiSettingsItemEnergo.switchEnergo;

        itemApi.setText(R.string.setting_frame_api);
        itemLogs.setText(R.string.setting_logs);
        itemEnergo.setText(R.string.energo);

        layoutApi.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingApi.class);
            startActivity(intent);
        });

        layoutLogs.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingLogs.class);
            startActivity(intent);
        });

        switchEnergo.setChecked(checkPowerSavingStatus());

        switchEnergo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            openPowerSavingSettings();
            switchEnergo.postDelayed(() -> switchEnergo.setChecked(checkPowerSavingStatus()), 500);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void openPowerSavingSettings() {
        Intent intent = new Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS);
        startActivity(intent);
    }

    private boolean checkPowerSavingStatus() {
        PowerManager powerManager = (PowerManager) requireContext().getSystemService(Context.POWER_SERVICE);
        return powerManager != null && powerManager.isPowerSaveMode();
    }
}
