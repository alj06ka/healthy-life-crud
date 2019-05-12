package com.phones.gui.events;

import com.phones.gui.ShowMessage;
import com.phones.utils.ClassDescription;
import com.phones.utils.EditWindow;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Event handling on clicking "Edit" button.
 * Loads selected object on gui form or
 * shows error message if object wasn't selected.
 */
public class EditButtonEvent implements Executable {
    @Override
    public void run(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        ClassDescription selectedItem = objectListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            new EditWindow(parentStage, selectedItem, objectListView);
        } else {
            new ShowMessage(parentStage, "Item wasn't selected");
        }
    }
}
