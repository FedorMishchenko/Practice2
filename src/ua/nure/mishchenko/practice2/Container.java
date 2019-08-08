package ua.nure.mishchenko.practice2;

import java.util.Iterator;

public interface Container extends Iterable<Object> {

    void clear();

    int size();

    String toString();

    Iterator<Object> iterator();

}
