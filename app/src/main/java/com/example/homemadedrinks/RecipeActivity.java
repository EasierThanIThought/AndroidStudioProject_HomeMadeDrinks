package com.example.homemadedrinks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

public class RecipeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNav;

    // creating variables for our edittext, button and dbhandler
    private EditText drinkNameEdt, drinkIngrEdt, drinkDescriptionEdt;
    private Button AddDrinkBtn, readRecipesBtn;
    private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.recipe);

        // initializing all our variables.
        drinkNameEdt = findViewById(R.id.idEdtDrinkName);
        drinkIngrEdt = findViewById(R.id.idEdtIngridients);
        drinkDescriptionEdt = findViewById(R.id.idEdtDescription);
        AddDrinkBtn = findViewById(R.id.idBtnAddDrink);
        readRecipesBtn = findViewById(R.id.idBtnViewAll);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(RecipeActivity.this);


        // below line is to add on click listener for our add course button.
        AddDrinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String drinkName = drinkNameEdt.getText().toString();
                String drinkIngr = drinkIngrEdt.getText().toString();
                String drinkDescription = drinkDescriptionEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (drinkName.isEmpty() && drinkIngr.isEmpty() && drinkDescription.isEmpty()) {
                    Toast.makeText(RecipeActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (drinkName.isEmpty() || drinkIngr.isEmpty() || drinkDescription.isEmpty()) {
                    Toast.makeText(RecipeActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewDrink(drinkName, drinkIngr, drinkDescription);

                // after adding the data we are displaying a toast message.
                Toast.makeText(RecipeActivity.this, "New recipe has been added.", Toast.LENGTH_SHORT).show();
                drinkNameEdt.setText("");
                drinkIngrEdt.setText("");
                drinkDescriptionEdt.setText("");
            }
        });

        readRecipesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(RecipeActivity.this, ViewRecipes.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.recipe:
                return true;

            case R.id.schedule:
                intent = new Intent(RecipeActivity.this,ScheduleActivity.class);
                startActivity(intent);
                return true;

            case R.id.home:
                intent = new Intent(RecipeActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.calculate:
                intent = new Intent(RecipeActivity.this,CalculateActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

}