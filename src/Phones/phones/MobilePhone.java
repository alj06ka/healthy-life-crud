package Phones.phones;

import Phones.utils.FieldName;

public abstract class MobilePhone extends AbstractPhone {
    private int simSlots;
    private Screen resolution;

    @FieldName("Количество слотов для симкарт")
    public int getSimSlots() {
        return simSlots;
    }

    public void setSimSlots(int simSlots) {
        this.simSlots = simSlots;
    }

    @FieldName("Разрешение экрана")
    public Screen getResolution() {
        return resolution;
    }

    public void setResolution(Screen resolution) {
        this.resolution = resolution;
    }

    private class Screen {
        private int width;
        private int height;

        @FieldName("Ширина")
        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        @FieldName("Высота")
        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
