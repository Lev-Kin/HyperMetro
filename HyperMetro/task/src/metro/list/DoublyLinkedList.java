package metro.list;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data, null, head);
        if (size == 0) {
            head = tail = newNode;
        } else {
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data, tail, null);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public void removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        if (size == 1) {
            head = tail = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        size--;
    }

    public void removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }
        size--;
    }

    public void remove(Node<T> current) {
        if (current == null) {
            throw new NoSuchElementException();
        }

        if (current == head) {
            removeFirst();
            return;
        }
        if (current == tail) {
            removeLast();
            return;
        }

        current.getPrevious().setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());
        size--;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        Node<T> tmp = head;
        StringBuilder output = new StringBuilder();
        while (tmp != null) {
            output.append(tmp.getData()).append("\n");
            tmp = tmp.getNext();
        }
        return output.toString();
    }
}
