package com.phones.gui.events;

import com.phones.utils.ClassDescription;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class RemoveButtonEvent implements Executable {
    @Override
    public void run(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        objectListView.getItems().remove(objectListView.getSelectionModel().getSelectedItem());
    }
}
