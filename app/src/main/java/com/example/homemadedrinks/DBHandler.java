package com.example.homemadedrinks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "drinksdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "recipes";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for name column
    private static final String NAME_COL = "name";

    // below variable id for ingridients column.
    private static final String INGRIDIENTS_COL = "ingridients";

    // below variable for description column.
    private static final String DESCRIPTION_COL = "description";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + INGRIDIENTS_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new recipe to our sqlite database.
    public void addNewDrink(String drinkName, String drinkIngridients, String drinkDescription) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, drinkName);
        values.put(INGRIDIENTS_COL, drinkIngridients);
        values.put(DESCRIPTION_COL, drinkDescription);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<RecipeModal> readRecipes() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorRecipes = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<RecipeModal> recipeModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorRecipes.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                recipeModalArrayList.add(new RecipeModal(cursorRecipes.getString(1),
                        cursorRecipes.getString(2),
                        cursorRecipes.getString(3)));
            } while (cursorRecipes.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorRecipes.close();
        return recipeModalArrayList;
    }

    // below is the method for updating our courses
    public void updateRecipe(String originalRecipeName, String recipeName, String recipeDescription, String recipeIngridients) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, recipeName);
        values.put(INGRIDIENTS_COL, recipeIngridients);
        values.put(DESCRIPTION_COL, recipeDescription);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalRecipeName});
        db.close();
    }


    // below is the method for deleting our course.
    public void deleteRecipe(String drinkName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
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
