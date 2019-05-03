package com.phones.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClassDescriptionTest {

    @Test
    void testSetClassObject() {
        ClassDescription testClass = new ClassDescription(TestClass.class);
        TestClass testObject = new TestClass();
        testClass.setClassObject(testObject);
        Assertions.assertEquals(testClass.getClassObject(), testObject);
    }

    @Test
    void testGetClassObject() {
        TestClass testObject = new TestClass();
        ClassDescription testClassObject = new ClassDescription(testObject);
        Assertions.assertEquals(testClassObject.getClassObject(), testObject);
    }

    @Test
    void testGetNameIfNotSet() {
        ClassDescription testObject = new ClassDescription(TestClass.class);
        Assertions.assertEquals(testObject.getName(), "TestClass");
    }

    @Test
    void testGetNameIfNameIsSet() {
        @LocalizedName("Some name")
        class TestNamedClass{}

        ClassDescription testObject = new ClassDescription(TestNamedClass.class);
        Assertions.assertEquals(testObject.getName(), "Some name");
    }

    private class TestClass {
    }
}