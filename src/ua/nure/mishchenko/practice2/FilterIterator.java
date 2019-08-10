package ua.nure.mishchenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilterIterator<E> implements Iterator<E> {
    private Iterator<? extends E> iterator;
    private Predicate<? super E> predicate;
    private E nextObject;
    private boolean nextObjectSet = false;


    public FilterIterator(final Iterator<? extends E> iterator, final Predicate<? super E> predicate) {
        super();
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        return nextObjectSet || setNextObject();
    }

    @Override
    public E next() {
        if (!nextObjectSet && !setNextObject()) {
            throw new NoSuchElementException();
        }
        nextObjectSet = false;
        return nextObject;
    }

    @Override
    public void remove() {
        if (nextObjectSet) {
            throw new IllegalStateException("remove() cannot be called");
        }
        iterator.remove();
    }

    private boolean setNextObject() {
        while (iterator.hasNext()) {
            final E object = iterator.next();
            if (predicate.test(object)) {
                nextObject = object;
                nextObjectSet = true;
                return true;
            }
        }
        return false;
    }
}
