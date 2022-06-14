import java.util.Iterator;
import java.lang.reflect.*;

/**
 * Represents queue that is realized by array according to given criterion in task.
 *
 * @param <E> generic element extends cloneable
 */
public class ArrayQueue<E extends Cloneable> implements Queue<E> {
    private final int maxSize;
    private int currSize;
    private int frontIndex;
    private int rearIndex;
    private E[] ququeList;

    /**
     * ArrayQueue constructor.
     * saves all queue details.
     * frontIndex - saves the index of the first item in the queue.
     * rearindex - saves the index of the last item in the queue.
     *
     * @param maxSize max size of queue
     * @throws NegativeCapacityException error when the queue size is negative.
     */
    public ArrayQueue(int maxSize) {
        if (maxSize < 0)
            throw new NegativeCapacityException("Negative capacity!");
        this.maxSize = maxSize;
        this.currSize = 0;
        this.ququeList = (E[]) new Cloneable[maxSize];
        this.frontIndex = 0;
        this.rearIndex = 0;

    }

    /**
     * add new item of type E to the arrayQueue.
     * add it to the end of the list using the rearIndex, calculating the new rearIndex using modulo and update it.
     *
     * @param element the new element to add to the queue
     * @throws QueueOverflowException error if the array is out of bounds.
     */
    @Override
    public void enqueue(E element) {
        if (this.currSize == this.maxSize)
            throw new QueueOverflowException("The queue reached its full capacity.");
        this.ququeList[this.rearIndex] = element;
        this.rearIndex = (this.rearIndex + 1) % maxSize;
        this.currSize++;
    }

    /**
     * remove an item of type E from the arrayQueue.
     * calculating the new frontIndex using modulo and update it.
     *
     * @return the first (entered) item in the queue
     * @throws EmptyQueueException error if the array is empty.
     */
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

    /**
     *
     * @return the first (entered) item in the queue
     * @throws EmptyQueueException error if the array is empty.
     */
    @Override
    public E peek() {
        if (this.currSize == 0)
            throw new EmptyQueueException("The queue is empty");
        return this.ququeList[this.frontIndex];
    }

    /**
     * @return queue size
     */
    @Override
    public int size() {
        return this.currSize;
    }

    /**
     * @return true if queue is empty and false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.currSize == 0;
    }

    /**
     * deep cloning of this array using covariant return type.
     * use invoke to get the clone method of element of type E, and surpass its private access.
     *
     * @return deep cloned object
     */
    @Override
    public ArrayQueue<E> clone() {
        try {
            ArrayQueue<E> returnedQueue = (ArrayQueue<E>) super.clone();
            returnedQueue.ququeList = (E[]) new Cloneable[maxSize];
            if (this.currSize == 0)
                return returnedQueue;
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

    /**
     * @return iterator of arrayQueue
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator<>(this.ququeList, this.frontIndex, this.maxSize, this.currSize);
    }

}
