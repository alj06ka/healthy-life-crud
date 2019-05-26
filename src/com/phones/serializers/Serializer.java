package com.phones.serializers;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Serializer implements Serializable {
    private Serializable serializer;

    public Serializer(Serializable serializer) {
        this.serializer = serializer;
    }

    @Override
    public void serialize(ArrayList<Object> objectToWrite, OutputStream outputStream) {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        serializer.serialize(objectToWrite, newOutputStream);
    }

    @Override
    public ArrayList<Object> deserialize(InputStream inputStream) {
        return serializer.deserialize(inputStream);
    }
}
