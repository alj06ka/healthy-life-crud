package com.phones.gui.events;

import com.phones.gui.FileExtensionChooser;
import com.phones.utils.ClassDescription;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SaveFileEvent implements Executable {

    @Override
    public void run(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        File selectedFile = fileChooser.showSaveDialog(parentStage);
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            new FileExtensionChooser(parentStage, objectListView, filePath);
        }
    }
}
