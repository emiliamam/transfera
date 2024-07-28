package com.example.transfer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transfer.databinding.UiCardsItemBinding;
import com.example.transfer.helper.BankLogoProvider;
import com.example.transfer.simpleClass.Cards;

import java.util.List;

public class AdapterCards extends RecyclerView.Adapter<AdapterCards.CardViewHolder> {

    private final Context context;
    private final List<Cards> cardsList;

    public AdapterCards(Context context, List<Cards> cardsList) {
        this.context = context;
        this.cardsList = cardsList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        UiCardsItemBinding binding = UiCardsItemBinding.inflate(inflater, parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Cards cards = cardsList.get(position);
        holder.bind(cards);
    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {

        private final UiCardsItemBinding binding;

        public CardViewHolder(@NonNull UiCardsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Cards cards) {
            binding.txtBank.setText(cards.getBank());
            binding.numberCard.setText(cards.getNumber());
            binding.name.setText(cards.getName());

            Integer logoResId = BankLogoProvider.getLogoResource(cards.getBank());
            if (logoResId != null) {
                binding.logoBank.setImageResource(logoResId);
            }
        }
    }
}
