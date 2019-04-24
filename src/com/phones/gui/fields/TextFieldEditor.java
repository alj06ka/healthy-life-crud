package com.phones.gui.fields;

import com.phones.utils.FieldOptions;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class TextFieldEditor extends FieldWrapper {
    private TextField textField;

    public TextFieldEditor(Object objectToInspect, FieldOptions field) {
        this.fieldObject = objectToInspect;
        this.fieldOptions = field;
        textField = new TextField();

        Object ObjectValue = getValueFromObject(this.fieldObject);
        writeObjectToElement(ObjectValue);
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
