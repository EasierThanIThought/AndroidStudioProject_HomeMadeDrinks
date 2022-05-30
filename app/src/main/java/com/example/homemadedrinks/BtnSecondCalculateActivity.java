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

public class BtnSecondCalculateActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNav;

    // creating variables
    private EditText volume1Edt, perc1Edt, volume2Edt, perc2Edt;
    private TextView seeResTV;
    private Button calcBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btnsecond_activity);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(this);
        bottomNav.setSelectedItemId(R.id.calculate);

        // initializing all our variables.
        volume1Edt = findViewById(R.id.idvol1);
        perc1Edt = findViewById(R.id.idperc1);
        volume2Edt = findViewById(R.id.idvol2);
        perc2Edt = findViewById(R.id.idperc2);
        seeResTV = findViewById(R.id.idresult);
        calcBtn = findViewById(R.id.idCalc);

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the string inside the EditText
                String v1 = volume1Edt.getText().toString();
                String v2 = volume2Edt.getText().toString();
                String p1 = perc1Edt.getText().toString();
                String p2 = perc2Edt.getText().toString();

                // validating if the text fields are empty or not
                if (v1.isEmpty() || v2.isEmpty() || p1.isEmpty() || p2.isEmpty()) {
                    Toast.makeText(BtnSecondCalculateActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // parse it to a long
                long LongV1 = Long.parseLong(v1);
                long LongV2 = Long.parseLong(v2);
                long LongP1 = Long.parseLong(p1);
                long LongP2 = Long.parseLong(p2);

                long result = (LongP1 * LongV1 + LongP2 * LongV2) / (LongV1 + LongV2);
                //result /= 1000;

                String s = "Volume fraction of alcohol: " + String.valueOf(result) + "%.";
                seeResTV.setText(s);

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.recipe:
                intent = new Intent(BtnSecondCalculateActivity.this,RecipeActivity.class);
                startActivity(intent);
                return true;

            case R.id.schedule:
                intent = new Intent(BtnSecondCalculateActivity.this,ScheduleActivity.class);
                startActivity(intent);
                return true;

            case R.id.home:
                intent = new Intent(BtnSecondCalculateActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.calculate:
                return true;
        }
        return false;
    }
}