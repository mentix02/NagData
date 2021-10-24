package com.nagarro.NagData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class VectorTest {

    final static int vSize = 100;
    static Vector<Integer> vector;

    @BeforeEach
    void setUp() {
        vector = new Vector<>();
        for (int i = 0; i < 100; i++)
            vector.append(i);
    }

    @Test
    void clear() {
        assertEquals(vSize, vector.size());
        vector.clear();
        assertEquals(0, vector.size());
        assertEquals(0, vector.capacity());
        assertEquals(0, vector.data.length);
    }

    @Test
    void get() {
        for (int i = 0; i < vSize; ++i)
            assertEquals(vector.data[i], vector.get(i));
    }

    @Test
    void remove() {
        int uLimit = 10;
        vector = new Vector<>(10);

        for (int i = 0; i < uLimit; i++)
            vector.append(i);

        assertEquals(9, vector.get(uLimit - 1));
        assertEquals(9, vector.remove());
        assertEquals(uLimit - 1, vector.size());

        assertEquals(3, vector.remove(3));
        assertEquals(uLimit - 2, vector.size());
        assertEquals(4, vector.get(3));
    }

    @Test
    void insert() {
        vector = new Vector<>(10);

        for (int i = 0; i < 10; i++)
            vector.append(i);

        assertEquals(10, vector.capacity());

        vector.insert(3, -3);
        assertEquals(-3, vector.get(3));
        assertEquals(3, vector.get(4));
        assertEquals(20, vector.capacity());
    }

    @Test
    void reverse() {
        vector.reverse();
        Vector<Integer> reversedVector = new Vector<>();
        for (int i = 99; i >= 0; i--)
            reversedVector.append(i);
        assertArrayEquals(reversedVector.toArray(), vector.toArray());
    }

    @Test
    void set() {
        int randomIdx = ThreadLocalRandom.current().nextInt(0, vSize);
        vector.set(randomIdx, randomIdx * 2);
        assertEquals(vector.data[randomIdx], randomIdx * 2);
    }

    @Test
    void VectorCollectionsConstructor() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add(i);
        }
        vector = new Vector<>(arrayList);

        int idx = 0;
        for (int el : vector)
            assertEquals(arrayList.get(idx++), el);
    }

}
