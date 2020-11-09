package com.example.android_assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTypeTran, txtNumTran, txtDateTime;

        public ViewHolder(View itemView){
            super(itemView);
            txtTypeTran = itemView.findViewById(R.id.txtTypeTran);
            txtNumTran = itemView.findViewById(R.id.txtNumTran);
            txtDateTime = itemView.findViewById(R.id.txtDateTime);
        }
    }

    // Create a list to store histories
    private List<History> listHistories;

    //HistoryAdapter constructor
    public HistoryAdapter(List<History> listHistories){
        this.listHistories = listHistories;
    }


    //inflate item_hisotry xml and return the new holder.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        //Inflate the custom layout
        View historyView = inflater.inflate(R.layout.item_history, parent, false);

        ViewHolder viewHolder = new ViewHolder(historyView);
        return viewHolder;
    }
    //populate data into item through holder.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History history = listHistories.get(position);

        holder.txtDateTime.setText(history.getDateTime());
        holder.txtTypeTran.setText(history.getTypeTran());
        holder.txtNumTran.setText(String.valueOf(history.getNumTran()));

    }


    @Override
    public int getItemCount() {
        return listHistories.size();
    }
}
