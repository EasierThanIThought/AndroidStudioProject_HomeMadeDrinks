package com.example.homemadedrinks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

public class RecipeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNav;

    // creating variables for our edittext, button and dbhandler
    private EditText drinkNameEdt, drinkIngrEdt, drinkDescriptionEdt;
    private Button AddDrinkBtn, readRecipesBtn;
    private DBHandler dbHandler;
    ValueEventListener eventListener;


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



        dbHandler.addNewDrink("Martini Classic", "60ml vodka or gin; 1 tbsp dry vermouth; " +
                        "olive or lemon peel", "Stir the gin or vodka, dry vermouth and a little ice " +
                "together or put them in a cocktail shaker to combine. Strain into a chilled martini glass. " +
                "Serve with an olive on a cocktail stick or a twist of lemon peel.");
        dbHandler.addNewDrink("Screwdriver", "1 large or 2 small oranges; 1 clementine; 50ml vodka; " +
                "few dashes of Angostura bitters; handful of ice cubes; wedge of orange", "Squeeze the juice " +
                "from the oranges and clementine and set aside. Put a handful of ice cubes into a tall glass and pour over the vodka, " +
                "followed by the fruit juices. Stir gently to combine, then add the Angostura bitters and a wedge of orange to garnish.");

        dbHandler.addNewDrink("Frozen margarita", "50ml tequila; 25ml triple sec; 25ml lime juice; 15ml sugar syrup; " +
                "large handful of ice; wedge of lime to garnish", "Put all the ingredients except the lime wedge " +
                "in a blender and blitz until smooth. Tip into a cocktail, margarita or rocks glass, garnish and serve.");
        dbHandler.addNewDrink("Classic margarita", "50ml tequila; 25ml lime juice; 20ml triple sec; " +
                "ice; salt and 2 lime wedges to garnish", "Sprinkle a few teaspoons of salt over the surface of a small plate or saucer. " +
                "Rub one wedge of lime along the rim of a tumbler and then dip it into the salt so that the entire rim is covered. Fill a cocktail " +
                "shaker with ice, then add the tequila, lime juice and triple sec. Shake until the outside of the shaker feels cold. Strain the mix into the prepared glass over fresh ice. Serve with a wedge of lime.");
        dbHandler.addNewDrink("Mai tai", "25ml golden rum; 1 tbsp orange curacao; 2 tsp orgeat syrup; 25ml lime juice; 2 tsp sugar syrup; " +
                "ice cubes or crushed ice; 25ml dark rum; wedge of pineapple, a lime wheel or a cocktail cherry (or all three!) to garnish",
                "Pour the golden rum, curacao, orgeat, lime juice and sugar syrup into a cocktail shaker with a handful of ice cubes. Shake until " +
                        "the outside of the shaker feels really cold. Fill a tumbler with crushed ice then strain the cocktail over it. Slowly pour the dark rum over " +
                        "the top so it stains the top of the drink a rusty gold. Garnish with fruit like pineapple, lime and cocktail cherries if you like.");
        dbHandler.addNewDrink("Piña colada", "120ml pineapple juice; 60ml white rum; 60ml coconut cream; " +
                "wedge of pineapple to garnish", "Pulse all the ingredients along with a handful of ice in a blender until smooth. Pour into a tall glass and garnish as you like.");


        // below line is to add on click listener for our add drink recipe button.
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
                // recipe to sqlite data and pass all our values to it.
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
                intent = new Intent(RecipeActivity.this,CalculateV2Activity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

}