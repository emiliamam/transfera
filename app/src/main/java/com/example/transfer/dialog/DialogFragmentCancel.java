package com.example.transfer.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogFragmentCancel extends DialogFragment {

    public interface CancelPaymentListener {
        void onCancelPaymentConfirmed();
    }

    private CancelPaymentListener listener;

    public void setListener(CancelPaymentListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Вы точно уверены, что хотите отменить платеж?")
                .setCancelable(false)
                .setPositiveButton("Да", (dialog, id) -> {
                    if (listener != null) {
                        listener.onCancelPaymentConfirmed();
                    }
                })
                .setNegativeButton("Нет", (dialog, id) -> dialog.dismiss());
        return builder.create();
    }
}
