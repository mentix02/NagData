package com.nagarro.NagData.LinkedList;

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

    // Setters - none required for `E data`
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
