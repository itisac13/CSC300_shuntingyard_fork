package drive;
public class Stack<T> extends LinkedList<T> {

    public void push(T data) {
        prepend(data);
    }

    public Node<T> pop() {
        if (isEmpty()) {
            return null;
        }

        Node<T> removedNode = this.Head;
        removeHead();
        return removedNode;
    }

    public Node<T> peek() {
        if (isEmpty()) {
            return null;
        }
        return this.Head;
    }
}