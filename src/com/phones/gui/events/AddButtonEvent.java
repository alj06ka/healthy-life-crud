package com.phones.gui.events;

import com.phones.gui.ShowMessage;
import com.phones.utils.ClassDescription;
import com.phones.utils.EditWindow;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;

/**
 * Event handling on clicking "Add" button.
 * Creates new instance of selected object or
 * shows error message
 */
public class AddButtonEvent implements Executable {
    @Override
    public void run(Stage parentStage, Class<?> selectedClass, ListView<ClassDescription> objectListView) {
        try {
            Object selectClassObject = selectedClass.getConstructor().newInstance();
            ClassDescription newClassObject = new ClassDescription(selectClassObject);
            objectListView.getItems().add(newClassObject);
            new EditWindow(parentStage, newClassObject, objectListView);
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            new ShowMessage(parentStage, "Item wasn't selected");
        }
    }
}
