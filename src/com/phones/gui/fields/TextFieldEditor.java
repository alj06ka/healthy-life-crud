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
                if (this.fieldOptions.getClassType().equals(Integer.class)) {
                    if (newText.length() == 0) {
                        newText = "0";
                    }
                    fieldOptions.getSet().invoke(this.fieldObject, Integer.parseInt(newText));
                } else if (this.fieldOptions.getClassType().equals(Double.class)) {
                    if (newText.length() == 0) {
                        newText = "0";
                    }
                    fieldOptions.getSet().invoke(this.fieldObject, Double.parseDouble(newText));
                } else {
                    fieldOptions.getSet().invoke(this.fieldObject, newText);
                }
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
