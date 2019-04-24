package com.phones.utils;

import com.phones.gui.fields.BooleanFieldEditor;
import com.phones.gui.fields.FieldWrapper;
import com.phones.gui.fields.SelectFieldEditor;
import com.phones.gui.fields.TextFieldEditor;
import javafx.scene.layout.Pane;

public class FieldGenerator extends Pane {

    public FieldGenerator(Object objectToInspect, FieldOptions field) {
        FieldWrapper newField;
        if (field.getFieldType() == FieldOptions.FieldType.TEXT) {
            newField = new TextFieldEditor(objectToInspect, field);
        } else if (field.getFieldType() == FieldOptions.FieldType.BOOLEAN) {
            newField = new BooleanFieldEditor(objectToInspect, field);
        } else if (field.getFieldType() == FieldOptions.FieldType.LIST) {
            newField = new SelectFieldEditor(objectToInspect, field);
        } else {
            newField = null;
        }
        if (newField != null) {
            getChildren().add(newField.getControl());
        }
    }
}
