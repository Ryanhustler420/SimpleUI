package com.example.crap.adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.crap.R;
import com.example.crap.model.Destination;

import java.util.ArrayList;

public class DestinationListAdaptor extends RecyclerView.Adapter<DestinationListAdaptor.ViewHolder> {

    private ArrayList<Destination> destinations;
    private Context context;
    private OnItemClickListener itemClickListener;

    public DestinationListAdaptor(Context ctx, ArrayList<Destination> destinations) {
        this.destinations = destinations;
        this.context = ctx;
    }

    public void setOnClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public DestinationListAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View destinationChip = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.destination_chip, viewGroup, false);
        return new ViewHolder(destinationChip);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationListAdaptor.ViewHolder viewHolder, int i) {
        final Context context = viewHolder.destinationTitle.getContext();

        // Binding Data and view
        Destination destination = destinations.get(i);
        viewHolder.destinationTitle.setText(destination.getName());
        viewHolder.price.setText("Rs. " + (destination.getPrice()));
    }

    @Override
    public int getItemCount() {
        return destinations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView destinationTitle;
        TextView price;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            destinationTitle = itemView.findViewById(R.id.destination_name);
            price = itemView.findViewById(R.id.destination_price);
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
