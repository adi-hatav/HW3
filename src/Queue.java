public interface Queue<E extends Cloneable> extends Iterable<E>, Cloneable {
    void enqueue(E element);
    E dequeue();
    E peek();
    int size();
    boolean isEmpty();
    Queue<E> clone();
}


