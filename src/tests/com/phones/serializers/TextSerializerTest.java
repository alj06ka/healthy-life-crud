package com.phones.serializers;

import com.phones.phones.AbstractPhone;
import com.phones.phones.Screen;
import com.phones.phones.mobilePhones.CellPhone;
import com.phones.phones.mobilePhones.SmartPhone;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

class TextSerializerTest {

    private final String serializerPath = "src/tests/com/phones/serializers/";
    @Test
    void serialize() {
        ArrayList<Object> testArray = getMockedList();
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(serializerPath + "test.txt"));
            TextSerializer test = new TextSerializer();
            test.serialize(testArray, out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deserialize() {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(serializerPath + "test.txt"));
            TextSerializer test = new TextSerializer();
            ArrayList<Object> testArray = test.deserialize(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private ArrayList<Object> getMockedList() {
        ArrayList<Object> testArray = new ArrayList<>();
        SmartPhone smartPhone = new SmartPhone();
        smartPhone.setBrand("new Brand");
        smartPhone.setColor(AbstractPhone.Color.BLACK);
        smartPhone.setFaceUnlock(true);
        smartPhone.setSimSlots(2);
        Screen screen = new Screen();
        screen.setWidth(15);
        screen.setHeight(20);
        smartPhone.setResolution(screen);
        testArray.add(smartPhone);
        testArray.add(new CellPhone());
        testArray.add(screen);
        return testArray;
    }
}