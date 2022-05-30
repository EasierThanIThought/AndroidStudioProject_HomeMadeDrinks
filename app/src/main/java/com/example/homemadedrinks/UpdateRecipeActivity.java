package com.example.homemadedrinks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateRecipeActivity extends AppCompatActivity {

    // variables for edittext, button, strings and dbhandler class
    private EditText recipeNameEdt, recipeIngrEdt, recipeDescriptionEdt;
    private Button updateRecipeBtn, deleteRecipeBtn, goBackBtn;
    private DBHandler dbHandler;
    String recipeName, recipeIngr, recipeDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);

        // initializing all variables
        recipeNameEdt = findViewById(R.id.idEdtRecipeName);
        recipeIngrEdt = findViewById(R.id.idEdtRecipeIngridients);
        recipeDescriptionEdt = findViewById(R.id.idEdtRecipeDescription);
        updateRecipeBtn = findViewById(R.id.idBtnUpdateRecipe);
        deleteRecipeBtn = findViewById(R.id.idBtnDelete);
        goBackBtn = findViewById(R.id.idBtnBack);

        // initialing dbhandler class
        dbHandler = new DBHandler(UpdateRecipeActivity.this);

        // getting data passed in adapter class
        recipeName = getIntent().getStringExtra("name");
        recipeIngr = getIntent().getStringExtra("ingridients");
        recipeDesc = getIntent().getStringExtra("description");

        // setting data to edit text of update activity
        recipeNameEdt.setText(recipeName);
        recipeIngrEdt.setText(recipeIngr);
        recipeDescriptionEdt.setText(recipeDesc);

        // on click listener for update button
        updateRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calling an update method and passing all edit text values
                dbHandler.updateRecipe(recipeName, recipeNameEdt.getText().toString(), recipeIngrEdt.getText().toString(),
                        recipeDescriptionEdt.getText().toString());

                // displaying a toast message that our recipe has been updated
                Toast.makeText(UpdateRecipeActivity.this, "Recipe Updated", Toast.LENGTH_SHORT).show();

                // launching our ViewRecipes activity
                Intent i = new Intent(UpdateRecipeActivity.this, ViewRecipes.class);
                startActivity(i);
            }
        });

        // on click listener for delete button to delete our recipe
        deleteRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete
                dbHandler.deleteRecipe(recipeName);
                Toast.makeText(UpdateRecipeActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateRecipeActivity.this, ViewRecipes.class);
                startActivity(i);
            }
        });

        // 27.5.2022 go back btn
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateRecipeActivity.this, ViewRecipes.class);
                startActivity(i);
            }
        });
    }
}