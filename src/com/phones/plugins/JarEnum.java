package com.phones.plugins;

import java.util.Enumeration;
import java.util.Iterator;

public class JarEnum <T> implements Iterable<T>{

    private final Enumeration<T> enumeration;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public T next() {
                return enumeration.nextElement();
            }

            @Override
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }
        };
    }

    public JarEnum(Enumeration<T> enumeration){
        this.enumeration = enumeration;
    }
}
