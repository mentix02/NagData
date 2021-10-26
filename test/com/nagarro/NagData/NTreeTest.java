package com.nagarro.NagData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class NTreeTest {

    static NTree<Integer> tree;

    @BeforeEach
    void setUp() {

        /*
         Builds the following tree -

                  0
                / | \
               /  |  \
              1   2   3
             / \    / | \
            4   5  6  7  8
                      |
                      9

        */

        tree = new NTree<>(0);
        NTree<Integer> r1 = tree.addChild(1);
        tree.addChild(2);
        NTree<Integer> r3 = tree.addChild(3);
        r1.addChild(4);
        r1.addChild(5);
        r3.addChild(6);
        NTree<Integer> r31 = r3.addChild(7);
        r3.addChild(8);
        r31.addChild(9);
    }

    @Test
    void size() {
        assertEquals(10, tree.size());
    }

    @Test
    void isLeaf() {
        assertTrue(tree.getChildren().get(1).isLeaf());
        assertTrue(tree.getChildren().get(0).getChildren().get(0).isLeaf());
    }

    @Test
    void elementsAtLevel() {

        int depth = tree.depth();
        Vector<Integer> levelVec;
        int[][] levels = {{0}, {1, 2, 3}, {4, 5, 6, 7, 8}, {9}};

        for (int level = 0; level < depth; level++) {
            levelVec = tree.elementsAtLevel(level);
            for (int i = 0; i < levels[level].length; i++) {
                assertEquals(levels[level][i], levelVec.get(i));
            }
        }

    }

    @Test
    void strDepthFirstPreOrder() {
        assertEquals("0145236798", tree.strDepthFirstPreOrder());
    }

    @Test
    void strBreadthFirst() {
        assertEquals("0123456789", tree.strBreadthFirst());
    }

    @Test
    void strDepthFirstPostOrder() {
        assertEquals("4512697830", tree.strDepthFirstPostOrder());
    }

    @Test
    void removeChild() {
        assertEquals(3, tree.removeChild());
    }

    @Test
    void contains() {
        assertTrue(tree.contains(4));
        assertFalse(tree.contains(-4));
    }

    @Test
    void depth() {
        assertEquals(4, tree.depth());
    }

}