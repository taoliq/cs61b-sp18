public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        Node ptr = sentinel.next;
        sentinel.next = ptr.next;
        ptr.next.prev = sentinel;
        size--;
        return ptr.item;
    }

    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        Node ptr = sentinel.prev;
        sentinel.prev = ptr.prev;
        ptr.prev.next = sentinel;
        size--;
        return ptr.item;
    }

    public T get(int index) {
        Node ptr = sentinel.next;
        while (ptr != sentinel) {
            if (index == 0) {
                return ptr.item;
            }
            ptr = ptr.next;
            index--;
        }
        return null;
    }

    private T getRecHelper(Node node, int index) {
        if (node == sentinel) {
            return null;
        }
        if (index == 0) {
            return node.item;
        }
        return getRecHelper(node.next, index - 1);
    }

    public T getRecursive(int index) {
        return getRecHelper(sentinel.next, index);
    }

}
