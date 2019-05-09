public class ArrayDeque<T> {
    private static final int INITSIZE = 8;
    private T[] arr;
    private int size;
    private int front, rear;

    public ArrayDeque() {
        arr = (T[]) new Object[INITSIZE];
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
        if (front >= rear) {
            int len1 = arr.length - front;
            int len2 = rear;
            if (len1 > 0) {
                System.arraycopy(arr, front, newArr, 0, len1);
            }
            if (len2 > 0) {
                System.arraycopy(arr, 0, newArr, len1, len2);
            }
        } else {
            System.arraycopy(newArr, 0, arr, front, size);
        }
        arr = newArr;
        front = 0;
        rear = size;
    }

    public void addFirst(T item) {
        if (size == arr.length) {
            resize(arr.length * 2);
        }
        front = decrement(front);
        arr[front] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == arr.length) {
            resize(arr.length * 2);
        }
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
            for (int i = front; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            for (int i = 0; i < rear; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            for (int i = front; i < rear; i++) {
                System.out.print(arr[i] + " ");
            }
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if (arr.length > INITSIZE * 2 && size * 4 < arr.length) {
            resize(arr.length / 2);
        }
        T popItem = arr[front];
        front = increment(front);
        size--;
        return popItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (arr.length > INITSIZE * 2 && size * 4 < arr.length) {
            resize(arr.length / 2);
        }
        rear = decrement(rear);
        T popItem = arr[rear];
        size--;
        return popItem;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return arr[(front + index) % arr.length];
    }
}
