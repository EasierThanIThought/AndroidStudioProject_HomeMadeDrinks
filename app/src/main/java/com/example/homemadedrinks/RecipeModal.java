package com.example.homemadedrinks;

public class RecipeModal {
    // variables for drinkname, ingridients and description, id.
    private String drinkName;
    private String drinkIngr;
    private String drinkDescription;
    private int id;
    // creating getter and setter methods
    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkIngridients() {
        return drinkIngr;
    }

    public void setDrinkIngridients(String drinkIngr) {
        this.drinkIngr = drinkIngr;
    }

    public String getDrinkDescription() {
        return drinkDescription;
    }

    public void setDrinkDescription(String drinkDescription) { this.drinkDescription = drinkDescription; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public RecipeModal(String drinkName, String drinkIngr, String drinkDescription) {
        this.drinkName = drinkName;
        this.drinkIngr = drinkIngr;
        this.drinkDescription = drinkDescription;
    }
}
