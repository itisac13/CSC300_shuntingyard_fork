package drive;

public class Node<T> {
    public T Data;
    public Node<T> NextNode;
    public Node<T> PreviousNode;

    public Node(){
        this.Data = null;
        this.NextNode = null;
        this.PreviousNode = null;
    }

    public Node(T input){
        this.Data = input;
        this.NextNode = null;
        this.PreviousNode = null;
    }
}
