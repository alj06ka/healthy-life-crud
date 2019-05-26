package com.phones.serializers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class XMLSerializer implements Serializable {

    @Override
    public void serialize(ArrayList<Object> objectToWrite, OutputStream outputStream) {
        try {
            XMLEncoder xmlEncoder = new XMLEncoder(outputStream);
            xmlEncoder.writeObject(objectToWrite);
            xmlEncoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public ArrayList<Object> deserialize(InputStream inputStream) {
        Object deserializedObject = null;
        try {
            XMLDecoder xmlDecoder = new XMLDecoder(inputStream);
            deserializedObject = xmlDecoder.readObject();
            xmlDecoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ArrayList<Object>)deserializedObject;
    }
}
