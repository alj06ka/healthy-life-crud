package com.phones.serializers;

import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

class TextSerializerTest {

    @Test
    void serialize() {
        TestClass testObject = new TestClass();
        testObject.setName("The best name ever");
        testObject.setValue(100004);
        TestClass1 testClass1 = new TestClass1();
        testClass1.setName("New name");
        testObject.setObject(testClass1);
        ArrayList<Object> testArray = new ArrayList<>();
        testArray.add(testObject);
        testArray.add(testClass1);
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("test.txt"));
            TextSerializer serializer = new TextSerializer();
            serializer.serialize(testArray, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deserialize() {
        new TestClass();
    }

    @Test
    void serialize1() {
    }

    @Test
    void deserialize1() {
    }

    public class TestClass {
        private String name;
        private int value;
        private TestClass1 object;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public TestClass1 getObject() {
            return object;
        }

        public void setObject(TestClass1 object) {
            this.object = object;
        }
    }

    public class TestClass1 {
        private String name1;

        public String getName() {
            return name1;
        }

        public void setName(String name) {
            this.name1 = name;
        }
    }
}