package morsecode.lists;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    private Node<T> next;
    final private T data;

    public Node(T data) {
        this.data= data;
    }

    public T get() {
        return data;
    }

    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("bad index: "+ index);
        } else if (index == 0) {
            return data;
        } else if (next == null) {
            throw new IndexOutOfBoundsException("bad index: "+ index);
        }

        return next.get(index - 1);
    }


    public Node<T> add(T data) {
        if (next == null) {
            return next= new Node<T>(data);
        } else {
            return next.add(data);
        }
    }

    public int compareTo(Node<T> node) {
        if (node == null) { return -1; }
        return this.compareTo(node);
    }

    public Node<T> sort() {
        if (next == null) {
            return new Node<>(data);
        }
        return next.sort(new Node<>(data));
    }

    private Node<T> sort(Node<T> sorted) {
        Node<T> ptr= sorted;

        while (ptr.next != null && this.compareTo(ptr.next) > 0) {
            // advance the pointer until we are at the point of insertion
            ptr= ptr.next;
        }

        if (this.compareTo(ptr) < 0) {
            Node<T> newHead= new Node<>(data);
            newHead.next= ptr;
            sorted= newHead;
        } else {
            ptr.insert(data);
        }

        if (next != null) {
            return next.sort(sorted);
        } else {
            return sorted;
        }
    }

    private Node<T> insert(T data) {
        Node<T> node= new Node<>(data);
        node.next= next;
        this.next= node;
        return node;
    }


    public Node<T> reverse() {
        if (next != null) {
            Node<T> reversed= next.reverse();
            reversed.add(this.data);
            return reversed;
        }
        return new Node<>(data);
    }

    public Node<T> tail() {
        return (next == null ? this : next.tail());
    }

    public Node<T> next() { return next; }
    public boolean hasNext() { return next != null; }

    public boolean contains(T value) {
        if (this.data.compareTo(value) == 0) { return true; }
        return next == null ? false : next.contains(value);
    }

    public String toString() {

        StringBuilder sb= new StringBuilder();
        sb.append(data);

        if (next != null) {
            sb.append(",");
            sb.append(next.toString());
        }
        return sb.toString();
    }


}
