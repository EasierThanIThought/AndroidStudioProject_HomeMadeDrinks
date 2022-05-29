package com.example.homemadedrinks;

public class BtnModal {
    // variables
    private String btnName;
    private int id;

    // creating getter and setter methods
    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public BtnModal(String btnName) {
        this.btnName = btnName;
    }
}