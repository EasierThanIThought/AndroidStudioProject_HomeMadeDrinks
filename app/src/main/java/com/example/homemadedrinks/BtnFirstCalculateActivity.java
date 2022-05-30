package com.example.homemadedrinks;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ValueEventListener;

public class BtnFirstCalculateActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNav;

    // creating variables
    private EditText volumeEdt, percBeforeEdt, percAfterEdt;
    private TextView seeResTV;
    private Button calcBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btnfirst_activity);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.calculate);

        // initializing all our variables.
        volumeEdt = findViewById(R.id.idvolume);
        percBeforeEdt = findViewById(R.id.idpercbefoere);
        percAfterEdt = findViewById(R.id.idpercafter);
        seeResTV = findViewById(R.id.idresult);
        calcBtn = findViewById(R.id.idCalc);

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the string inside the EditText
                String vol = volumeEdt.getText().toString();
                String pB = percBeforeEdt.getText().toString();
                String pA = percAfterEdt.getText().toString();

                // validating if the text fields are empty or not
                if (vol.isEmpty() || pB.isEmpty() || pA.isEmpty()) {
                    Toast.makeText(BtnFirstCalculateActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // parse it to a long
                long LongVol = Long.parseLong(vol);
                long LongPB = Long.parseLong(pB);
                long LongPA = Long.parseLong(pA);

                long result = 100 * LongPB * LongVol * 10 / LongPA - LongVol * 1000;
                //result /= 1000;

                String s = "For " + pA + "% after dilution, you need to add " + String.valueOf(result) + " ml of water.";
                seeResTV.setText(s);

                //volumeEdt.setText("");
                //percBeforeEdt.setText("");
                //percAfterEdt.setText("");

                //seeResTV.setText("WHAT");


            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.recipe:
                intent = new Intent(BtnFirstCalculateActivity.this,RecipeActivity.class);
                startActivity(intent);
                return true;

            case R.id.schedule:
                intent = new Intent(BtnFirstCalculateActivity.this,ScheduleActivity.class);
                startActivity(intent);
                return true;

            case R.id.home:
                intent = new Intent(BtnFirstCalculateActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.calculate:
                return true;
        }
        return false;
    }
}