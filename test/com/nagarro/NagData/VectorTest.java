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
