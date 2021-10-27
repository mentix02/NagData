package com.nagarro.NagData.heap;

import com.nagarro.NagData.Vector;

import java.util.Objects;

/**
 * Simple heap data structure implemented using a {@link Vector}.
 *
 * @param <E> type of data to store
 * @author mentix02
 */
public abstract class AbstractHeap<E extends Comparable<E>> {

    protected Vector<E> heap = new Vector<>();

    abstract protected boolean comparator(E left, E right);

    // Read from heap

    public AbstractHeap() {
    }

    public AbstractHeap(Vector<E> vector) {
        int heapSize = vector.size();
        heap = new Vector<>(heapSize);

        for (int i = 0; i < heapSize; i++) heap.append(vector.get(i));

        // Heapify process, O( n * log(n) )
        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) heapifyDown(i);
    }

    public int size() {
        return heap.size();
    }

    public int capacity() {
        return heap.capacity();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public boolean contains(E toFind) {
        for (E el : heap)
            if (el.equals(toFind))
                return true;
        return false;
    }

    /**
     * @return highest priority or lowest priority element
     * or throws {@link IllegalStateException}
     */
    public E peek() {
        if (isEmpty())
            throw new IllegalStateException();
        return heap.get(0);
    }

    /**
     * @return highest or lowest priority element or throws {@link IllegalStateException}
     */
    public E poll() {
        if (isEmpty())
            throw new IllegalStateException();

        // Swap root and last leaf nodes
        E el = heap.get(0);
        heap.set(0, heap.remove());

        // Heapify from root to restore heap invariant
        heapifyDown(0);

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


    /**
     * Iteratively restores heap invariant (if required) by
     * comparing and swapping child and parent nodes for each
     * invalid position until invariance is achieved.
     */
    protected void heapifyUp() {
        int idx = size() - 1;
        while (
                hasParent(idx) && comparator(heap.get(idx), parentVal(idx))
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
    protected void heapifyDown(int idx) {
        while (hasLeftChild(idx)) {
            int comparativeChildIdx = leftChildIdx(idx);
            if (hasRightChild(idx) && comparator(rightChildVal(idx), leftChildVal(idx))) {
                comparativeChildIdx = rightChildIdx(idx);
            }

            /*
             If parent is bigger or lesser than either child,
             we find the valid position for this value at the
             index and heap invariance is restored -> end loop
            */
            if (comparator(heap.get(idx), heap.get(comparativeChildIdx)))
                break;
            else
                swapNodes(idx, comparativeChildIdx); // Parent was smaller than child - perform swap.

            idx = comparativeChildIdx;
        }
    }

    protected void swapNodes(int idxOne, int idxTwo) {
        E elemOne = heap.get(idxOne);
        E elemTwo = heap.get(idxTwo);
        heap.set(idxOne, elemTwo);
        heap.set(idxTwo, elemOne);
    }

    protected int parentIdx(int childIdx) {
        return (childIdx - 1) / 2;
    }

    protected int leftChildIdx(int parentIdx) {
        return 2 * parentIdx + 1;
    }

    protected int rightChildIdx(int parentIdx) {
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

    protected E parentVal(int idx) {
        return heap.get(parentIdx(idx));
    }

    protected E leftChildVal(int idx) {
        return heap.get(leftChildIdx(idx));
    }

    protected E rightChildVal(int idx) {
        return heap.get(rightChildIdx(idx));
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

}
