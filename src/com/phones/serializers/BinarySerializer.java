package com.phones.serializers;

import java.io.*;

public class BinarySerializer implements Serializable {
    @Override
    public void serialize(Object objectToWrite, OutputStream outputStream) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(objectToWrite);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(InputStream inputStream) {
        Object deserializedObject = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            deserializedObject = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedObject;
    }
}
