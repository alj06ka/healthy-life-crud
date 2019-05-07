package com.phones.utils;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class for creating window
 */
public class EditWindow extends Stage {

    public EditWindow(Stage parentStage, ClassDescription objectToShow) {
        initOwner(parentStage);
        initModality(Modality.APPLICATION_MODAL);
        VBox container = new VBox();
        Label classTitle = new Label(objectToShow.getName());
        classTitle.setFont(new Font("System Bold", 18));
        classTitle.setPadding(new Insets(15));
        container.getChildren().addAll(classTitle, new Separator());
        VBox objectFields = new ExtractFields(parentStage, objectToShow.getClassObject());
        container.getChildren().add(objectFields);

        HBox bottomNavigation = new HBox();
        Button addButton = new Button("Apply");
        addButton.setPadding(new Insets(5, 10, 10, 5));
        addButton.setOnAction(actionEvent -> {
            this.close();
        });

        HBox.setMargin(addButton, new Insets(10));
        bottomNavigation.getChildren().add(addButton);

        container.getChildren().add(bottomNavigation);

        Scene scene = new Scene(container);
        setScene(scene);

        showAndWait();
    }
}
