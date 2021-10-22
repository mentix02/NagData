package com.nagarro.NagData.LinkedList;

import java.util.Collection;

public class LinkedList<E> {

    private int length;

    private LinkedListNode<E> head;
    private LinkedListNode<E> tail;

    public LinkedList() {
    }

    public LinkedList(Collection<? extends E> c) {
        LinkedList<E> list = new LinkedList<>();
        for (E element : c) {
            list.add(element);
        }
    }

    public int size() {
        return length;
    }

    // Appends to end list.
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
        }

        return true;
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
