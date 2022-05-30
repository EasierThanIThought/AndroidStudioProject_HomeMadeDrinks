package com.example.homemadedrinks;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> implements Filterable {
    // variable for our array list and context
    private ArrayList<RecipeModal> recipeModalArrayList;
    private Context context;

    // 27.5.2022 for search
    private ArrayList<RecipeModal> recipeArrayListFull;

    // constructor
    public RecipeAdapter(ArrayList<RecipeModal> recipeModalArrayList, Context context) {
        this.recipeModalArrayList = recipeModalArrayList;
        this.context = context;
        // 27.5.2022 will contain same items but will be independent
        recipeArrayListFull = new ArrayList<>(recipeModalArrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout file for recycler view items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to views of recycler view item
        RecipeModal modal = recipeModalArrayList.get(position);
        holder.drinkNameTV.setText(modal.getDrinkName());
        holder.drinkIngrTV.setText(modal.getDrinkIngridients());
        holder.drinkDescTV.setText(modal.getDrinkDescription());

        // add on click listener for recycler view item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling an intent
                Intent i = new Intent(context, UpdateRecipeActivity.class);

                // passing all our values
                i.putExtra("name", modal.getDrinkName());
                i.putExtra("ingridients", modal.getDrinkIngridients());
                i.putExtra("description", modal.getDrinkDescription());

                // starting activity
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of array list
        return recipeModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for text views
        private TextView drinkNameTV, drinkIngrTV, drinkDescTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing text views
            drinkNameTV = itemView.findViewById(R.id.idTVDrinkName);
            drinkIngrTV = itemView.findViewById(R.id.idTVDrinkIngridients);
            drinkDescTV = itemView.findViewById(R.id.idTVDrinkDescription);
        }
    }

    // 27.5.2022
    @Override
    public Filter getFilter() {
        return recipeFilter;
    }
    private Filter recipeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<RecipeModal> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(recipeArrayListFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim();

                for (RecipeModal item : recipeArrayListFull) {
                    if (item.getDrinkName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            recipeModalArrayList.clear();
            recipeModalArrayList.addAll((List) results.values);

            notifyDataSetChanged();
        }
    };
}