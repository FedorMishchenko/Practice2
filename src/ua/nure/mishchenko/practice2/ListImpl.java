package ua.nure.mishchenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    private static final int ZERO = 0;
    private int size;
    private Node head;
    private Node tail;

    public ListImpl() {
        size = ZERO;
    }

    @Override
    public void addFirst(Object element) {
        Node h = head;
        Node node = new Node(null, element, h);
        head = node;
        if (h == null) {
            tail = node;
        } else {
            h.prev = node;
        }
        size++;
    }

    @Override
    public void addLast(Object element) {
        Node t = tail;
        Node node = new Node(t, element, null);
        tail = node;
        if (t == null) {
            head = node;
        }else {
            t.next = node;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        Node next = head.next;
        head.element = null;
        head.next = null;
        head = next;
        if (next == null) {
            tail = null;
        } else {
            next.prev = null;
        }
        size--;
    }

    @Override
    public void removeLast() {
        Node prev = tail.prev;
        tail.element = null;
        tail.prev = null;
        tail = prev;
        if (prev == null){
            head = null;
        }else {
            tail.next = null;
        }
        size--;
    }

    @Override
    public Object getFirst() {
        if (head == null) {
            return null;
        }
        return head.element;
    }

    @Override
    public Object getLast() {
        if (tail == null) {
            return null;
        }
        return tail.element;
    }

    @Override
    public Object search(Object element) {
        for (Node node = head; node != null; node = node.next) {
            if (node.element.equals(element)) {
                return node.element;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        for (Node node = head; node != null; node = node.next) {
            if (element.equals(node.element)) {
                if (node.prev != null && node.next != null) {
                    node.next.prev = node.prev;
                    node.prev.next = node.next;
                    node.prev = null;
                    node.next = null;
                    return true;
                }
                if (node.prev == null) {
                    removeFirst();
                } else {
                    removeLast();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node = head; node != null; node = node.next) {
            if (node.element == null) {
                sb.append("null");
                if(node.next != null) {
                    sb.append(", ");
                }
            } else {
                sb.append(node.element.toString());
                if(node.next != null) {
                    sb.append(", ");
                }
            }
        }
        return sb.insert(0,"[").append("]").toString().trim();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node current;
        private Node lastRet;
        private boolean condition;

        IteratorImpl(){
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
            }else {
                lastRet = current;
                current = current.next;
                condition = true;
                return lastRet.element;
            }
        }

        @Override
        public void remove() {
            if (size == 0) {
                return;
            }
            if (head == null || !condition) {
                throw new IllegalStateException();
            }
            if (lastRet != null) {
                if (lastRet.prev != null && lastRet.next != null) {
                    lastRet.next = current.next;
                    lastRet.prev = current.prev;
                    size--;
                } else if (lastRet.prev == null) {
                    removeFirst();
                } else {
                    removeLast();
                }
                condition = false;
            }
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
