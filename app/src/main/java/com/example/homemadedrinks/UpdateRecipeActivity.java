package com.example.homemadedrinks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateRecipeActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText recipeNameEdt, recipeIngrEdt, recipeDescriptionEdt;
    private Button updateRecipeBtn, deleteRecipeBtn;
    private DBHandler dbHandler;
    String recipeName, recipeIngr, recipeDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recipe);

        // initializing all our variables.
        recipeNameEdt = findViewById(R.id.idEdtRecipeName);
        recipeIngrEdt = findViewById(R.id.idEdtRecipeIngridients);
        recipeDescriptionEdt = findViewById(R.id.idEdtRecipeDescription);
        updateRecipeBtn = findViewById(R.id.idBtnUpdateRecipe);
        deleteRecipeBtn = findViewById(R.id.idBtnDelete);

        // on below line we are initialing our dbhandler class.
        dbHandler = new DBHandler(UpdateRecipeActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        recipeName = getIntent().getStringExtra("name");
        recipeIngr = getIntent().getStringExtra("ingridients");
        recipeDesc = getIntent().getStringExtra("description");

        // setting data to edit text
        // of our update activity.
        recipeNameEdt.setText(recipeName);
        recipeIngrEdt.setText(recipeIngr);
        recipeDescriptionEdt.setText(recipeDesc);

        // adding on click listener to our update button.
        updateRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update
                // method and passing all our edit text values.
                dbHandler.updateRecipe(recipeName, recipeNameEdt.getText().toString(), recipeIngrEdt.getText().toString(),
                        recipeDescriptionEdt.getText().toString());

                // displaying a toast message that our recipe has been updated.
                Toast.makeText(UpdateRecipeActivity.this, "Recipe Updated", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateRecipeActivity.this, ViewRecipes.class);
                startActivity(i);
            }
        });

        // adding on click listener for delete button to delete our recipe.
        deleteRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                dbHandler.deleteRecipe(recipeName);
                Toast.makeText(UpdateRecipeActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateRecipeActivity.this, ViewRecipes.class);
                startActivity(i);
            }
        });
    }
}