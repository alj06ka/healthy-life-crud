package com.phones.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ExtractFields extends VBox {
    private Stage stage;
    private ListView<ClassDescription> objectListView;

    public ExtractFields(Stage stage, Object objectToParse, ListView<ClassDescription> objectListView) {
        this.stage = stage;
        this.objectListView = objectListView;
        getChildren().addAll(generateFields(objectToParse));
    }

    /**
     * Generate list of object fields to preview in edit window (except navigate buttons).
     */
    public ArrayList<Node> generateFields(Object objectToParse) {
        ArrayList<FieldOptions> fields = FieldsParser.parseFields(objectToParse);
        ArrayList<Node> generatedFields = new ArrayList<>();
        for (FieldOptions field : fields) {
            if (field.getFieldType() == FieldOptions.FieldType.OBJECT) {

                generatedFields.add(new Separator());
                Label titleLabel = new Label(field.getFieldName());

                titleLabel.setPadding(new Insets(10, 15, 15, 5));
                titleLabel.setFont(new Font("System Bold", 18));

                generatedFields.add(titleLabel);
                generatedFields.add(generateObjectField(objectToParse, field));
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
        BorderPane borderPane = new BorderPane();
        Insets elementInsets = new Insets(10, 15, 15, 10);
        Label textLabel = new Label(field.getFieldName());
        borderPane.setLeft(textLabel);
        Pane generatedField = new FieldGenerator(objectToInspect, field);
        borderPane.setRight(generatedField);
        BorderPane.setMargin(textLabel, elementInsets);
        BorderPane.setMargin(generatedField, elementInsets);
        return borderPane;
    }


    /**
     * Generate object field with dropdown container and add button.
     */
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
        ClassDescription selectedDefaultOption = null;
        try {
            Object objectToFind = field.getGet().invoke(objectToInspect);
            for (ClassDescription objectValue : comboBoxValues) {
                if (objectValue.getClassObject() == objectToFind) {
                    selectedDefaultOption = objectValue;
                }
            }
            comboBox.getSelectionModel().select(selectedDefaultOption);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        comboBox.setOnAction(actionEvent -> {
            ClassDescription selectedItem = comboBox.getSelectionModel().getSelectedItem();
            try {
                field.getSet().invoke(objectToInspect, selectedItem.getClassObject());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        BorderPane.setMargin(comboBox, elementInsets);
        borderPane.setLeft(comboBox);

        Button addInnerClassButton = new Button("Add");
        addInnerClassButton.setOnAction(actionEvent -> {
            try {
                Object innerObject = field.getGet().invoke(objectToInspect);
                if (innerObject == null) {
                    innerObject = field.getGet().getReturnType().getDeclaredConstructor().newInstance();
                    ClassDescription innerObjectDescribed = new ClassDescription(innerObject);
                    objectListView.getItems().add(innerObjectDescribed);
                    comboBoxValues.add(innerObjectDescribed);
                    new EditWindow(stage, innerObjectDescribed, objectListView);
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
