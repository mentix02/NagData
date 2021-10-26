package com.nagarro.NagData;

import java.util.Iterator;

class NTreeDepthIterator<E> implements Iterator<E> {
    NTreeDepthIterator() {
    }

    public boolean hasNext() {
        return false;
    }

    public E next() {
        return null;
    }
}

/**
 * Implements a level order traversal over each
 * node of the n-ary tree.

 * @param <E> type of object to return each {@link #next()} call
 */
class NTreeBreadthIterator<E> implements Iterator<E> {

    private final LinkedList<E> list = new LinkedList<>();
    private final Queue<NTree<E>> q = new LinearQueue<>();

    NTreeBreadthIterator(NTree<E> tree) {
        q.enqueue(tree);
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }

    public E next() {
        if (!list.isEmpty()) {
            return list.removeFirst();
        }
        int size = q.size();
        for (int i = 0; i < size; i++) {
            NTree<E> node = q.dequeue();
            list.add(node.getData());

            for (NTree<E> child : node.getChildren())
                q.enqueue(child);
        }
        return list.removeFirst();
    }

}

public class NTree<E> implements NagCollection<E> {

    private E data;
    private final LinkedList<NTree<E>> children = new LinkedList<>();

    // Constructors

    public NTree() {
    }

    public NTree(E value) {
        this.data = value;
    }

    public NTree(E rootValue, E[] childrenValues) {
        this.data = rootValue;
        for (E el : childrenValues)
            children.add(new NTree<>(el));
    }

    // Read from tree

    public int size() {
        int res = 1;
        for (NTree<E> child : children)
            res += child.size();
        return res;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public Vector<E> elementsAtLevel(int level) {

        level = Math.abs(level);

        Vector<E> layer = new Vector<>();
        Queue<NTree<E>> q = new LinearQueue<>();
        q.enqueue(this);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                NTree<E> node = q.dequeue();
                if (level == 0)
                    layer.add(node.getData());

                for (NTree<E> child : node.children)
                    q.enqueue(child);
            }
            if (level-- == 0)
                return layer;
        }
        if (level > 0)
            throw new IndexOutOfBoundsException();

        return layer;
    }

    public boolean contains(E toFind) {
        if (data.equals(toFind))
            return true;

        for (NTree<E> child : children) {
            if (child.contains(toFind))
                return true;
        }
        return false;
    }

    // Getters

    public E getData() {
        return data;
    }

    public LinkedList<NTree<E>> getChildren() {
        return children;
    }

    // Manipulation

    public NTree<E> addChild(E element) {
        NTree<E> child = new NTree<>(element);
        children.add(child);
        return child;
    }

    public NTree<E> addChild(NTree<E> node) {
        children.add(node);
        return node;
    }

    public NTree<E> removeChild() {
        return children.removeLast();
    }

    @Override
    public Iterator<E> iterator() {
        return new NTreeBreadthIterator<>(this);
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
