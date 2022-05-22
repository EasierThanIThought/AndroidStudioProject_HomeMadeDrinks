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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CalculateActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNav;

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);


        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.calculate);



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