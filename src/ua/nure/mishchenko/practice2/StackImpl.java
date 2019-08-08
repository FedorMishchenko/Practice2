package ua.nure.mishchenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    private int size;
    private Node tail;
    private Node head;
    private static final int ZERO = 0;

    public StackImpl(){
        size = ZERO;
    }

    @Override
    public void push(Object element) {
        Node tmp = tail;
        Node node = new Node(tmp, element, null);
        tail = node;
        if (tmp != null) {
            tmp.next = node;
        } else {
            head = node;
        }
        size++;
    }

    @Override
    public Object pop() {
        Node prev = tail.prev;
        Object res = tail.element;
        tail.element = null;
        tail.prev = null;
        tail = prev;
        if (prev != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return res;
    }

    @Override
    public Object top() {
        return tail.element;
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
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Node node = head; node != null; node = node.next) {
            if (node.element != null) {
                sb.append(node.element.toString());
                if(node.next != null) {
                    sb.append(", ");
                }
            } else {
                sb.append("null");
                if(node.next != null) {
                    sb.append(", ");
                }
            }
        }
        return sb.insert(0,"[").append("]").toString().trim();
    }

    private static class Node{
        private Object element;
        private Node next;
        private Node prev;

        Node(Node prev, Object element, Node next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object>{
        private Node current;
        private Node lastRet;
        private boolean condition;

        IteratorImpl(){
            current = tail;
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
                current = current.prev;
                condition = true;
                return lastRet.element;
            }
        }

        @Override
        public void remove() {
            if (size == ZERO) {
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
                    pop();
                }
                condition = false;
            }
        }

        private void removeFirst() {
            Node next = head.next;
            head.element = null;
            head.next = null;
            head = next;
            if (next == null) {
                tail = null;
            }else {
                next.prev = null;
            }
            size--;
        }
    }

}
