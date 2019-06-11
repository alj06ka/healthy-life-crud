package com.phones;

import com.phones.phones.Screen;
import com.phones.phones.Transmitter;
import com.phones.phones.mobilePhones.CellPhone;
import com.phones.phones.mobilePhones.SmartPhone;
import com.phones.phones.stationaryPhones.RadioPhone;
import com.phones.phones.stationaryPhones.WiredPhone;
import com.phones.plugins.PluginLoader;
import com.phones.plugins.PluginOptions;
import com.phones.serializers.BinarySerializer;
import com.phones.serializers.SerializerDescription;
import com.phones.serializers.TextSerializer;
import com.phones.serializers.XMLSerializer;
import com.phones.utils.ClassDescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

import static com.phones.gui.MainWindow.mainWindow;

public class Main {

    private static ObservableList<ClassDescription> classList;
    private static ObservableList<SerializerDescription> serializersList;
    private static ArrayList<PluginOptions> plugins;

    public static ObservableList<ClassDescription> getClassList() {
        return classList;
    }

    public static ObservableList<SerializerDescription> getSerializersList() {
        return serializersList;
    }

    public static ArrayList<PluginOptions> getPlugins() {
        return plugins;
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
        serializersList = FXCollections.observableArrayList();
        serializersList.addAll(
                new SerializerDescription(BinarySerializer.class, "Binary file", "bin"),
                new SerializerDescription(XMLSerializer.class, "XML file", "xml"),
                new SerializerDescription(TextSerializer.class, "Text file", "txt")
        );
        plugins = new PluginLoader().loadPlugins("plugins");
        mainWindow(args);
    }

}
