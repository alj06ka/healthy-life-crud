package com.phones.gui.events;

import com.phones.utils.ClassDescription;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public interface Executable {
    public void run(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView);
}
