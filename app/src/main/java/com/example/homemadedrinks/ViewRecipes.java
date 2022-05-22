package com.example.homemadedrinks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ViewRecipes extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<RecipeModal> recipeModalArrayList;
    private DBHandler dbHandler;
    private RecipeAdapter recipeRVAdapter;
    private RecyclerView recipeRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipes);
        // initializing our all variables.
        recipeModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewRecipes.this);

        // getting our course array
        // list from db handler class.
        recipeModalArrayList = dbHandler.readRecipes();

        // on below line passing our array lost to our adapter class.
        recipeRVAdapter = new RecipeAdapter(recipeModalArrayList, ViewRecipes.this);
        recipeRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewRecipes.this, RecyclerView.VERTICAL, false);
        recipeRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        recipeRV.setAdapter(recipeRVAdapter);
    }
}