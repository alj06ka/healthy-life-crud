package com.phones.utils;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ExtractFields extends VBox {

    public ExtractFields(Object objectToParse) {
        getChildren().addAll(generateFields(objectToParse));
    }

    /**
     * Generate list of object fields to preview
     */
    public ArrayList<Node> generateFields(Object objectToParse) {
        ArrayList<FieldOptions> fields = FieldsParser.parseFields(objectToParse);
        ArrayList<Node> generatedFields = new ArrayList<>();
        for (FieldOptions field : fields) {
            if (field.getFieldType() == FieldOptions.FieldType.OBJECT) {
                generatedFields.add(new Separator());
                generatedFields.add(new Label(field.getFieldName()));
                try {
                    Object innerObject = field.getGet().invoke(objectToParse);
                    if (innerObject == null){
                        innerObject = field.getGet().getReturnType().getDeclaredConstructor().newInstance();
                        field.getSet().invoke(objectToParse, innerObject);
                    }
                    generatedFields.addAll(generateFields(innerObject));
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
                generatedFields.add(new Separator());
            } else {
                generatedFields.add(generatePrimitiveField(objectToParse, field));
            }
        }
        return generatedFields;
    }

    /**
     * Generate simple field like the following:
     * *Label*     *Component*
     */
    private Node generatePrimitiveField(Object objectToInspect, FieldOptions field) {
        GridPane gridPane = new GridPane();
        gridPane.add(new Label(field.getFieldName()), 0, 0);
        // Add field generating here
        Node generatedField = new FieldGenerator(objectToInspect, field);
        return gridPane;
    }
}
