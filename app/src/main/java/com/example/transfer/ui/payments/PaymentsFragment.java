package com.example.transfer.ui.payments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transfer.R;
import com.example.transfer.adapter.AdapterCards;
import com.example.transfer.adapter.AdapterPayment;
import com.example.transfer.databinding.FragmentPaymentsBinding;
import com.example.transfer.simpleClass.Cards;
import com.example.transfer.simpleClass.PaymantCard;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class PaymentsFragment extends Fragment {

    private FragmentPaymentsBinding binding;
    private ArrayList<PaymantCard> paymantCards;
    private RelativeLayout filter;
    private TextView date;
    private AdapterPayment adapterPayment;
    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayoutCopy;

    private static final String[] month = {"ru", "en", "zh", "es", "ar", "bn", "pt"};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PaymentsViewModel paymentsViewModel =
                new ViewModelProvider(this).get(PaymentsViewModel.class);

        binding = FragmentPaymentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        filter =  binding.filter;
        date = binding.date;
        recyclerView = binding.recyclerView;
        paymantCards = new ArrayList<>();

        paymantCards.add(new PaymantCard("12345", "213", "79998765432", "Райф Райфович Р.", "22:02","succes"));
        paymantCards.add(new PaymantCard("125", "213", "79998765432", "Райф Райфович Р.", "22:02","wait"));
        paymantCards.add(new PaymantCard("345", "213", "2200 3005 5003 0022", "Райф Райфович Р.", "22:02","cancel"));
        paymantCards.add(new PaymantCard("345", "213", "2200 3005 5003 0022", "Райф Райфович Р.", "22:02","cancel"));
        paymantCards.add(new PaymantCard("125", "213", "2200 3005 5003 0022", "Райф Райфович Р.", "22:02","wait"));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterPayment = new AdapterPayment(getActivity(), paymantCards);
        recyclerView.setAdapter(adapterPayment);

        showTodayDate();
        filter.setOnClickListener(v -> showDatePickerDialog());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireActivity(),
                (view, year1, monthOfYear, dayOfMonth) -> {
                    calendar.set(year1, monthOfYear, dayOfMonth);
                    String selectedDate = formatDate(calendar.getTime());
                    Log.i("Selected data", selectedDate);
                    date.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }
    private String formatDate(Date date) {
        Locale currentLocale = getResources().getConfiguration().getLocales().get(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM", currentLocale);
        return dateFormat.format(date);
    }
    @SuppressLint("SetTextI18n")
    private void showTodayDate() {
        Date today = new Date();
        String formattedDate = formatDate(today);
        date.setText(formattedDate);
    }
}
