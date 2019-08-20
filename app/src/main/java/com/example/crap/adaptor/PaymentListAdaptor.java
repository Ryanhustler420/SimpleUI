package com.example.crap.adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crap.R;
import com.example.crap.model.Payment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PaymentListAdaptor extends RecyclerView.Adapter<PaymentListAdaptor.ViewHolder> {

    private ArrayList<Payment> payments;
    private Context context;
    private OnItemClickListener itemClickListener;

    public PaymentListAdaptor(Context ctx, ArrayList<Payment> payments) {
        this.payments = payments;
        this.context = ctx;
    }

    public void setOnClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PaymentListAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View paymentChip = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.payment_chip, viewGroup, false);
        return new ViewHolder(paymentChip);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentListAdaptor.ViewHolder viewHolder, int i) {
        // final Context context = viewHolder.date.getContext();
        // Binding Data and view
        Payment payment = payments.get(i);
        viewHolder.date.setText("Date: " + new SimpleDateFormat("dd-MM-yyyy", Locale.US).format(payment.getDate()));
        viewHolder.amount.setText("Amount: " + payment.getAmount());
    }

    @Override
    public int getItemCount() {
        return payments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView date;
        TextView amount;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            date = itemView.findViewById(R.id.payment_date);
            amount = itemView.findViewById(R.id.payment_amount);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
