package com.phones.gui;

import com.phones.gui.events.AddButtonEvent;
import com.phones.gui.events.EditButtonEvent;
import com.phones.gui.events.Executable;
import com.phones.gui.events.RemoveButtonEvent;
import com.phones.utils.ClassDescription;
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
            execute(new AddButtonEvent(), primaryStage, selectClass, objectListView);
        });

        editButton.setOnAction(actionEvent -> {
            execute(new EditButtonEvent(), primaryStage, selectClass, objectListView);
        });

        removeButton.setOnAction(actionEvent -> {
            execute(new RemoveButtonEvent(), primaryStage, selectClass, objectListView);
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

    private void execute(Executable ButtonEvent, Stage primaryStage,
                         ChoiceBox<ClassDescription> selectedClass,
                         ListView<ClassDescription> objectListView) {
        ButtonEvent.run(primaryStage, selectedClass.getValue().getaClass(), objectListView);
    }

    public static void mainWindow(String[] args) {
        launch(args);
    }

}

