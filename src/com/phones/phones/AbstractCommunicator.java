package com.phones.phones;

import com.phones.utils.LocalizedName;

public abstract class AbstractCommunicator {
    private String brand;
    private String model;

    @LocalizedName("Бренд")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @LocalizedName("Модель")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
