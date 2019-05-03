package com.phones.phones.stationaryPhones;

import com.phones.phones.StationaryPhone;
import com.phones.utils.LocalizedName;

@LocalizedName("Радио телефон")
public class RadioPhone extends StationaryPhone {
    private String waveRange;
    private String signalRange;

    @LocalizedName("Диапазон волн")
    public String getWaveRange() {
        return waveRange;
    }

    public void setWaveRange(String waveRange) {
        this.waveRange = waveRange;
    }

    @LocalizedName("Радиус действия")
    public String getSignalRange() {
        return signalRange;
    }

    public void setSignalRange(String signalRange) {
        this.signalRange = signalRange;
    }
}
