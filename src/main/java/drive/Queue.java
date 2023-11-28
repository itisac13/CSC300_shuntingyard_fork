package drive;

public class Queue<T> extends LinkedList<T> {

    public void enqueue(T data) {
        append(data);
    }

    public Node<T> dequeue() {
        if (isEmpty()) {
            return null;
        }

        Node<T> dequeuedNode = this.Head;
        removeHead();
        return dequeuedNode;
    }

    public Node<T> peek() {
        if (isEmpty()) {
            return null;
        }

        return this.Head;
    }
}