package com.phones.gui.fields;

import com.phones.utils.FieldOptions;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.lang.reflect.InvocationTargetException;

public class TextFieldEditor extends FieldWrapper {

    private TextField textField;

    public TextFieldEditor(Object objectToInspect, FieldOptions field) {
        this.fieldObject = objectToInspect;
        this.fieldOptions = field;
        textField = new TextField();

        Object ObjectValue = getValueFromObject(this.fieldObject);
        writeObjectToElement(ObjectValue);

        textField.textProperty().addListener((obs, oldText, newText) -> {
            try {
                fieldOptions.getSet().invoke(this.fieldObject, newText);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void writeObjectToElement(Object objectToWrite) {
        textField.setText((objectToWrite != null ? objectToWrite.toString() : ""));
    }

    @Override
    public Node getControl() {
        return textField;
    }

    @Override
    protected Object getValueFromElement() {
        return textField.getText();
    }
}
