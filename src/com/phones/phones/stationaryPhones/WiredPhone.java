package com.phones.phones.stationaryPhones;

import com.phones.phones.StationaryPhone;
import com.phones.annotations.LocalizedName;

@LocalizedName("Проводной телефон")
public class WiredPhone extends StationaryPhone {
    private String CableLength;
    private boolean isCableConnected;

    @LocalizedName("Длина провода")
    public String getCableLength() {
        return CableLength;
    }

    public void setCableLength(String cableLength) {
        CableLength = cableLength;
    }

    @LocalizedName("Подключен провод")
    public boolean isCableConnected() {
        return isCableConnected;
    }

    public void setCableConnected(boolean cableConnected) {
        isCableConnected = cableConnected;
    }
}
