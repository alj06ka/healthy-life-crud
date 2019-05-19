package com.phones.serializers;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Serializer implements Serializable {
    private Serializable serializer;

    public Serializer(Serializable serializer) {
        this.serializer = serializer;
    }

    @Override
    public void serialize(Object objectToWrite, OutputStream outputStream) {
        ByteArrayOutputStream newOutputStream = new ByteArrayOutputStream();
        serializer.serialize(objectToWrite, newOutputStream);
    }

    @Override
    public Object deserialize(InputStream inputStream) {
        return serializer.deserialize(inputStream);
    }
}
