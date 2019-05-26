package com.phones.phones;

import com.phones.annotations.LocalizedName;

public abstract class AbstractPhone extends AbstractCommunicator {
    public enum Color {
        @LocalizedName("Синий")
        BLUE,
        @LocalizedName("Зелёный")
        GREEN,
        @LocalizedName("Жёлтый")
        YELLOW,
        @LocalizedName("Белый")
        WHITE,
        @LocalizedName("Чёрный")
        BLACK,
        @LocalizedName("Красный")
        RED,
        @LocalizedName("Коралловый")
        CORAL
    }

    private String number;
    private Color color;

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
