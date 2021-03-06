package com.nagarro.NagData;

import com.nagarro.NagData.queue.LinearQueue;
import com.nagarro.NagData.queue.Queue;

import java.util.Iterator;
import java.util.Objects;

/**
 * Implements a level order traversal over each
 * node of the n-ary tree.
 *
 * @param <E> type of object to return each {@link #next()} call
 */
class NTreeBreadthIterator<E> implements Iterator<E> {

    private final Queue<NTree<E>> s = new LinearQueue<>();

    NTreeBreadthIterator(NTree<E> tree) {
        s.enqueue(tree);
    }

    public boolean hasNext() {
        return !s.isEmpty();
    }

    public E next() {
        NTree<E> node = s.dequeue();
        for (NTree<E> child : node.getChildren())
            s.enqueue(child);
        return node.getData();
    }
}

/**
 * Do not use. Does not work. Kept for sentimental purposes.
 *
 * @param <E>
 */
class NTreeDepthIterator<E> implements Iterator<E> {

    private NTree<E> root;
    private int currentRootIndex = 0;
    private final Stack<Pair<E>> stack = new Stack<>();
    private final Vector<E> postorderTraversal = new Vector<>();

    static class Pair<E> {
        public NTree<E> node;
        public int childrenIndex;

        public Pair(NTree<E> node, int childrenIndex) {
            this.node = node;
            this.childrenIndex = childrenIndex;
        }
    }

    NTreeDepthIterator(NTree<E> root) {
        this.root = root;
    }

    public boolean hasNext() {
        return !Objects.isNull(root) || !stack.isEmpty();
    }

    public E next() {
        if (!Objects.isNull(root)) {
            stack.push(new Pair<>(root, currentRootIndex));
            currentRootIndex = 0;

            if (!root.isLeaf())
                root = root.getChildren().get(0);
            else
                root = null;
        }
        Pair<E> temp = stack.pop();
        return temp.node.getData();
    }

}

public class NTree<E> implements NagCollection<E> {

    private E data;
    private final LinkedList<NTree<E>> children = new LinkedList<>();

    // Constructors

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

    public int depth() {
        int maxDepth = 0;
        for (NTree<E> node : children)
            maxDepth = Math.max(maxDepth, node.depth());
        return maxDepth + 1;
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

    // Setters

    public void setData(E data) {
        this.data = data;
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

    public E removeChild() {
        NTree<E> lastChild = children.removeLast();
        if (Objects.isNull(lastChild))
            throw new IllegalStateException();
        return lastChild.getData();
    }

    public String strBreadthFirst() {
        StringBuilder stringBuilder = new StringBuilder();
        for (E node : this) {
            stringBuilder.append(node);
        }
        return stringBuilder.toString();
    }

    /**
     * @return returns a depth-first post order traversal string
     */
    public String strDepthFirstPostOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        for (NTree<E> node : getChildren()) {
            stringBuilder.append(node.strDepthFirstPostOrder());
        }
        stringBuilder.append(getData());
        return stringBuilder.toString();
    }

    /**
     * @return returns a depth-first pre order traversal string
     */
    public String strDepthFirstPreOrder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getData());
        for (NTree<E> node : children) {
            stringBuilder.append(node.strDepthFirstPreOrder());
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new NTreeBreadthIterator<>(this);
    }

    @Override
    public String toString() {
        return String.format("{ val: %s, children: %d }", this.data.toString(), this.children.size());
    }

}
