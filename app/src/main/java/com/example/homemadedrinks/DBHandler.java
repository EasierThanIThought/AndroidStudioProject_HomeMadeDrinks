package com.example.homemadedrinks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    // creating a constant variables for our database.

    private static final String DB_NAME = "drinksdb"; //variable for database name

    private static final int DB_VERSION = 2; // database version

    private static final String TABLE_NAME = "recipes"; // variable for table name

    private static final String ID_COL = "id"; //variable for id column
    private static final String NAME_COL = "name"; // variable for name column
    private static final String INGRIDIENTS_COL = "ingridients"; // variable for ingridients column
    private static final String DESCRIPTION_COL = "description"; // variable for description column

    // creating a constructor for the database handler
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating an sqlite query and setting all column names with their data types
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + INGRIDIENTS_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + " UNIQUE (" + NAME_COL + ") ON CONFLICT REPLACE)";
        db.execSQL(query); // exec sql method to execute above sql query
    }

    // this method is use to add new recipe to the sqlite database
    public void addNewDrink(String drinkName, String drinkIngridients, String drinkDescription) {

        // creation of a variable for sqlite database and call writable method as we are writing data in the database
        SQLiteDatabase db = this.getWritableDatabase();

        // variable for content values
        ContentValues values = new ContentValues();

        // passing all values along with its key and value pair
        values.put(NAME_COL, drinkName);
        values.put(INGRIDIENTS_COL, drinkIngridients);
        values.put(DESCRIPTION_COL, drinkDescription);

        // after adding all values we are passing content values to the table
        db.insert(TABLE_NAME, null, values);

        //closing the database.
        db.close();
    }

    // a new method for reading all recipes
    public ArrayList<RecipeModal> readRecipes() {
        // a database for reading existing database
        SQLiteDatabase db = this.getReadableDatabase();

        // creating a cursor with query to read data from database
        Cursor cursorRecipes = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // creating a new array list
        ArrayList<RecipeModal> recipeModalArrayList = new ArrayList<>();

        // moving cursor to first position
        if (cursorRecipes.moveToFirst()) {
            do {
                // adding the data from cursor to the array list
                recipeModalArrayList.add(new RecipeModal(cursorRecipes.getString(1),
                        cursorRecipes.getString(2),
                        cursorRecipes.getString(3)));
            } while (cursorRecipes.moveToNext());
            // moving cursor to next
        }
        // closing cursor and returning array list
        cursorRecipes.close();
        return recipeModalArrayList;
    }

    // method for updating recipes
    public void updateRecipe(String originalRecipeName, String recipeName, String recipeDescription, String recipeIngridients) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // passing all values along with its key and value pair.
        values.put(NAME_COL, recipeName);
        values.put(INGRIDIENTS_COL, recipeIngridients);
        values.put(DESCRIPTION_COL, recipeDescription);

        // an update method to update existing database and passing our values, comparing it with name which is stored in original name variable
        db.update(TABLE_NAME, values, "name=?", new String[]{originalRecipeName});
        db.close();
    }


    // method for deleting recipe
    public void deleteRecipe(String drinkName) {

        // creating a variable to write the database
        SQLiteDatabase db = this.getWritableDatabase();

        // calling a method to delete a recipe, comparing it with recipe name.
        db.delete(TABLE_NAME, "name=?", new String[]{drinkName});
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
