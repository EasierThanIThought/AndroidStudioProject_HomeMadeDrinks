package com.example.homemadedrinks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BtnAdapter extends RecyclerView.Adapter<BtnAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<BtnModal> btnModalArrayList;
    private Context context;

    // constructor
    public BtnAdapter(ArrayList<BtnModal> btnModalArrayList, Context context) {
        this.btnModalArrayList = btnModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.btn_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        BtnModal modal = btnModalArrayList.get(position);
        holder.btnNameTV.setText(modal.getBtnName());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return btnModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView btnNameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            btnNameTV = itemView.findViewById(R.id.idTVBTNName);
        }
    }
}