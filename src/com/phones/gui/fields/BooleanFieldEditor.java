package com.phones.gui.fields;

import com.phones.utils.FieldOptions;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;

import java.lang.reflect.InvocationTargetException;

public class BooleanFieldEditor extends FieldWrapper {

    protected CheckBox checkBox;

    public BooleanFieldEditor(Object objectToInspect, FieldOptions field) {
        checkBox = new CheckBox();
        this.fieldObject = objectToInspect;
        this.fieldOptions = field;

        Object ObjectValue = getValueFromObject(this.fieldObject);
        writeObjectToElement(ObjectValue);

        checkBox.setOnAction(actionEvent -> {
            try {
                fieldOptions.getSet().invoke(this.fieldObject, getValueFromElement());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
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
