package com.example.homemadedrinks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.recipe:
                intent = new Intent(HomeActivity.this,RecipeActivity.class);
                startActivity(intent);
                return true;

            case R.id.schedule:
                intent = new Intent(HomeActivity.this,ScheduleActivity.class);
                startActivity(intent);
                return true;

            case R.id.home:
                return true;

            case R.id.calculate:
                intent = new Intent(HomeActivity.this,CalculateV2Activity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}