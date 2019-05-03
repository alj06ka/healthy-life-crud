package com.phones.phones;

import com.phones.utils.LocalizedName;

@LocalizedName("Рация")
public class Transmitter extends AbstractCommunicator {
    private String transmitRange;
    private String waveRange;

    @LocalizedName("Радиус действия")
    public String getTransmitRange() {
        return transmitRange;
    }

    public void setTransmitRange(String transmitRange) {
        this.transmitRange = transmitRange;
    }

    @LocalizedName("Длина волны")
    public String getWaveRange() {
        return waveRange;
    }

    public void setWaveRange(String waveRange) {
        this.waveRange = waveRange;
    }
}
