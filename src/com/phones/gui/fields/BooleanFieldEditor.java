package com.phones.gui.fields;

import com.phones.utils.FieldOptions;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

public class BooleanFieldEditor extends FieldWrapper {
    protected CheckBox checkBox;

    public BooleanFieldEditor(Object objectToInspect, FieldOptions field) {
        checkBox = new CheckBox();
        this.fieldObject = objectToInspect;
        this.fieldOptions = field;

        Object ObjectValue = getValueFromObject(this.fieldObject);
        writeObjectToElement(ObjectValue);
    }

    @Override
    protected void writeObjectToElement(Object objectValue) {
        checkBox.setSelected((boolean) objectValue);
    }

    @Override
    public Node getControl() {
        return checkBox;
    }

    @Override
    protected Object getValueFromElement() {
        return checkBox.isSelected();
    }
}
