package com.phones.gui.fields;

import com.phones.utils.FieldOptions;
import com.phones.utils.LocalizedName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.phones.utils.FieldsParser.parseFields;

public class SelectFieldEditorTest {

    @Test
    public void testGetValueFromElement() {
    }

    @Test
    public void testWriteObjectToElement() {
    }

    @Test
    public void testSelectFieldEditor() {
        ClassToInspect objectToInspect = new ClassToInspect();
        ArrayList<FieldOptions> fields = parseFields(objectToInspect);
        for (FieldOptions field : fields) {
            if (field.getFieldType() == FieldOptions.FieldType.LIST) {
                SelectFieldEditor selectFieldEditor = new SelectFieldEditor(objectToInspect, field);
                System.out.println(selectFieldEditor);
            }

        }

    }

    public enum TestEnum {
        @LocalizedName("First field")
        FIRST_FIELD,
        @LocalizedName("Second field")
        SECOND_FIELD,
        @LocalizedName("Third field")
        THIRT_FIELD
    }

    public class ClassToInspect {
        private String name;
        private int someValue;
        private TestEnum testEnum;

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

        @LocalizedName("Test enum")
        public TestEnum getTestEnum() {
            return testEnum;
        }

        public void setTestEnum(TestEnum testEnum) {
            this.testEnum = testEnum;
        }
    }
}