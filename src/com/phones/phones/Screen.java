package com.phones.phones;

import com.phones.utils.LocalizedName;

import static java.lang.Integer.parseInt;

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

    public void setWidth(String width) {
        if (width.length() > 0) {
            this.width = parseInt(width);
        } else {
            this.width = 0;
        }
    }

    @LocalizedName("Высота")
    public int getHeight() {
        return height;
    }

    public void setHeight(String height) {
        if (height.length() > 0){
            this.height = parseInt(height);
        } else {
            this.height = 0;
        }
    }
}
