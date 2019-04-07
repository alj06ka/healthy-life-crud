package Phones.phones.mobilePhones;

import Phones.phones.MobilePhone;
import Phones.utils.LocalizedName;

@LocalizedName("Смартфон")
public class SmartPhone extends MobilePhone {
    private boolean fourGSupport;
    private boolean waterResistant;
    private boolean touchID;
    private boolean faceId;
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
    public boolean isTouchID() {
        return touchID;
    }

    @LocalizedName("Разблокировка по лицу")
    public boolean isFaceId() {
        return faceId;
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

    public void setTouchID(boolean touchID) {
        this.touchID = touchID;
    }

    public void setFaceId(boolean faceId) {
        this.faceId = faceId;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
