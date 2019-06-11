package com.phones.gui;

import com.phones.Main;
import com.phones.plugins.PluginOptions;
import com.phones.serializers.Serializable;
import com.phones.serializers.SerializerDescription;
import com.phones.utils.ClassDescription;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class FileExtensionChooser extends Stage {
    public FileExtensionChooser(Stage parentStage, ListView<ClassDescription> list, String savePath) {
        initOwner(parentStage);
        initModality(Modality.APPLICATION_MODAL);

        VBox container = new VBox();

        VBox messageBox = new VBox();
        ChoiceBox<SerializerDescription> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(Main.getSerializersList());
        messageBox.getChildren().add(choiceBox);
        ChoiceBox<PluginOptions> pluginsBox = new ChoiceBox<>();
        pluginsBox.setItems(FXCollections.observableArrayList(Main.getPlugins()));
        messageBox.getChildren().add(pluginsBox);
        BorderPane.setMargin(choiceBox, new Insets(30));
        BorderPane.setMargin(pluginsBox, new Insets(30));
        Button closeButton = new Button("Ok");
        closeButton.setPadding(new Insets(5));
        closeButton.setOnAction(actionEvent -> {
            try {
                SerializerDescription serializerDescription = choiceBox.getSelectionModel().getSelectedItem();
                Serializable serializer = serializerDescription.getSerializer().newInstance();
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(savePath + '.' + serializerDescription.getExtension()));
                ArrayList<Object> listToSerialize = new ArrayList<>();
                for (ClassDescription objectToSerialize : list.getItems()) {
                    listToSerialize.add(objectToSerialize.getClassObject());
                }
                serializer.serialize(listToSerialize, outputStream);
                outputStream.close();
                PluginOptions selectedPlugin = pluginsBox.getSelectionModel().getSelectedItem();
                if (selectedPlugin != null){
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(savePath + '.' + serializerDescription.getExtension()));
                    selectedPlugin.getPlugin().compress(in, new BufferedOutputStream(new FileOutputStream(savePath + '.' + serializerDescription.getExtension() + '.' + selectedPlugin.getExtension())));
                    in.close();
                }

            } catch (InstantiationException | IllegalAccessException | IOException e) {
                e.printStackTrace();
            }
            this.close();
        });
        VBox.setMargin(closeButton, new Insets(10));
        container.getChildren().addAll(messageBox, closeButton);
        Scene scene = new Scene(container);
        setScene(scene);

        showAndWait();
    }
}
