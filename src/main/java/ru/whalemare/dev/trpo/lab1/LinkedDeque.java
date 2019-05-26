package ru.whalemare.dev.trpo.lab1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementing of single linked list that represent a head-in-head-out
 */
public class LinkedDeque<Item> implements Iterable<Item> {
    private int size;

    /*@Nullable*/
    private Node head; // first (index: 0)

    /*@Nullable*/
    private Node last; // last (index: size)

    private class Node {
        Node(Item item) {
            this(item, null);
        }

        Node(Item item, /*@Nullable*/ Node next) {
            this.item = item;
            this.next = next;
        }

        private Item item;

        /*@Nullable*/
        private Node next;
    }

    /**
     * Initializes an empty queue.
     */
    public LinkedDeque() {
        head = null;
        last = null;
        size = 0;
        assert check();
    }

    /**
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * @return the number of items in this queue
     */
    public int size() {
        return size;
    }

    /**
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peekHead() {
        if (head == null) throw new NoSuchElementException("Queue underflow");
        return head.item;
    }

    /**
     * @return the item firstly added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peekLast() {
        if (last == null) throw new NoSuchElementException("Queue underflow");
        return last.item;
    }

    /**
     * Add the item to the head of this queue.
     *
     * @param item to add
     */
    public void addHead(Item item) {
        final Node prevHead = head;
        final Node newNode = new Node(item, prevHead);
        head = newNode;
        if (prevHead == null) {
            last = newNode;
        } else {

        }
        size++;
    }


    /**
     * Adds the item as last of this queue.
     *
     * @param item to add
     */
    public void addLast(Item item) {
        final Node l = last;
        final Node newNode = new Node(item, null);
        last = newNode;
        if (l == null) {
            head = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item removeHead() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");

        Item item = head.item;
        head = head.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item removeLast() {
        if (last == null) throw new NoSuchElementException("Queue is empty");
        final Item element = last.item;

        if (head == last) {
            head = null;
            last = null;
        } else {
            Node prevToLast = head;
            while (prevToLast.next != last) {
                prevToLast = prevToLast.next;
            }
            last = prevToLast;
            last.next = null;
        }

        size--;
        return element;
    }

    /**
     * Find the index of the first occurrence of the specified element
     *
     * @param item searchable element
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    public int indexOf(Item item) {
        int index = 0;
        if (item == null) {
            return size;
        } else {
            for (Node x = head; x != null; x = x.next) {
                if (item.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        boolean skipped = false;
        for (Item item : this) {
            if (skipped) {
                s.append("->").append(item);
            } else {
                s.append(item);
            }
            skipped = true;
        }
        return s.toString();
    }

    // check internal invariants
    private boolean check() {
        if (size < 0) {
            return false;
        } else if (size == 0) {
            if (head != null) return false;
            if (last != null) return false;
        } else if (size == 1) {
            if (head == null || last == null) return false;
            if (head != last) return false;
            if (head.next != null) return false;
        } else {
            if (head == null || last == null) return false;
            if (head == last) return false;
            if (head.next == null) return false;
            if (last.next != null) return false;

            // check internal consistency of instance variable size
            int numberOfNodes = 0;
            for (Node x = head; x != null && numberOfNodes <= size; x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != size) return false;

            // check internal consistency of instance variable last
            Node lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            if (last != lastNode) return false;
        }

        return true;
    }

    /**
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator() {
        return new ListIterator(head);
    }

    private class ListIterator implements Iterator<Item> {
        private Node current;

        public ListIterator(Node head) {
            this.current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
