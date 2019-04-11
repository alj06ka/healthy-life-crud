package Phones.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Parses methods and it's names from class.
 */
public class FieldsParser {
    /**
     * Map for getting primitive types
     */
    private static Map<Class<?>, Class<?>> PRIMITIVE_TYPE = new HashMap<>();

    static {
        PRIMITIVE_TYPE.put(Boolean.class, boolean.class);
        PRIMITIVE_TYPE.put(Integer.class, int.class);
        PRIMITIVE_TYPE.put(Byte.class, byte.class);
        PRIMITIVE_TYPE.put(Character.class, char.class);
        PRIMITIVE_TYPE.put(Short.class, short.class);
        PRIMITIVE_TYPE.put(Long.class, long.class);
        PRIMITIVE_TYPE.put(Float.class, float.class);
        PRIMITIVE_TYPE.put(Double.class, double.class);
    }

    private static Class<?> getWrapperType(Class<?> primitiveType) {
        for (Map.Entry<Class<?>, Class<?>> entry : PRIMITIVE_TYPE.entrySet()) {
            if (primitiveType.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Parses list of fields in class by inspecting all available methods.
     *
     * @param objectToParse object of class to parse fields
     * @return ArrayList contains options of every field in this class object
     */
    public static ArrayList<FieldOptions> parseFields(Object objectToParse) {
        ArrayList<FieldOptions> classFields = new ArrayList<>();
        HashMap<String, FieldOptions> fields = new HashMap<>();

        Method[] methods = objectToParse.getClass().getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (isSet(methodName) || isGet(methodName)) {
                String fieldName = getFieldName(methodName);
                boolean isNewField = !fields.containsKey(fieldName);
                FieldOptions fieldOptions = isNewField ? new FieldOptions(fieldName) : fields.get(fieldName);
                if (isGet(methodName)) {
                    try {
                        Object fieldValue = method.invoke(objectToParse);
                        fieldOptions.setFieldValue(fieldValue);
                        Class<?> classType = method.getReturnType();
                        fieldOptions.setClassType(classType);
                        if (classType.isPrimitive()) {
                            classType = getWrapperType(classType);
                        }
                        fieldOptions.setClassType(classType);
                        fieldOptions.setGet(method);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    fieldOptions.setSet(method);
                }
                if (isNewField) {
                    fields.put(fieldName, fieldOptions);
                } else {
                    classFields.add(fieldOptions);
                }
            }
        }
        return classFields;
    }

    /**
     * Getting field name from method name.
     * Will suggest that method name equals
     * "set(fieldName)", "get(fieldName)" or "is(fieldName)".
     *
     * @param methodName: name of method to find field.
     * @return fieldName of this method (String).
     */
    private static String getFieldName(String methodName) {
        if (methodName.startsWith("is")) {
            return methodName.substring("is".length());
        } else if (methodName.startsWith("get") || methodName.startsWith("set")) {
            return methodName.substring(3);
        } else return methodName;
    }

    /**
     * Checking field name if method looks like setter
     */
    private static boolean isSet(String methodName) {
        return methodName.startsWith("set");
    }

    /**
     * Checking field name if method looks like getter or boolean getter
     */
    private static boolean isGet(String methodName) {
        return methodName.startsWith("get") || methodName.startsWith("is");
    }
}
