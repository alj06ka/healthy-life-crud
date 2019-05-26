package com.phones.utils;

import com.phones.annotations.LocalizedName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.phones.utils.FieldsParser.parseFields;

public class FieldsParserTest {

    @Test
    public void testParseFieldsCount() {
        ArrayList<FieldOptions> fields = parseFields(new ClassToInspect());

        Assertions.assertEquals(fields.size(), 2);
    }

    @Test
    public void testParseFieldsFieldNames() {
        ArrayList<FieldOptions> fields = parseFields(new ClassToInspect());
        Set<String> actualNames = new HashSet<>();
        for (FieldOptions field : fields) {
            actualNames.add(field.getName());
        }
        Set<String> expectedNames = new HashSet<>();
        expectedNames.add("Name");
        expectedNames.add("SomeValue");
        Assertions.assertEquals(actualNames, expectedNames);
    }

    @Test
    public void testParseFieldsFieldLocalizedNames() {
        ArrayList<FieldOptions> fields = parseFields(new ClassToInspect());
        Set<String> actualNames = new HashSet<>();
        for (FieldOptions field : fields) {
            actualNames.add(field.getFieldName());
        }
        Set<String> expectedNames = new HashSet<>();
        expectedNames.add("Test name");
        expectedNames.add("Test Value");
        Assertions.assertEquals(actualNames, expectedNames);
    }

    @Test
    public void testParseFieldsFieldClassType() {
        ArrayList<FieldOptions> fields = parseFields(new ClassToInspect());
        Set<Class> actualNames = new HashSet<>();
        for (FieldOptions field : fields) {
            actualNames.add(field.getClassType());
        }
        Set<Class> expectedNames = new HashSet<>();
        expectedNames.add(String.class);
        expectedNames.add(Integer.class);
        Assertions.assertEquals(actualNames, expectedNames);
    }


    class ClassToInspect {
        private String name;
        private int someValue;

        @LocalizedName("Test name")
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @LocalizedName("Test Value")
        public int getSomeValue() {
            return someValue;
        }

        public void setSomeValue(int someValue) {
            this.someValue = someValue;
        }
    }
}