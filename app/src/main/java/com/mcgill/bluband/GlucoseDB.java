package com.mcgill.bluband;

import java.util.ArrayList;

public class GlucoseDB {
    private ArrayList<Glucose> database;

    public GlucoseDB() {}

    public GlucoseDB(ArrayList<Glucose> database) {
        this.database = database;
    }

    public ArrayList<Glucose> getDatabase () {return this.database;}

    public void setDatabase(ArrayList<Glucose> database) {this.database = database;}

}
