package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

public abstract class EurecaMultivaluedMapValue<T extends EurecaMapValue> implements Collection {
    public abstract Collection<T> getCollection();

    @Override
    public int size() {
        return getCollection().size();
    }

    @Override
    public boolean isEmpty() {
        return getCollection().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return getCollection().contains(o);
    }

    @Override
    public Iterator iterator() {
        return getCollection().iterator();
    }

    @Override
    public Object[] toArray() {
        return getCollection().toArray();
    }

    @Override
    public boolean add(Object o) {
        return getCollection().add((T) o);
    }

    @Override
    public boolean remove(Object o) {
        return getCollection().remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        return getCollection().addAll(c);
    }

    @Override
    public void clear() {
        getCollection().clear();
    }

    @Override
    public boolean retainAll(Collection c) {
        return getCollection().retainAll(c);
    }

    @Override
    public boolean removeAll(Collection c) {
        return getCollection().removeAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return getCollection().containsAll(c);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return getCollection().toArray(a);
    }

    @Override
    public String toString() {
        String s = getCollection().stream().map(Object::toString).collect(Collectors.joining(","));
        return String.format("%s", s);
    }
}
