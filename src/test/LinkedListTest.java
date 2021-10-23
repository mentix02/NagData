import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.nagarro.NagData.LinkedList;

class LinkedListTest {

    final static int lSize = 100;
    static LinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
        for (int i = 0; i < 100; ++i)
            list.add(i);
    }

    @AfterAll
    static void tearDown() {
        list.clear();
    }

    @Test
    void size() {
        assertEquals(list.size(), lSize);
    }

    @Test
    void isEmpty() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void get() {
        int randomIdx;
        for (int i = 0; i < 5; ++i) {
            randomIdx = ThreadLocalRandom.current().nextInt(0, lSize);
            assertEquals(randomIdx, list.get(randomIdx));
        }
    }

    @Test
    void getFirst() {
        assertEquals(list.get(0), 0);
        assertEquals(list.element(), 0);
        assertEquals(list.getFirst(), 0);
    }

    @Test
    void getCenter() {
        assertEquals(list.getCenter(), lSize / 2);
    }

    @Test
    void getLast() {
        assertEquals(list.get(99), 99);
        assertEquals(list.getLast(), 99);
    }

    @Test
    void add() {
        int randomIdx = ThreadLocalRandom.current().nextInt(0, lSize - 10);
        boolean b = list.add(randomIdx, -randomIdx);

        assertTrue(b);
        assertEquals(list.get(randomIdx), -randomIdx);
        assertEquals(list.get(randomIdx + 1), randomIdx);
    }

    @Test
    void reverse() {
        int idx = lSize;
        Object[] arr = list.toArray();
        list.reverse();
        for (Integer el : list) {
            assertEquals(arr[--idx], el);
        }
    }

    @Test
    void addToEnd() {
        list.add(100);
        assertEquals(list.size(), 101);
        assertEquals(list.get(100), 100);
    }

    @Test
    void addLast() {
        boolean b = list.addLast(100);
        assertTrue(b);
        assertEquals(list.size(), 101);
        assertEquals(list.get(100), 100);
    }

    @Test
    void LinkedListCollectionsConstructor() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arrayList.add(i);
        }
        list = new LinkedList<>(arrayList);

        int idx = 0;
        for (int el : list)
            assertEquals(arrayList.get(idx++), el);
    }
}