package com.phones.phones.mobilePhones;

import com.phones.phones.MobilePhone;
import com.phones.utils.LocalizedName;

@LocalizedName("Кнопочный телефон")
public class CellPhone extends MobilePhone {
    private boolean isFlashlight;
    private String buttonsCount;

    @LocalizedName("Наличие фонарика")
    public boolean isFlashlight() {
        return isFlashlight;
    }

    public void setFlashlight(boolean flashlight) {
        isFlashlight = flashlight;
    }

    @LocalizedName("Количество кнопок")
    public String getButtonsCount() {
        return buttonsCount;
    }

    public void setButtonsCount(String buttonsCount) {
        this.buttonsCount = buttonsCount;
    }
}
