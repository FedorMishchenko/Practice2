package ua.nure.mishchenko.practice2;

import java.util.Iterator;
import java.util.function.Predicate;

public interface Container extends Iterable<Object> {

    void clear();

    int size();

    String toString();

    Iterator<Object> iterator();

    Iterator iterator(Predicate<Entity> isPositive);
}
