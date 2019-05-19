package com.phones.serializers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.InputStream;
import java.io.OutputStream;

public class XMLSerializer implements Serializable {
    @Override
    public void serialize(Object objectToWrite, OutputStream outputStream) {
        try {
            XMLEncoder xmlEncoder = new XMLEncoder(outputStream);
            xmlEncoder.writeObject(objectToWrite);
            xmlEncoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object deserialize(InputStream inputStream) {
        Object deserializedObject = null;
        try {
            XMLDecoder xmlDecoder = new XMLDecoder(inputStream);
            deserializedObject = xmlDecoder.readObject();
            xmlDecoder.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deserializedObject;
    }
}
