package com.arun.tasc;

public class StudentModel {
    public String name,roll,adno,col;

    public StudentModel() {
    }

    public StudentModel(String name, String roll, String adno, String col) {
        this.name = name;
        this.roll = roll;
        this.adno = adno;
        this.col = col;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getAdno() {
        return adno;
    }

    public void setAdno(String adno) {
        this.adno = adno;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }
}
