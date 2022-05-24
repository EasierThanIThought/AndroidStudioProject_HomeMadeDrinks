package com.example.homemadedrinks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ViewRecipes extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<RecipeModal> recipeModalArrayList;
    private DBHandler dbHandler;
    private RecipeAdapter recipeRVAdapter;
    private RecyclerView recipeRV;

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipes);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.recipe);


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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.recipe:
                return true;

            case R.id.schedule:
                intent = new Intent(ViewRecipes.this,ScheduleActivity.class);
                startActivity(intent);
                return true;

            case R.id.home:
                intent = new Intent(ViewRecipes.this,HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.calculate:
                intent = new Intent(ViewRecipes.this,CalculateActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

}