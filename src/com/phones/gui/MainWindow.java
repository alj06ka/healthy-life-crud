package com.phones.gui;

import com.phones.utils.ClassDescription;
import com.phones.utils.EditWindow;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import static com.phones.Main.getClassList;


public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ObservableList<ClassDescription> classList = getClassList();
        primaryStage.setTitle("Phone editor");

        ListView<ClassDescription> objectListView = new ListView<>();
        objectListView.setOrientation(Orientation.VERTICAL);

        Label labelSelectClass = new Label("Select class: ");
        labelSelectClass.setPadding(new Insets(15));

        ChoiceBox<ClassDescription> selectClass = new ChoiceBox<>(classList);

        BorderPane topNavigation = new BorderPane();
        topNavigation.setLeft(labelSelectClass);
        topNavigation.setCenter(selectClass);

        HBox bottomNavigation = new HBox();
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button removeButton = new Button("Remove");
        Insets buttonMargin = new Insets(15);
        Insets buttonPadding = new Insets(10, 20, 10, 20);

        addButton.setOnAction(actionEvent -> {
            try {
                Object selectClassObject = selectClass.getValue().getaClass().getConstructor().newInstance();
                ClassDescription newClassObject = new ClassDescription(selectClassObject);
                objectListView.getItems().add(newClassObject);
                new EditWindow(primaryStage, newClassObject);
            } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        editButton.setOnAction(actionEvent -> {
            ClassDescription selectedItem = objectListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                new EditWindow(primaryStage, selectedItem);
            }
        });

        removeButton.setOnAction(actionEvent -> {
            objectListView.getItems().remove(objectListView.getSelectionModel().getSelectedItem());
        });

        bottomNavigation.setSpacing(5);

        addButton.setPadding(buttonPadding);
        editButton.setPadding(buttonPadding);
        removeButton.setPadding(buttonPadding);

        HBox.setMargin(addButton, buttonMargin);
        HBox.setMargin(editButton, buttonMargin);
        HBox.setMargin(removeButton, buttonMargin);

        bottomNavigation.getChildren().addAll(addButton, editButton, removeButton);

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        menuBar.getMenus().add(menuFile);
        MenuItem menuItem = new MenuItem("Save as..");
        menuItem.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(primaryStage);
        });
        menuFile.getItems().add(menuItem);

        VBox vBox = new VBox();
        vBox.getChildren().add(menuBar);
        vBox.getChildren().add(topNavigation);
        vBox.getChildren().add(objectListView);
        vBox.getChildren().add(bottomNavigation);
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void mainWindow(String[] args) {
        launch(args);
    }

}

