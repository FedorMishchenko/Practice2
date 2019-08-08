package ua.nure.mishchenko.practice2;

public interface Array extends Container {

    void add(Object element);

    void set(int index, Object element);

    Object get(int index);

    int indexOf(Object element);

    void remove(int index);

    boolean contains(Object element);

    boolean containsAll(Array arr);
}
