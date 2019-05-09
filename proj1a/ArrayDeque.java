public class ArrayDeque<T> {
    private T[] arr;
    private int size;
    private int front, rear;

    public ArrayDeque(T item) {
        arr = (T[]) new Object[8];
        arr[0] = item;
        front = 0;
        rear = 1;
        size = 1;
    }

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        front = 0;
        rear = 0;
        size = 0;
    }

    private int increment(int n) {
        return (n + 1) % arr.length;
    }

    private int decrement(int n) {
        return (n - 1 + arr.length) % arr.length;
    }

    private void resize(int newSize) {
        T[] newArr = (T[]) new Object[newSize];
        if (front > rear) {
            int len1 = arr.length - front;
            int len2 = rear;
            System.arraycopy(newArr, 0, arr, front, len1);
            System.arraycopy(newArr, len1, arr, 0, len2);
        } else {
            System.arraycopy(newArr, 0, arr, front, size);
        }
        arr = newArr;
        front = 0;
        rear = size;
    }

    public void addFirst(T item) {
        if (size == arr.length)
            resize(arr.length * 2);
        front = decrement(front);
        arr[front] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == arr.length)
            resize(arr.length * 2);
        arr[rear] = item;
        rear = increment(rear);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (front > rear) {
            for (int i = front; i < arr.length; i++)
                System.out.print(arr[i] + " ");
            for (int i = 0; i < rear; i++)
                System.out.print(arr[i] + " ");
        } else {
            for (int i = front; i < rear; i++)
                System.out.print(arr[i] + " ");

        }
    }

    public T removeFirst() {
        T popItem = arr[front];
        front = increment(front);
        size--;
        if (size * 4 < arr.length)
            resize(arr.length / 2);
        return popItem;
    }

    public T removeLast() {
        rear = decrement(rear);
        T popItem = arr[rear];
        size--;
        if (size * 4 < arr.length)
            resize(arr.length / 2);
        return popItem;
    }

    public T get(int index) {
        if (index >= size)
            return null;
        return arr[(front + index) % arr.length];
    }
}
