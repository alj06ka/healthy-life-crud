package com.phones.utils;

import com.phones.phones.AbstractPhone;

public class ClassDescription {
    private final Class<? extends AbstractPhone> aClass;
    private final String name;
    private AbstractPhone classObject;

    public ClassDescription(Class<? extends AbstractPhone> aClass) {
        this.aClass = aClass;
        this.name = getLocalName();

    }

    public ClassDescription(AbstractPhone classObject) {
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

    public AbstractPhone getClassObject() {
        return classObject;
    }

    @Override
    public String toString() {
        return name;
    }
}
