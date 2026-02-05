import java.util.List;

public class Queue<T> {
    protected Node<T> front;
    protected Node<T> rear;
    protected int size;

    public Queue() {
        front = rear = null;
        size = 0;
    }

    public Queue(List<T> list) {
        this();
        for (T item : list) {
            enqueue(item);
        }
    }

    public boolean isEmpty() { return front == null; }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) return null;
        T data = front.getData();
        front = front.getNext();
        if (front == null) rear = null;
        size--;
        return data;
    }

    public int size() { return size; }

    public void displayAllElements() {
        Node<T> current = front;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}
