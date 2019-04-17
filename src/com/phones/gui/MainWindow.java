package com.phones.gui;

import com.phones.phones.AbstractPhone;
import com.phones.phones.mobilePhones.CellPhone;
import com.phones.phones.mobilePhones.SmartPhone;
import com.phones.utils.ClassDescription;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MainWindow extends Application {
    private ArrayList<Class<? extends AbstractPhone>>  classList;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ObservableList<ClassDescription> classList = FXCollections.observableArrayList();
        classList.addAll(new ClassDescription(SmartPhone.class), new ClassDescription(CellPhone.class));
        primaryStage.setTitle("Phone editor");

        Label labelSelectClass = new Label("Select class: ");
        labelSelectClass.setPadding(new Insets(15));
        ChoiceBox<ClassDescription> selectClass = new ChoiceBox<>(classList);
        BorderPane topNavigation = new BorderPane();
        topNavigation.setLeft(labelSelectClass);
        topNavigation.setCenter(selectClass);

        ObservableList<ClassDescription> objectList = FXCollections.observableArrayList();
        ListView<ClassDescription> listViewClasses = new ListView<>(objectList);
        listViewClasses.setOrientation(Orientation.VERTICAL);

        HBox bottomNavigation = new HBox();
        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button removeButton = new Button("Remove");
        Insets buttonMargin = new Insets(15);
        Insets buttonPadding = new Insets(10, 20, 10, 20);

        bottomNavigation.setSpacing(5);

        addButton.setPadding(buttonPadding);
        editButton.setPadding(buttonPadding);
        removeButton.setPadding(buttonPadding);

        HBox.setMargin(addButton, buttonMargin);
        HBox.setMargin(editButton, buttonMargin);
        HBox.setMargin(removeButton, buttonMargin);

        bottomNavigation.getChildren().addAll(addButton, editButton, removeButton);

        VBox vBox = new VBox();
        vBox.getChildren().add(topNavigation);
        vBox.getChildren().add(listViewClasses);
        vBox.getChildren().add(bottomNavigation);
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void mainWindow(String[] args) {
        launch(args);
    }
}

