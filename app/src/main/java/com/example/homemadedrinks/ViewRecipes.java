package com.example.homemadedrinks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

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

        // getting our recipe array
        // list from db handler class.
        recipeModalArrayList = dbHandler.readRecipes();

        // on below line passing our array lost to our adapter class.
        recipeRVAdapter = new RecipeAdapter(recipeModalArrayList, ViewRecipes.this);
        recipeRV = findViewById(R.id.idRVRecipes);

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
                intent = new Intent(ViewRecipes.this,CalculateV2Activity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    //27.5.2022 search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recipeRVAdapter.getFilter().filter(s);
                return false;
            }
        });


        return true;
    }
}