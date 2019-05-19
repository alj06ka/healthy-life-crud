package com.phones.serializers;

import java.io.InputStream;
import java.io.OutputStream;

public interface Serializable {

    void serialize(Object objectToWrite, OutputStream outputStream);

    Object deserialize(InputStream inputStream);
}
