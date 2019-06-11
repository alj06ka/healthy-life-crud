package com.phones.gui.events;

import com.phones.Main;
import com.phones.plugins.PluginOptions;
import com.phones.serializers.SerializerDescription;
import com.phones.utils.ClassDescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class OpenFileEvent implements Executable {

    @Override
    public void run(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(parentStage);
        ArrayList<PluginOptions> plugins = Main.getPlugins();
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            String fileExtension = filePath.substring(filePath.lastIndexOf('.') + 1);
            String newFilePath = filePath;
            if (isZipped(fileExtension)) {
                String unzippedFile = filePath.substring(0, filePath.lastIndexOf('.'));
                for (PluginOptions pluginToInspect : plugins) {
                    if (pluginToInspect.getExtension().equals(fileExtension)) {
                        try {
                            fileExtension = unzippedFile.substring(unzippedFile.lastIndexOf('.') + 1);
                            newFilePath = "tmp." + fileExtension;
                            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(newFilePath));
                            pluginToInspect.getPlugin().decompress(new BufferedInputStream(new FileInputStream(filePath)), out);
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
            for (SerializerDescription serializerDescription : Main.getSerializersList()) {
                if (serializerDescription.getExtension().equals(fileExtension)) {
                    try {
                        BufferedInputStream in = new BufferedInputStream(new FileInputStream(newFilePath));
                        ArrayList<Object> deserializedList = serializerDescription.getSerializer().newInstance().deserialize(in);
                        ObservableList<ClassDescription> deserializedObservableList = FXCollections.observableArrayList();
                        for (Object deserializedObject : deserializedList) {
                            deserializedObservableList.add(new ClassDescription(deserializedObject));
                        }
                        objectListView.setItems(deserializedObservableList);
                        in.close();
                    } catch (InstantiationException | IllegalAccessException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    private boolean isZipped(String fileExtension) {
        HashSet<String> zippedTypes = new HashSet<>();
        for (PluginOptions plugin : Main.getPlugins()) {
            zippedTypes.add(plugin.getExtension());
        }
        return zippedTypes.contains(fileExtension);
    }
}
