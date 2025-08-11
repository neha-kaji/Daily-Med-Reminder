package com.example.dailymedreminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medreminder.R;

import java.util.ArrayList;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {

    private ArrayList<Medicine> medicineList;

    public MedicineAdapter(ArrayList<Medicine> medicineList) {
        this.medicineList = medicineList;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine, parent, false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        Medicine medicine = medicineList.get(position);
        holder.nameTextView.setText(medicine.getName());
        holder.dosageTextView.setText(medicine.getDosage());
        holder.timeTextView.setText(medicine.getTime());
        holder.stockTextView.setText("Stock: " + medicine.getCurrentStock());

        // Refill alert logic: change UI if stock is below threshold
        if (medicine.getCurrentStock() <= medicine.getRefillThreshold()) {
            holder.refillAlertCard.setVisibility(View.VISIBLE);
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.red_refill_alert));
        } else {
            holder.refillAlertCard.setVisibility(View.GONE);
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));
        }

        // Add a fade-in animation to the card
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
        holder.cardView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public static class MedicineViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView nameTextView;
        public TextView dosageTextView;
        public TextView timeTextView;
        public TextView stockTextView;
        public CardView refillAlertCard;
        public TextView refillAlertTextView;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.medicine_card_view);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            dosageTextView = itemView.findViewById(R.id.dosage_text_view);
            timeTextView = itemView.findViewById(R.id.time_text_view);
            stockTextView = itemView.findViewById(R.id.stock_text_view);
            refillAlertCard = itemView.findViewById(R.id.refill_alert_card);
            refillAlertTextView = itemView.findViewById(R.id.refill_alert_text_view);
        }
    }
}
