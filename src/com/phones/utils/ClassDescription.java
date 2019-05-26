package com.phones.utils;

import com.phones.annotations.LocalizedName;

/**
 * Class for describing class and object of this class.
 */
public class ClassDescription {
    private final Class<?> aClass;
    private final String name;
    private Object classObject;

    public void setClassObject(Object classObject) {
        this.classObject = classObject;
    }

    public ClassDescription(Class<?> aClass) {
        this.aClass = aClass;
        this.name = getLocalName();

    }

    public ClassDescription(Object classObject) {
        this.classObject = classObject;
        this.aClass = classObject.getClass();
        this.name = getLocalName();
    }

    private String getLocalName() {
        String localName;
        if (aClass.isAnnotationPresent(LocalizedName.class)) {
            localName = aClass.getAnnotation(LocalizedName.class).value();
        } else {
            localName = aClass.getSimpleName();
        }
        return localName;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public String getName() {
        return name;
    }

    public Object getClassObject() {
        return classObject;
    }

    @Override
    public String toString() {
        return name;
    }
}
