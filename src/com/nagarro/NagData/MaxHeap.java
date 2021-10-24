package com.nagarro.NagData;

import java.util.Objects;

/**
 * A simple MaxHeap implemented using {@link Vector an unbounded array}.
 *
 * @param <E> type of data to store
 * @author mentix02
 * @see <a href="https://www.youtube.com/watch?v=t0Cq6tVNRBA">Data Structures: Heaps</a>
 */
public class MaxHeap<E extends Comparable<E>> {

    protected Vector<E> heap = new Vector<>();

    // Read from heap

    public int size() {
        return heap.size();
    }

    public int capacity() {
        return heap.capacity();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * @return highest priority element or throws {@link IllegalStateException}
     */
    public E peek() {
        if (isEmpty())
            throw new IllegalStateException();
        return heap.get(0);
    }

    // Manipulate heap

    /**
     * Returns and removes highest priority and preserves heap invariance
     * in O(log n) time.
     *
     * @return highest priority element or throws {@link IllegalStateException}
     */
    public E poll() {
        if (isEmpty())
            throw new IllegalStateException();

        // Swap root and last leaf nodes
        E el = heap.get(0);
        heap.set(0, heap.remove());

        // Heapify to restore heap invariant
        heapifyDown();

        return el;
    }

    /**
     * Inserts and positions object in heap according to its priority
     * in O(log n) time.
     *
     * @param element object to inserted in heap
     */
    public void add(E element) {
        if (Objects.isNull(element))
            throw new IllegalArgumentException(this.getClass().getName() + " cannot hold null values");
        heap.append(element);
        heapifyUp();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size() / 2; i++) {
            stringBuilder.append(" PARENT ").append(heap.get(i)).append(" | ");
            if (hasLeftChild(i)) {
                stringBuilder.append(" LEFT CHILD ").append(leftChildVal(i)).append(" | ");
                if (hasRightChild(i)) {
                    stringBuilder.append(" RIGHT CHILD ").append(rightChildVal(i)).append(" | ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    // Private methods

    /**
     * Iteratively restores max heap invariant (if required) by
     * comparing and swapping child and parent nodes for each
     * invalid position until invariance is achieved.
     */
    private void heapifyUp() {
        int idx = size() - 1;
        while (
                hasParent(idx) && greaterThan(heap.get(idx), parentVal(idx))
        ) {
            // Child is greater than parent - perform swap
            // Child becomes parent.
            swapNodes(parentIdx(idx), idx);
            // Continue upwards
            idx = parentIdx(idx);
        }
    }

    /**
     * Similar to {@link #heapifyUp()} where invariance is restored
     * via an iterative process. After a {@link #poll()} occurs, the
     * last "node" of heap is temporarily placed as root only to be
     * shifted down to its new appropriate position.
     */
    private void heapifyDown() {
        int idx = 0;
        while (hasLeftChild(idx)) {
            int greaterChildIdx = leftChildIdx(idx);
            if (hasRightChild(idx) && greaterThan(rightChildVal(idx), leftChildVal(idx))) {
                greaterChildIdx = rightChildIdx(idx);
            }

            /*
             If parent is bigger than either child, we find the
             valid position for this value at the index and heap
             invariance is restored - end loop
            */
            if (greaterThan(heap.get(idx), heap.get(greaterChildIdx)))
                break;
            else
                swapNodes(idx, greaterChildIdx); // Parent was smaller than child - perform swap.

            idx = greaterChildIdx;
        }
    }

    /**
     * @param left  object tested to be greater
     * @param right object tested to be lesser
     * @return true if left > right else false
     */
    private boolean greaterThan(E left, E right) {
        return left.compareTo(right) > 0;
    }

    private void swapNodes(int idxOne, int idxTwo) {
        E elemOne = heap.get(idxOne);
        E elemTwo = heap.get(idxTwo);
        heap.set(idxOne, elemTwo);
        heap.set(idxTwo, elemOne);
    }

    private int parentIdx(int childIdx) {
        return (childIdx - 1) / 2;
    }

    private int leftChildIdx(int parentIdx) {
        return 2 * parentIdx + 1;
    }

    private int rightChildIdx(int parentIdx) {
        return 2 * parentIdx + 2;
    }

    protected boolean hasParent(int idx) {
        return parentIdx(idx) >= 0;
    }

    protected boolean hasLeftChild(int idx) {
        return leftChildIdx(idx) < size();
    }

    protected boolean hasRightChild(int idx) {
        return rightChildIdx(idx) < size();
    }

    private E parentVal(int idx) {
        return heap.get(parentIdx(idx));
    }

    private E leftChildVal(int idx) {
        return heap.get(leftChildIdx(idx));
    }

    private E rightChildVal(int idx) {
        return heap.get(rightChildIdx(idx));
    }

}