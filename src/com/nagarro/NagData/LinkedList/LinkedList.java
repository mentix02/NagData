package com.nagarro.NagData.LinkedList;

import java.util.Collection;

/**
 * The API for this particular implementation of
 * a LinkedList is borrowed heavily from Java's own
 * java.util.LinkedList. NOT MEANT TO BE A DROP IN.
 * ref - https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html
 */
public class LinkedList<E> {

    private int length;

    private LinkedListNode<E> head;
    private LinkedListNode<E> tail;

    // Constructors

    public LinkedList() {
    }

    public LinkedList(Collection<? extends E> c) {
        LinkedList<E> list = new LinkedList<>();
        for (E element : c) {
            list.add(element);
        }
    }

    // Methods to read data from list

    public int size() {
        return length;
    }

    public E getCenter() {
        if (isEmpty()) return null;
        return get(length / 2);
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public E element() {
        return getFirst();
    }

    public E getFirst() {
        return isEmpty() ? null : head.getData();
    }

    public E getLast() {
        return isEmpty() ? null : tail.getData();
    }

    public E get(int index) {

        if (isEmpty())
            return null;
        else if (index >= length)
            throw new IndexOutOfBoundsException();
        else if (index == 0 || index == length - 1)
            return index == 0 ? getFirst() : getLast();

        LinkedListNode<E> node = head;
        while (index-- > 0) node = node.getNextNode();
        return node.getData();
    }

    // Methods to add elements to list

    public boolean add(E element) {
        LinkedListNode<E> node = new LinkedListNode<>(element);

        /*
         * List is empty. Assign head and tail
         * to newly added node.
         */
        if (head == null) {
            head = node;
        }

        /*
         * If previous last member exists,
         * make it point to new tail.
         */
        if (tail != null) {
            tail.setNext(node);
        }

        /*
         * Make current tail's previous pointer
         * point to old tail.
         */
        node.setPrev(tail);

        tail = node;
        length++;

        return true;
    }

    public boolean addLast(E element) {
        return add(element);
    }

    public boolean addFirst(E element) {
        if (length == 0)
            return add(element);

        LinkedListNode<E> node = new LinkedListNode<>(element);

        node.setNext(head);
        this.head.setPrev(node);

        this.head = node;
        return true;
    }

    public boolean add(int index, E element) {

        if (index < 0)
            throw new IllegalArgumentException("Cannot insert at negative index.");

        /*
         * Simply append to end if index to insert
         * element at is greater than list size.
         */
        if (index >= length) {
            return add(element);
        } else if (index == 0) {
            return addFirst(element);
        }

        LinkedListNode<E>
                nodeToInsertAfter = head,
                nodeToInsert = new LinkedListNode<>(element);

        /*
         * Traverse list and decrement index at
         * iteration until we reach the node to
         * insert a new element at. We don't need
         * any bound checking since we already check
         * for over / under cases at the beginning.
         */
        while (index-- > 1) nodeToInsertAfter = nodeToInsertAfter.getNextNode();

        nodeToInsert.setPrev(nodeToInsertAfter);
        nodeToInsert.setNext(nodeToInsertAfter.getNextNode());
        nodeToInsertAfter.setNext(nodeToInsert);

        return true;
    }

    // Methods to remove elements from list

    public void clear() {
        LinkedListNode<E> next, node = head;
        while (node != null) {
            next = node.getNextNode();
            node.setPrev(null);
            node.setNext(null);
            node = next;
        }
        head = null;
        tail = null;
        length = 0;
    }

    public E remove() {

        if (length == 0)
            return null;

        E toReturn = head.getData();

        if (length == 1)
            head = tail = null;
        else {
            LinkedListNode<E> newHead = head.getNextNode();
            newHead.setPrev(null);
            head = newHead;
        }

        return toReturn;
    }

    public E removeFirst() {
        return remove();
    }

    public E removeLast() {
        if (length == 0)
            return null;

        E toReturn = tail.getData();

        if (length == 1) {
            head = tail = null;
        } else {
            LinkedListNode<E> newTail = tail.getPrevNode();
            newTail.setNext(null);
            tail = newTail;
        }

        return toReturn;
    }

    public E remove(int index) {
        if (length == 0 || index <= 0)
            return removeFirst();
        else if (index >= length)
            throw new IllegalArgumentException("Index cannot be greater than list length.");
        else if (index == length - 1)
            return removeLast();

        LinkedListNode<E>
                nodeToDelete,
                nodeToDeleteAfter = head;

        while (index-- > 1) nodeToDeleteAfter = nodeToDeleteAfter.getNextNode();

        nodeToDelete = nodeToDeleteAfter.getNextNode();
        E toReturn = nodeToDelete.getData();

        nodeToDelete.getNextNode().setPrev(nodeToDeleteAfter);
        nodeToDeleteAfter.setNext(nodeToDelete.getNextNode());

        return toReturn;
    }

    // Conversion & transformation methods

    public Object[] toArray() {
        if (length == 0)
            return new Object[0];

        int i = 0;
        LinkedListNode<E> node = head;
        Object[] arr = new Object[length];

        while (node != null) {
            arr[i++] = node.getData();
            node = node.getNextNode();
        }

        return arr;
    }

    public void reverse() {
        LinkedListNode<E>
                temp = null,
                current = head;

        while (current != null) {
            temp = current.getPrevNode();
            current.setPrev(current.getNextNode());
            current.setNext(temp);
            current = current.getPrevNode();
        }

        if (temp != null)
            head = temp.getPrevNode();
    }

    @Override
    public String toString() {

        if (length == 0)
            return "[]";

        LinkedListNode<E> current = head;
        StringBuilder stringBuilder = new StringBuilder("[ ");

        while (current != null) {
            stringBuilder.append(current).append(", ");
            current = current.getNextNode();
        }

        // Replace redundant last comma with a right bracket
        stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), " ]");

        return stringBuilder.toString();
    }

}
