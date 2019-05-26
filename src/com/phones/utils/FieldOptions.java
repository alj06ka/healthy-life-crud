package com.phones.utils;

import com.phones.annotations.LocalizedName;

import java.lang.reflect.Method;

public class FieldOptions {


    private String name;
    private Object fieldValue;
    private Class<?> classType;
    private Method get, set;

    /**
     * Class designed for holding info about class fields
     */
    public FieldOptions(String name) {
        this.name = name;
    }

    public FieldType getFieldType() {
        return FieldsParser.getFieldType(getClassType());
    }

    public String getFieldName() {
        if (get.isAnnotationPresent(LocalizedName.class)) {
            return get.getAnnotation(LocalizedName.class).value();
        }
        return getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Method getGet() {
        return get;
    }

    public void setGet(Method get) {
        this.get = get;
    }

    public Method getSet() {
        return set;
    }

    public void setSet(Method set) {
        this.set = set;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public void setClassType(Class<?> classType) {
        this.classType = classType;
    }

    public boolean isPrimitive() {
        return classType.equals(Boolean.class) | classType.equals(Integer.class) |
                classType.equals(Enum.class) | classType.equals(String.class);
    }

    public enum FieldType {
        LIST,
        OBJECT,
        BOOLEAN,
        TEXT
    }
}
