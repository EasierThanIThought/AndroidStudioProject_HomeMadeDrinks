package com.example.homemadedrinks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CalculateActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNav;

    ArrayAdapter adapter;

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<BtnModal> btnModalArrayList;
    private DBHandlerForBtn dbHandlerForBtn;
    private BtnAdapter btnAdapter;
    private RecyclerView btnRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);


        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.calculate);


        // initializing our all variables.
        btnModalArrayList = new ArrayList<>();
        dbHandlerForBtn = new DBHandlerForBtn(CalculateActivity.this);


        dbHandlerForBtn.addNewBtn("Water Alcohol Dilution Calculator");
        dbHandlerForBtn.addNewBtn("Calculator for mixing two alcohol-containing liquids");
        dbHandlerForBtn.addNewBtn("Calculation of the parameters of sugar mash");
        dbHandlerForBtn.addNewBtn("Replacing sugar with glucose or fructose");
        dbHandlerForBtn.addNewBtn("Alcohol in Braga before and after fermentation");
        dbHandlerForBtn.addNewBtn("Estimated output of moonshine and the volume of stillage");
        dbHandlerForBtn.addNewBtn("Calculator of pure alcohol");
        dbHandlerForBtn.addNewBtn("Optimal wort acidity");
        dbHandlerForBtn.addNewBtn("Correction of hydrometer readings depending on temperature");

        // getting our btn array
        // list from db handler class.
        btnModalArrayList = dbHandlerForBtn.readBtn();

        // on below line passing our array lost to our adapter class.
        btnAdapter = new BtnAdapter(btnModalArrayList, CalculateActivity.this);
        btnRV = findViewById(R.id.idRVBTN);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CalculateActivity.this, RecyclerView.VERTICAL, false);
        btnRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        btnRV.setAdapter(btnAdapter);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.recipe:
                intent = new Intent(CalculateActivity.this,RecipeActivity.class);
                startActivity(intent);
                return true;

            case R.id.schedule:
                intent = new Intent(CalculateActivity.this,ScheduleActivity.class);
                startActivity(intent);
                return true;

            case R.id.home:
                intent = new Intent(CalculateActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.calculate:
                return true;
        }
        return false;
    }
}