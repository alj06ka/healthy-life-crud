package com.phones.utils;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    public Node generatePrimitiveField(Object objectToInspect, FieldOptions field) {
        GridPane gridPane = new GridPane();
        gridPane.add(new Label(field.getFieldName()), 0, 0);
        // Add field generating here
        Pane generatedField = new FieldGenerator(objectToInspect, field);
        gridPane.add(generatedField, 1, 0);
        return gridPane;
    private Node generateObjectField(Object objectToInspect, FieldOptions field) {
        BorderPane borderPane = new BorderPane();
        Insets elementInsets = new Insets(10, 15, 15, 10);
        ObservableList<ClassDescription> comboBoxValues = FXCollections.observableArrayList();
        for (ClassDescription objectValue : objectListView.getItems()) {
            if (objectValue.getaClass() == field.getClassType()) {
                comboBoxValues.add(objectValue);
            }
        }
        ComboBox<ClassDescription> comboBox = new ComboBox<>(comboBoxValues);
        BorderPane.setMargin(comboBox, elementInsets);
        borderPane.setLeft(comboBox);

        Button addInnerClassButton = new Button("Add");
        addInnerClassButton.setOnAction(actionEvent -> {
            try {
                Object innerObject = field.getGet().invoke(objectToInspect);
                if (innerObject == null){
                    innerObject = field.getGet().getReturnType().getDeclaredConstructor().newInstance();
                    ClassDescription innerObjectDescribed = new ClassDescription(innerObject);
                    objectListView.getItems().add(innerObjectDescribed);
                    comboBoxValues.add(innerObjectDescribed);
                    new EditWindow(stage, innerObjectDescribed);
                }
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
        BorderPane.setMargin(addInnerClassButton, elementInsets);
        borderPane.setRight(addInnerClassButton);

        return borderPane;
    }
}
