package com.nagarro.NagData;

import java.util.Objects;
import java.util.Iterator;
import java.util.Collection;

/**
 * Basic building block for {@link LinkedList}. Nothing but a
 * struct to keep track of an element, a ref to the next and
 * previous node.
 *
 * @param <E> type of element to store
 * @author mentix02
 */
class LinkedListNode<E> {

    private final E data;
    private LinkedListNode<E> nextNode;
    private LinkedListNode<E> prevNode;

    public LinkedListNode(E data) {
        this.data = data;
    }

    // Getters

    public E getData() {
        return data;
    }

    public LinkedListNode<E> getNextNode() {
        return nextNode;
    }

    public LinkedListNode<E> getPrevNode() {
        return prevNode;
    }

    // Setters

    public void setPrev(LinkedListNode<E> prevNode) {
        this.prevNode = prevNode;
    }

    public void setNext(LinkedListNode<E> nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}

class LinkedListIterator<E> implements Iterator<E> {

    LinkedListNode<E> current;

    public LinkedListIterator(LinkedList<E> list) {
        current = list.head;
    }

    public boolean hasNext() {
        return !Objects.isNull(current);
    }

    public E next() {
        E data = current.getData();
        current = current.getNextNode();
        return data;
    }

}

/**
 * <code>LinkedList</code> implements a generic doubly linked list that supports
 * iteration, insertion, deletion, and other helpful methods. Also used to
 * implement the {@link com.nagarro.NagData.queue.LinearQueue LinearQueue} data structure.
 *
 * @param <E> type of element to store
 * @author mentix02
 */
public class LinkedList<E> implements NagCollection<E> {

    private int length = 0;

    protected LinkedListNode<E> head;
    protected LinkedListNode<E> tail;

    // Constructors

    public LinkedList() {
    }

    public LinkedList(Collection<? extends E> c) {
        LinkedList<E> list = new LinkedList<>();
        for (E element : c) {
            list.add(element);
        }
    }

    @Override
    protected void finalize() {
        clear();
    }

    // Methods to read data from list

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public E getCenter() {
        return isEmpty() ? null : get(length / 2);
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

    public boolean contains(E toFind) {
        for (E el : this)
            if (el.equals(toFind))
                return true;
        return false;
    }

    // Methods to add elements to list

    public boolean add(E element) {
        LinkedListNode<E> node = new LinkedListNode<>(element);

        /*
         * List is empty. Assign head and tail
         * to newly added node.
         */
        if (Objects.isNull(head)) {
            head = node;
        }

        /*
         * If previous last member exists,
         * make it point to new tail.
         */
        if (!Objects.isNull(tail)) {
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

    public boolean add(LinkedList<E> list) {
        for (E el : list) {
            add(el);
        }
        return true;
    }

    public boolean add(E[] arr) {
        for (E el : arr) {
            add(el);
        }
        return true;
    }

    /**
     * Simply replaces the head of list with new element
     *
     * @param element object to be inserted
     * @return always true
     */
    public boolean addFirst(E element) {
        if (length == 0)
            return add(element);

        LinkedListNode<E> node = new LinkedListNode<>(element);

        node.setNext(head);
        this.head.setPrev(node);

        this.head = node;
        return true;
    }

    /**
     * Inserts element AT index provided.
     * O(n-1) -> O(n) worst case
     *
     * @param index   position to insert element at
     * @param element object to be inserted
     * @return always true
     */
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

    /**
     * Clears entire list. Traversing and manually setting
     * prev and next node addresses is required since the
     * GC will count next and prev refs from each node and
     * won't delete them by itself.
     */
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

        length--;

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

        length--;

        return toReturn;
    }

    /**
     * Traverses list until <code>index</code> position to remove
     * node. Throws <code>IllegalArgumentException</code> for
     * invalid indices (negative or >= length).
     *
     * @param index position of element to delete
     * @return element to be deleted
     */
    public E remove(int index) {
        if (length == 0 || index == 0)
            return removeFirst();
        else if (index >= length || index < 0)
            throw new IllegalArgumentException("Index cannot be negative or exceed list length.");
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

    public void selfReverse() {
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

    public Iterator<E> iterator() {
        return new LinkedListIterator<>(this);
    }

    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Object clone() {
        LinkedList<E> linkedList = new LinkedList<>();
        for (E el : linkedList)
            linkedList.add(el);
        return linkedList;
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
