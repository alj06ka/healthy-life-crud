package com.phones;

import com.phones.phones.Screen;
import com.phones.phones.Transmitter;
import com.phones.phones.mobilePhones.CellPhone;
import com.phones.phones.mobilePhones.SmartPhone;
import com.phones.phones.stationaryPhones.RadioPhone;
import com.phones.phones.stationaryPhones.WiredPhone;
import com.phones.utils.ClassDescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import static com.phones.gui.MainWindow.mainWindow;

public class Main {

    public static ListView<ClassDescription> objectListView;

    private static ObservableList<ClassDescription> classList;

    public static ObservableList<ClassDescription> getClassList() {
        return classList;
    }

    public static void main(String[] args) {
        classList = FXCollections.observableArrayList();
        classList.addAll(
                new ClassDescription(SmartPhone.class),
                new ClassDescription(CellPhone.class),
                new ClassDescription(Screen.class),
                new ClassDescription(RadioPhone.class),
                new ClassDescription(WiredPhone.class),
                new ClassDescription(Transmitter.class)
        );

        mainWindow(args);
    }

}
