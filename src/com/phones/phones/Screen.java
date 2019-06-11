package com.phones.phones;

import com.phones.annotations.LocalizedName;

@LocalizedName("Дисплей")
public class Screen {
    private int width;
    private int height;

    public Screen() {
    }

    @LocalizedName("Ширина")
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
            this.width = width;
    }

    @LocalizedName("Высота")
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
