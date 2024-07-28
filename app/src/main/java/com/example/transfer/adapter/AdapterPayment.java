package com.example.transfer.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transfer.databinding.UiPaymentsItemBinding;
import com.example.transfer.helper.StatusLogoProvider;
import com.example.transfer.simpleClass.PaymantCard;

import java.util.List;



public class AdapterPayment extends RecyclerView.Adapter<AdapterPayment.PaymentViewHolder> {

    private final Context context;
    private final List<PaymantCard> paymantCards;
    private boolean isDialogShown = false;

    public AdapterPayment(Context context, List<PaymantCard> paymantCards) {
        this.context = context;
        this.paymantCards = paymantCards;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        UiPaymentsItemBinding binding = UiPaymentsItemBinding.inflate(inflater, parent, false);
        return new PaymentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        PaymantCard paymantCard = paymantCards.get(position);
        holder.bind(paymantCard);
    }

    @Override
    public int getItemCount() {
        return paymantCards.size();
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder {

        private final UiPaymentsItemBinding binding;

        public PaymentViewHolder(@NonNull UiPaymentsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(PaymantCard paymantCard) {
            binding.summ.setText(paymantCard.getSumm());
            binding.numberCard.setText(paymantCard.getNumberCard());
            binding.name.setText(paymantCard.getName());
            binding.numbId.setText(paymantCard.getId());
            binding.time.setText(paymantCard.getTime());
            binding.cancelButton.setText("Отменить");

            Integer logoResId = StatusLogoProvider.getLogoResource(paymantCard.getStatus());
            if (logoResId != null) {
                binding.logoBank.setImageResource(logoResId);
            }

            binding.cancelButton.setVisibility(paymantCard.getStatus().equals("wait") ? View.VISIBLE : View.GONE);

            binding.cancelButton.setOnClickListener(v -> showCancelDialog(getBindingAdapterPosition()));

            binding.constraintLayoutCopy.setOnClickListener(v -> copyTextToClipboard(paymantCard.getId()));
        }
    }

    private void copyTextToClipboard(String textToCopy) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(context, "ID скопирован", Toast.LENGTH_SHORT).show();
    }

    private void showCancelDialog(int position) {
        if (!isDialogShown) {
            isDialogShown = true;
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setMessage("Вы точно уверены, что хотите отменить платеж?")
                    .setPositiveButton("Да", (dialogInterface, i) -> {
                        PaymantCard paymantCard = paymantCards.get(position);
                        paymantCard.setStatus("cancel");
                        notifyItemChanged(position);
                        Toast.makeText(context, "Платеж отменен", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                        isDialogShown = false;
                    })
                    .setNegativeButton("Нет", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        isDialogShown = false;
                    })
                    .create();
            dialog.show();
        }
    }
}
