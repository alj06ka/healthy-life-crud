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
}
