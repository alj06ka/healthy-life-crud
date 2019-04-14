package com.phones.phones;

import com.phones.utils.LocalizedName;

public abstract class MobilePhone extends AbstractPhone {
    private int simSlots;
    private Screen resolution;

    @LocalizedName("Количество слотов для симкарт")
    public int getSimSlots() {
        return simSlots;
    }

    public void setSimSlots(int simSlots) {
        this.simSlots = simSlots;
    }

    @LocalizedName("Разрешение экрана")
    public Screen getResolution() {
        return resolution;
    }

    public void setResolution(Screen resolution) {
        this.resolution = resolution;
    }

    private class Screen {
        private int width;
        private int height;

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
}
