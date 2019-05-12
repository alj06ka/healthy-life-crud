package com.phones.utils;

import com.phones.gui.fields.BooleanFieldEditor;
import com.phones.gui.fields.FieldWrapper;
import com.phones.gui.fields.SelectFieldEditor;
import com.phones.gui.fields.TextFieldEditor;
import javafx.scene.layout.Pane;

public class FieldGenerator extends Pane {

    /**
     * Creates GUI object depends on field type.
     */
    public FieldGenerator(Object objectToInspect, FieldOptions field) {
        FieldWrapper newField;
        switch (field.getFieldType()) {
            case TEXT:
                newField = new TextFieldEditor(objectToInspect, field);
                break;
            case BOOLEAN:
                newField = new BooleanFieldEditor(objectToInspect, field);
                break;
            case LIST:
                newField = new SelectFieldEditor(objectToInspect, field);
                break;
            default:
                newField = null;
        }

        if (newField != null) {
            getChildren().add(newField.getControl());
        }
    }
}
