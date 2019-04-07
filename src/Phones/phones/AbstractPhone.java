package Phones.phones;

import Phones.utils.LocalizedName;

public abstract class AbstractPhone {
    public enum Color {
        BLUE,
        GREEN,
        YELLOW,
        WHITE,
        BLACK,
        RED,
        CORAL
    }

    private String brand;
    private String model;
    private String number;
    private Color color;

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

    @LocalizedName("Номер")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @LocalizedName("Цвет")
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
