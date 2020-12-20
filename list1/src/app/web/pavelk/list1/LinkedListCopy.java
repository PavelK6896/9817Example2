package app.web.pavelk.list1;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedListCopy implements List {

    int size = 0;

    Node first;
    Node last;

    class Node {
        Object item;
        Node next;
        Node prev;

        public Node(Object item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public Object getItem() {
            return item;
        }

        public void setItem(Object item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    '}';
        }
    }


    @Override
    public int size() {
        return 0;
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

        Node node = new Node(o, null, last);

        if (first == null) {
            first = node;
        }

        if (last == null) {
            last = node;
        } else {
            last.setNext(node);
            last = node;
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node node;
        if (first != null) {
            node = first;
            for (int i = 0; i < size; i++) {
                if (node.getNext() != null) {
                    if (node.getItem().equals(o)) {
                        if (node.getPrev() == null) {
                            first = node.getNext();
                            size--;
                            return true;
                        }
                        node.getPrev().setNext(node.getNext());
                    }
                } else {
                    if (node.getItem().equals(o)) {
                        node.getPrev().setNext(null);
                    }
                    break;
                }

                node = node.getNext();
            }
        }
        size--;
        return true;
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
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public Object get(int index) {
        Node node;
        if (first != null) {
            node = first;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    return node.getItem();
                }

                if (node.getNext() != null) {
                    node = node.getNext();
                }

            }
        }
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
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
        stringBuilder.append("LinkedListCopy{");

        Node node;
        if (first != null) {
            node = first;
            for (int i = 0; i < size; i++) {
                if (node.getNext() != null) {
                    stringBuilder.append(node.toString());
                    node = node.getNext();
                } else {
                    stringBuilder.append(node.toString());
                    break;
                }
            }
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
