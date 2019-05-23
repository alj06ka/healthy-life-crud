package com.phones.serializers;

import com.phones.utils.FieldOptions;
import com.phones.utils.FieldsParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class TextSerializer {

    private static String handleFields(int tabLevel, Object objectToWrite, ArrayList<Object> parentObject) {
        if (objectToWrite.getClass() != null) {
            ObservableList<Object> parentObservable = FXCollections.observableArrayList(parentObject);
            StringBuilder resultString = new StringBuilder();
            ArrayList<FieldOptions> fields = FieldsParser.parseFields(objectToWrite);
            for (FieldOptions field : fields) {
                String className = field.getClassType().getName();
                String fieldName = field.getName();
                if (field.getFieldType() == FieldOptions.FieldType.OBJECT) {
                    resultString.append(addTabs(tabLevel)).append("<object ").append(fieldName).append(" ").append(className).append(">\n");
                    resultString.append(addTabs(tabLevel + 1)).append(parentObservable.indexOf(field.getFieldValue()));
                    resultString.append("\n").append(addTabs(tabLevel)).append("</object>\n");
                } else {
                    resultString.append(addTabs(tabLevel)).append("<").append(fieldName).append(" ").append(field.getFieldValue()).append(">\n");
                }
            }
            return resultString.toString();
        }
        return "null";
    }

    private static String addTabs(int tabLevel) {
        return "\t".repeat(tabLevel);
    }

    public void serialize(ArrayList<Object> objectToWrite, OutputStream outputStream) throws IOException {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream)) {
            writeObject(objectToWrite, outputStreamWriter);
        }
    }

    public ArrayList<Object> deserialize(InputStream inputStream) throws IOException {
        Scanner scanner = new Scanner(inputStream);
        String inputText = removeTabs(scanner.next());
        List<String> inputLineList = Arrays.asList(inputText.split("\n"));
        String line = inputLineList.get(0);
        while (line != null){
            Class classObject;
            Object object;
            if (getRecordType(line).equals("object")) {
                try {
                    classObject = Class.forName(getClassName(line));
                    object = classObject.newInstance();
//                    setFields(classObject, object);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return readObject();
    }

    private String getClassName(String line) {
        String[] strings = line.split(" ");
        String className = strings[2];
        className = className.replace(">", "");
        return className;
    }

    private String getRecordType(String line) {
        String[] strings = line.split(" ");
        String className = strings[0];
        className = className.replace("<", "");
        className = className.replace("\t", "");
        return className;
    }

    private String removeTabs(String fileInput) {
        return fileInput.replace("\t", "");
    }

    private void writeObject(ArrayList<Object> objectToWrite, OutputStreamWriter outputStreamWriter) throws IOException {
        writeLine(outputStreamWriter, "<list " + objectToWrite.getClass() + ">\n");
        for (Object itemObject : objectToWrite) {
            writeLine(outputStreamWriter, addTabs(1) + "<object item ");
            writeLine(outputStreamWriter, itemObject.getClass().getName() + ">\n");
            writeLine(outputStreamWriter, handleFields(2, itemObject, objectToWrite));
            writeLine(outputStreamWriter, addTabs(1) + "</object>\n");
        }
        writeLine(outputStreamWriter, "</list>");

    }

    private ArrayList<Object> readObject() throws IOException {
        ArrayList<Object> streamObject;
        return null;
    }

    private void writeLine(OutputStreamWriter outputStreamWriter, String stringToWrite) throws IOException {
        outputStreamWriter.append(stringToWrite);
    }
}