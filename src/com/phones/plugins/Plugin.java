package com.phones.plugins;

import java.io.InputStream;
import java.io.OutputStream;

public interface Plugin {

    void compress(InputStream inputStream, OutputStream outputStream);
    void decompress(InputStream inputStream, OutputStream outputStream);
}
