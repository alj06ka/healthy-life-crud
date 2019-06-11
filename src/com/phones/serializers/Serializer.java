package com.phones.serializers;

import com.phones.plugins.Plugin;

import java.io.*;
import java.util.ArrayList;

public class Serializer implements Serializable {

    private final Serializer serializer;
    private final Plugin plugin;

    public Serializer(Serializer serializer, Plugin plugin){
        this.serializer = serializer;
        this.plugin = plugin;
    }

    @Override
    public void serialize(ArrayList<Object> objectToWrite, OutputStream outputStream) {
        ByteArrayOutputStream tmpOutputStream = new ByteArrayOutputStream();
        serializer.serialize(objectToWrite, tmpOutputStream);
        plugin.compress(new ByteArrayInputStream(tmpOutputStream.toByteArray()), outputStream);

    }

    @Override
    public ArrayList<Object> deserialize(InputStream inputStream) {
        ByteArrayOutputStream tmpOutputStream = new ByteArrayOutputStream();
        plugin.decompress(inputStream, tmpOutputStream);

        return serializer.deserialize(new ByteArrayInputStream(tmpOutputStream.toByteArray()));
    }
}
