import java.util.Iterator;
import java.lang.reflect.*;

public class ArrayQueue<E extends Cloneable> implements Queue<E> {
    private final int maxSize;
    private int currSize;
    private int frontIndex;
    private int rearIndex;
    private E[] ququeList;

    public ArrayQueue(int maxSize) {
        if (maxSize < 0)
            throw new NegativeCapacityException("Negative capacity!");
        this.maxSize = maxSize;
        this.currSize = 0;
        this.ququeList = (E[]) new Cloneable[maxSize];
        this.frontIndex = 0;
        this.rearIndex = 0;

    }

    @Override
    public void enqueue(E element) {
        if (this.currSize == this.maxSize)
            throw new QueueOverflowException("The queue reached its full capacity.");
        this.ququeList[this.rearIndex] = element;
        this.rearIndex = (this.rearIndex + 1) % maxSize;
        this.currSize++;
    }

    @Override
    public E dequeue() {
        if (this.currSize == 0)
            throw new EmptyQueueException("The queue is empty");
        E returnedElement = this.ququeList[this.frontIndex];
        this.ququeList[this.frontIndex] = null;
        this.frontIndex = (this.frontIndex + 1) % this.maxSize;
        currSize--;
        return returnedElement;
    }

    @Override
    public E peek() {
        if (this.currSize == 0)
            throw new EmptyQueueException("The queue is empty");
        return this.ququeList[this.frontIndex];
    }

    @Override
    public int size() {
        return this.currSize;
    }

    @Override
    public boolean isEmpty() {
        return this.currSize == 0;
    }

    @Override
    public ArrayQueue<E> clone() {
        try {
            ArrayQueue<E> returnedQueue = (ArrayQueue<E>) super.clone();
            returnedQueue.ququeList =  (E[]) new Cloneable[maxSize];
            Method cloneM = this.ququeList[this.frontIndex].getClass().getMethod("clone");
            E element;
            for (int i = 0; i < this.maxSize; i++) {
                if (this.ququeList[i] != null) {
                    element = this.ququeList[i];
                    returnedQueue.ququeList[i] = (E) cloneM.invoke(element);
                }
            }
            return returnedQueue;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator<>(this.ququeList, this.frontIndex, this.maxSize, this.currSize);
    }

}
