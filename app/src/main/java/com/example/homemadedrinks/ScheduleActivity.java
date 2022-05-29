package com.example.homemadedrinks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScheduleActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.schedule);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.recipe:
                intent = new Intent(ScheduleActivity.this,RecipeActivity.class);
                startActivity(intent);
                return true;

            case R.id.schedule:
                return true;

            case R.id.home:
                intent = new Intent(ScheduleActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.calculate:
                intent = new Intent(ScheduleActivity.this,CalculateV2Activity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}