package com.phones.gui.fields;

import com.phones.utils.FieldOptions;
import com.phones.utils.LocalizedName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Class for handling Enum fields in a gui
 */
public class SelectFieldEditor extends FieldWrapper {

    private ComboBox<ComboBoxItem> comboBox;
    private ObservableList<ComboBoxItem> enumFields;

    public SelectFieldEditor(Object objectToInspect, FieldOptions field) {
        comboBox = new ComboBox<>();
        this.fieldObject = objectToInspect;
        this.fieldOptions = field;

        // Only Enum fields are supported
        // To-Do: add list support
        enumFields = FXCollections.observableArrayList();
        if (field.getClassType().isEnum()) {
            Enum[] enumValues = (Enum[]) fieldOptions.getClassType().getEnumConstants();
            for (Enum enumValue : enumValues) {
                enumFields.add(new ComboBoxItem(enumValue));
            }
            comboBox.setItems(enumFields);
            Object ObjectValue = getValueFromObject(this.fieldObject);
            writeObjectToElement(ObjectValue);
        }

        comboBox.setOnAction(actionEvent -> {
            try {
                fieldOptions.getSet().invoke(this.fieldObject, getValueFromElement());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    protected Object getValueFromElement() {
        return comboBox.getValue().enumField;
    }

    @Override
    protected void writeObjectToElement(Object objectToWrite) {
        ComboBoxItem foundItem = null;
        for (ComboBoxItem item : enumFields) {
            if (item.getEnumField() == objectToWrite) {
                foundItem = item;
            }
        }
        comboBox.getSelectionModel().select(foundItem);
    }

    @Override
    public Node getControl() {
        return comboBox;
    }

    /**
     * Class for describing combobox items and showing class
     * names as field value for combobox.
     */
    private class ComboBoxItem {
        private Enum enumField;

        ComboBoxItem(Enum enumField) {
            this.enumField = enumField;
        }

        Enum getEnumField() {
            return enumField;
        }

        @Override
        public String toString() {
            Class<? extends Enum> enumType = enumField.getClass();
            String localizedName = enumField.toString();
            try {
                Field enumConstant = enumType.getField(localizedName);
                if (enumConstant.isAnnotationPresent(LocalizedName.class)) {
                    localizedName = enumConstant.getAnnotation(LocalizedName.class).value();
                }
            } catch (NoSuchFieldException ignored) {
            }
            return localizedName;
        }
    }
}
