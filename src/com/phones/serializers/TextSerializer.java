package com.phones.serializers;

import com.phones.utils.FieldOptions;
import com.phones.utils.FieldsParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSerializer implements Serializable {

    private ArrayList<DependencyRecord> dependencyRestorer;

    @Override
    public void serialize(ArrayList<Object> ListToSerialize, OutputStream outputStream) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        try {
            for (Object object : ListToSerialize) {
                String iniObject = parseToIni(object, ListToSerialize);
                outputStreamWriter.write(iniObject);
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseToIni(Object objectToSerialize, ArrayList<Object> listToSerialize) {
        ObservableList<Object> objectObservableList = FXCollections.observableList(listToSerialize);
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<FieldOptions> fields = FieldsParser.parseFields(objectToSerialize);
        stringBuilder.append('[').append(objectToSerialize.getClass().getName()).append("]\n");
        for (FieldOptions field : fields) {
            String fieldSetter = field.getSet().getName();
            FieldOptions.FieldType fieldType = field.getFieldType();
            String fieldValue;
            if (fieldType.equals(FieldOptions.FieldType.OBJECT)) {
                int fieldPosition = objectObservableList.indexOf(field.getFieldValue());
                fieldValue = String.valueOf(fieldPosition);
            } else {
                fieldValue = String.valueOf(field.getFieldValue());
            }
            stringBuilder.append(fieldSetter).append('=').append(fieldValue).append('\n');
        }
        stringBuilder.append('\n');
        return stringBuilder.toString();
    }

    @Override
    public ArrayList<Object> deserialize(InputStream inputStream) {
        ArrayList<Object> resultObjectList = new ArrayList<>();
        dependencyRestorer = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            Object currentObject = null;
            HashMap<String, String> fields = new HashMap<>();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[")) {
                    Pattern pattern = Pattern.compile("\\[(.*)]");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String className = matcher.group(1);
                        currentObject = Class.forName(className).newInstance();
                    }
                } else if (!line.equals("")) {
                    String[] splitString = line.split("=", 2);
                    String fieldName = splitString[0];
                    String fieldValue = splitString[1];
                    fields.put(fieldName, fieldValue);
                } else {
                    fillObjectFields(currentObject, fields);
                    resultObjectList.add(currentObject);
                    currentObject = null;
                    fields = new HashMap<>();
                }
            }
            restoreDependencies(resultObjectList);
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return resultObjectList;
    }

    private void fillObjectFields(Object objectToFill, HashMap<String, String> fields) {
        ArrayList<FieldOptions> objectFields = FieldsParser.parseFields(objectToFill);
        try {
            for (FieldOptions objectField : objectFields) {
                String fieldSetterName = objectField.getSet().getName();
                String fieldValue = fields.get(fieldSetterName);
                setField(objectToFill, objectField, fieldValue);
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void setField(Object object, FieldOptions field, String stringValue) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class parameterType = field.getSet().getParameterTypes()[0];
        Object fieldValue = null;
        if (field.getFieldType() == FieldOptions.FieldType.OBJECT) {
            this.dependencyRestorer.add(new DependencyRecord(object, field.getSet(), Integer.parseInt(stringValue)));
            return;
        }
        if (!stringValue.equals("null"))
            fieldValue = createObject(parameterType, stringValue);
        if (fieldValue != null) {
            field.getSet().invoke(object, fieldValue);
        }
    }

    private Object createObject(Class className, String value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<Class, Class> PRIMITIVE_TYPE = new HashMap<>();
        PRIMITIVE_TYPE.put(boolean.class, Boolean.class);
        PRIMITIVE_TYPE.put(int.class, Integer.class);
        PRIMITIVE_TYPE.put(float.class, Float.class);
        PRIMITIVE_TYPE.put(double.class, Double.class);

        if (className != String.class) {
            if (PRIMITIVE_TYPE.containsKey(className)) {
                className = PRIMITIVE_TYPE.get(className);
            }
            Method objectValue = className.getMethod("valueOf", String.class);
            return objectValue.invoke(null, value);
        } else {
            return value;
        }
    }

    private void restoreDependencies(ArrayList<Object> listToRestore) throws InvocationTargetException, IllegalAccessException {
        for (DependencyRecord dependencyRestore : this.dependencyRestorer) {
            Method setter = dependencyRestore.getFieldSetter();
            Object objectToRestore = dependencyRestore.getObject();
            int id = dependencyRestore.getId();
            if ((id >= 0) && (id < listToRestore.size())) {
                Object linkedObject = listToRestore.get(id);
                setter.invoke(objectToRestore, linkedObject);
            } else if (id != -1) {
                throw new IndexOutOfBoundsException("Object link was corrupted");
            }
        }
    }

    private class DependencyRecord {
        private Object object;
        private Method fieldSetter;
        private int id;

        DependencyRecord(Object object, Method fieldSetter, int id) {
            this.object = object;
            this.fieldSetter = fieldSetter;
            this.id = id;
        }

        public Object getObject() {
            return object;
        }

        public Method getFieldSetter() {
            return fieldSetter;
        }

        public int getId() {
            return id;
        }
    }

}
