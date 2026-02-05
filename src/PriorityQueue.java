public class PriorityQueue<T extends Comparable<T>> extends Queue<T> {
    @Override
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty() || data.compareTo(front.getData()) > 0) {
            newNode.setNext(front);
            front = newNode;
            if (rear == null) rear = newNode;
        } else {
            Node<T> current = front;
            while (current.getNext() != null &&
                    data.compareTo(current.getNext().getData()) <= 0) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            if (newNode.getNext() == null) rear = newNode;
        }
        size++;
    }
}
