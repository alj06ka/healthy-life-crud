package com.phones.gui.fields;

import com.phones.utils.FieldOptions;
import javafx.scene.Node;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.phones.utils.FieldsParser.isBoxed;

/**
 * Abstract class for defining fields that will be generated in field list
 */
public abstract class FieldWrapper {
    protected Object fieldObject;
    protected FieldOptions fieldOptions;

    Object getValueFromObject(Object fieldObject) {
        try {
            return fieldOptions.getGet().invoke(fieldObject);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    void writeValueToObject() {
        Object elementValue = getValueFromElement();
        // If element is not initialized
        if (elementValue == null) {
            elementValue = getDefaultValue();
        }

        // Trying to get class type
        try {
            if (fieldOptions.getClassType() != String.class) {
                Method valueOf = fieldOptions.getClassType().getMethod("valueOf", String.class);
                elementValue = valueOf.invoke(null, elementValue);
            }
            fieldOptions.setFieldValue(elementValue);
            fieldOptions.getSet().invoke(fieldObject, fieldOptions.getFieldValue());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object getDefaultValue() {
        Class<?> fieldClass = fieldOptions.getClassType();
        if (isBoxed(fieldClass)) {
            if (fieldClass.equals(Boolean.class)) {
                return false;
            }
            return "0";
        }
        return "";
    }

    protected abstract Object getValueFromElement();

    protected abstract void writeObjectToElement(Object objectToWrite);

    public abstract Node getControl();

}
