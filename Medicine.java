package com.example.dailymedreminder;

import java.io.Serializable;

public class Medicine implements Serializable {
    private int id;
    private String name;
    private String dosage;
    private String time;
    private int currentStock;
    private int refillThreshold;

    public Medicine(int id, String name, String dosage, String time, int currentStock, int refillThreshold) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.time = time;
        this.currentStock = currentStock;
        this.refillThreshold = refillThreshold;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public int getCurrentStock() { return currentStock; }
    public void setCurrentStock(int currentStock) { this.currentStock = currentStock; }

    public int getRefillThreshold() { return refillThreshold; }
    public void setRefillThreshold(int refillThreshold) { this.refillThreshold = refillThreshold; }
}
