package morsecode.lists;

import java.util.Arrays;
import java.util.Iterator;

public class NodeList<T extends Comparable<T>> implements Iterable<T>, Comparable<NodeList<T>> {

    private Node<T> head;
    private Node<T> tail;

    public NodeList() { }
    public NodeList(T[] array) {
        Arrays.stream(array).forEach(e -> this.add(e));
    }

    protected NodeList(Node<T> head) {
        this.head= head;
        this.tail= head.tail();
        Node<T> ptr= head;
    }

    public NodeList<T> add(T value) {
        if (head == null) {
            head= tail= new Node<>(value);
        } else {
            this.tail= tail.add(value);
        }
        return this;
    }

    public String toString() {
        if (head == null) {
            return "[null]";
        }
        return "["+ head.toString() +"]";
    }

    public NodeList<T> sort() {
        return new NodeList<>(head.sort());
    }
    public NodeList<T> reverse() {
        return new NodeList<>(head.reverse());
    }

    public Node<T> getHead() { return head; }
    public Node<T> getTail() { return tail; }
    public boolean isEmpty() { return head == null; }

    public T get(int index) { return head.get(index); }

    public boolean contains(T value) { return head.contains(value); }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current= head;
            @Override
            public boolean hasNext() {
                return current.hasNext();
            }

            @Override
            public T next() {
                T data= current.get();
                current= current.next();
                return data;
            }
        };
    }

    @Override
    public int compareTo(NodeList<T> o) {
        int i= 0;
        for (T a : this) {
            try {
                int diff = a.compareTo(o.get(i++));
                if (diff != 0) {
                    return diff;
                }
            } catch (IndexOutOfBoundsException inequal) {
                return 1;
            }
        }
        try {
            o.get(i+1);
            return -1;
        } catch (IndexOutOfBoundsException ignore) {
            return 0;
        }
    }
}

