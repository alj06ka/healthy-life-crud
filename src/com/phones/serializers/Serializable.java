package com.phones.serializers;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public interface Serializable {

    void serialize(ArrayList<Object> objectToWrite, OutputStream outputStream);

    ArrayList<Object> deserialize(InputStream inputStream);
}
