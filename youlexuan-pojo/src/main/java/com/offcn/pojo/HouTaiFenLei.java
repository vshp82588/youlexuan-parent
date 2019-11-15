package com.offcn.pojo;

import java.io.Serializable;

public class HouTaiFenLei implements Serializable {
    private String name;
    private String brand;
    private String title;
    private int prices;
    private String instant;

    public HouTaiFenLei() {
    }

    public HouTaiFenLei(String name, String brand, String title, int prices, String instant) {
        this.name = name;
        this.brand = brand;
        this.title = title;
        this.prices = prices;
        this.instant = instant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrices() {
        return prices;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }

    public String getInstant() {
        return instant;
    }

    public void setInstant(String instant) {
        this.instant = instant;
    }
}
