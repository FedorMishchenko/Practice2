package ua.nure.mishchenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    private Object[] array;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private static final int DEFAULT_CAPACITY = 10;
    private final int capacity;
    private int pointer;

    public ArrayImpl() {
        this(0);
    }

    ArrayImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            this.array = new Object[initialCapacity];
            capacity = initialCapacity;
        } else if (initialCapacity == 0) {
            this.array = new Object[DEFAULT_CAPACITY];
            capacity = DEFAULT_CAPACITY;
        } else {
            throw new IllegalArgumentException("illegal capacity " + initialCapacity);
        }
    }

    private void init(int size) {
        pointer = 0;
        array = new Object[size];
    }

    private void resize(int len) {
        Object[] temp = new Object[len];
        System.arraycopy(array, 0, temp, 0, pointer);
        array = temp;
    }

    private void rangeCheck(int index) {
        if (index >= pointer || index < 0) {
            throw new IndexOutOfBoundsException(
                    "size = " + size() + ", index = " + index);
        }
    }

    private void rebuild(int index) {
        if (index == size() - 1) {
            array[index] = null;
        } else {
            int lengthCopy = size() - index - 1;
            System.arraycopy(array, index + 1, array, index, lengthCopy);
        }
        pointer--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            if (array[i] == null) {
                sb.append("null");
                if (i < size() - 1) {
                    sb.append(", ");
                }
            } else {
                sb.append(array[i].toString());
                if (i < size() - 1) {
                    sb.append(", ");
                }
            }
        }
        return sb.insert(0, "[").append("]").toString();
    }

    @Override
    public void add(Object element) {
        if (array == EMPTY_ELEMENT_DATA) {
            init(capacity);
        }
        if (pointer >= array.length) {
            resize(array.length + (array.length >> 1));
        }
        array[pointer++] = element;
    }

    @Override
    public void set(int index, Object element) {
        rangeCheck(index);
        array[index] = element;

    }

    @Override
    public Object get(int index) {
        rangeCheck(index);
        return array[index];
    }

    @Override
    public int indexOf(Object element) {
        if (element == null) {
            for (int i = 0; i < size(); i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size(); i++) {
                if (element.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        rangeCheck(index);
        rebuild(index);
    }

    @Override
    public void clear() {
        array = EMPTY_ELEMENT_DATA;
        init(capacity);
    }

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public boolean contains(Object element) {
        if (element != null) {
            for (int i = 0; i < pointer; i++) {
                if (element.equals(array[i])) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size(); i++) {
                if (array[i] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Array array) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != null || !contains(array.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private static final int ZERO = 0;
        private int cursor;
        private boolean condition;

        IteratorImpl() {
            cursor = ZERO;
        }

        @Override
        public boolean hasNext() {
            return cursor != size() && array[cursor] != null;
        }

        @Override
        public Object next() {
            if (cursor >= size()) {
                throw new NoSuchElementException();
            }
            condition = true;
            return array[cursor++];
        }

        @Override
        public void remove() {
            if (!condition) {
                throw new IllegalStateException();
            }
            int index = --cursor;
            if (this.hasNext()) {
                ArrayImpl.this.remove(index);
            }
            condition = false;
        }
    }


}
