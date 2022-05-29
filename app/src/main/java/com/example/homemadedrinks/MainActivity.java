package com.example.homemadedrinks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.home);


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.recipe:
                intent = new Intent(MainActivity.this,RecipeActivity.class);
                startActivity(intent);
                return true;

            case R.id.schedule:
                intent = new Intent(MainActivity.this,ScheduleActivity.class);
                startActivity(intent);
                return true;

            case R.id.home:
                intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.calculate:
                intent = new Intent(MainActivity.this,CalculateV2Activity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}