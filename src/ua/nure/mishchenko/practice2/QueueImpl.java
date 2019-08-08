package ua.nure.mishchenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
    private static final int ZERO = 0;
    private Node head;
    private Node tail;
    private int size;

    public QueueImpl() {
        size = ZERO;
    }

    @Override
    public void enqueue(Object element) {
        Node t = tail;
        Node node = new Node(t, element, null);
        tail = node;
        if (t == null) {
            head = node;
        } else {
            t.next = node;
        }
        size++;
    }

    @Override
    public Object dequeue() {
        if (size == 0) {
            return null;
        }
        Object result = head.element;
        head = head.next;
        size--;
        return result;
    }

    @Override
    public Object top() {
        if (size == 0) {
            return null;
        }
        if (head == null) {
            tail = null;
            return null;
        } else {
            return head.element;
        }
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node = head; node != null; node = node.next) {
            if (node.element != null) {
                sb.append(node.element.toString());
                if (node.next != null) {
                    sb.append(", ");
                }
            }
        }
        return sb.insert(0, "[").append("]").toString().trim();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node current;
        private Node lastRet;
        private boolean condition;

        IteratorImpl() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                lastRet = current;
                current = current.next;
                condition = true;
                return lastRet.element;
            }
        }

        @Override
        public void remove() {
            if (head == null || !condition) {
                throw new IllegalStateException();
            }
            if (lastRet != null) {
                if (lastRet.prev == null) {
                    Node next = head.next;
                    head.element = null;
                    head.next = null;
                    head = next;
                    if (next == null) {
                        tail = null;
                    } else {
                        next.prev = null;
                    }
                } else if (lastRet.next == null) {
                    Node prev = tail.prev;
                    tail.element = null;
                    tail.prev = null;
                    tail = prev;
                    if (prev == null) {
                        head = null;
                    } else {
                        tail.next = null;
                    }
                } else {
                    lastRet.next = current.next;
                    lastRet.prev = current.prev;
                }
            }
            size--;
            condition = false;
        }
    }


    private static class Node {
        private Object element;
        private Node next;
        private Node prev;

        Node(Node prev, Object element, Node next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

    }
}
