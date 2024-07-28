package com.example.transfer.ui.cards;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transfer.adapter.AdapterCards;
import com.example.transfer.databinding.FragmentCardsBinding;
import com.example.transfer.simpleClass.Cards;

import java.util.ArrayList;

public class CardsFragment extends Fragment {

    private FragmentCardsBinding binding;
    private ArrayList<Cards> cards;
    private AdapterCards adapterCards;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CardsViewModel cardsViewModel = new ViewModelProvider(this).get(CardsViewModel.class);

        binding = FragmentCardsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initializeData();
        setupRecyclerView();

        return root;
    }

    private void initializeData() {
        cards = new ArrayList<>();
        cards.add(new Cards("Райфайзен", "2200 0022 2002 0202", "Иван Иванович И.", true));
        cards.add(new Cards("Тинькофф", "2200 0022 2002 0202", "Иван Иванович И.", true));
        cards.add(new Cards("Сбербанк", "2200 0022 2002 0202", "Иван Иванович И.", true));
        cards.add(new Cards("Уралсиб", "2200 0022 2002 0202", "Иван Иванович И.", true));
        cards.add(new Cards("Райфайзен", "2200 0022 2002 0202", "Иван Иванович И.", true));
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterCards = new AdapterCards(getActivity(), cards);
        recyclerView.setAdapter(adapterCards);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Ошибка")
                .setMessage(message)
                .setPositiveButton("ОК", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
