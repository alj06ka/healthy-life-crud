package com.phones.phones.mobilePhones;

import com.phones.phones.MobilePhone;
import com.phones.annotations.LocalizedName;

@LocalizedName("Смартфон")
public class SmartPhone extends MobilePhone {
    private boolean fourGSupport;
    private boolean waterResistant;
    private boolean fingerUnlock;
    private boolean faceUnlock;
    private String operatingSystem;

    @LocalizedName("4G")
    public boolean isFourGSupport() {
        return fourGSupport;
    }

    @LocalizedName("Влагозащита")
    public boolean isWaterResistant() {
        return waterResistant;
    }

    @LocalizedName("Сканер отпечатков пальцев")
    public boolean isFingerUnlock() {
        return fingerUnlock;
    }

    @LocalizedName("Разблокировка по лицу")
    public boolean isFaceUnlock() {
        return faceUnlock;
    }

    @LocalizedName("Операционная система")
    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setFourGSupport(boolean fourGSupport) {
        this.fourGSupport = fourGSupport;
    }

    public void setWaterResistant(boolean waterResistant) {
        this.waterResistant = waterResistant;
    }

    public void setFingerUnlock(boolean fingerUnlock) {
        this.fingerUnlock = fingerUnlock;
    }

    public void setFaceUnlock(boolean faceUnlock) {
        this.faceUnlock = faceUnlock;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
