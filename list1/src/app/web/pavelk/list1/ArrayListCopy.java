package app.web.pavelk.list1;

import java.util.*;

public class ArrayListCopy implements List {

    private int size;
    Object[] elementData;

    private Object[] grow() {
        return elementData = Arrays.copyOf(elementData, 10 + size);
    }

    public ArrayListCopy() {
        this.size = 0;
        this.elementData = new Object[10];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {

        if (size == elementData.length)
            elementData = grow();

        elementData[size] = o;
        size = size + 1;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {
        this.elementData = new Object[10];
        this.size = 0;
    }

    @Override
    public Object get(int index) {
        return elementData[index];
    }

    @Override
    public Object set(int index, Object element) {
        elementData[index] = element;
        return element;
    }

    @Override
    public void add(int index, Object element) {

    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    @Override
    public Object remove(int index) {
        Object elementDatum = elementData[index];
        elementData[index] = null;
        Object[] objects = Arrays.copyOfRange(elementData, 0, index);
        Object[] objects2 = Arrays.copyOfRange(elementData, index + 1, elementData.length);
        elementData = concat(objects, objects2);
        return elementDatum;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ArrayListCopy{");
        boolean start = false;
        for (Object o : elementData) {
            if (o != null) {
                if (start) {
                    stringBuilder.append(",");
                }
                start = true;
                stringBuilder.append(o);
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
