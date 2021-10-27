package com.nagarro.NagData.map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    static HashMap<String, Float> map;

    final static Float[] marks = {100f, 91.3f, 98.8f, 69.42f};
    final static String[] names = {"Manan", "John", "Mary", "Aryan"};

    @BeforeEach
    void setUp() {
        map = new HashMap<>();
        for (int i = 0; i < marks.length; i++)
            map.put(names[i], marks[i]);
    }

    @Test
    void size() {
        assertEquals(marks.length, map.size());
    }

    @Test
    void get() {
        for (int name = 0; name < names.length; name++)
            assertEquals(marks[name], map.get(names[name]));
    }

    @Test
    void contains() {
        int randomNameIdx = ThreadLocalRandom.current().nextInt(0, names.length);
        assertTrue(map.contains(names[randomNameIdx]));

        map.remove(names[randomNameIdx]);
        assertFalse(map.contains(names[randomNameIdx]));
    }

    @Test
    void iterator() {
        for (MapEntry<String, Float> student : map) {
            String name = student.getKey();
            Float marks = student.getValue();
            assertEquals(getMarksFor(name), marks);
        }
    }

    private Float getMarksFor(String name) {
        int pos = 0;
        for (int idx = 0; idx < names.length; idx++) {
            if (Objects.equals(name, names[idx])) {
                pos = idx;
            }
        }
        return marks[pos];
    }

}