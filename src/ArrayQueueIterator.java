import java.util.Iterator;

/**
 * iterator over arrayQueue for using foreach on arrayQueue
 * @param <E> generic element extends cloneable
 */
public class ArrayQueueIterator  <E extends Cloneable> implements Iterator<E> {
    private int maxSize;
    private int currSize;
    private int frontIndex;
    private E[] ququeList;

    /**
     * ArrayQueueIterator constructor.
     * @param ququeList list to iterate over
     * @param frontIndex the index of the first item in queue
     * @param maxSize the max size of queue
     * @param currSize the current size of remained items to retrieve
     */
    public ArrayQueueIterator(E[]ququeList,int frontIndex,int maxSize,int currSize){
        this.maxSize = maxSize;
        this.currSize = currSize;
        this.ququeList = ququeList;
        this.frontIndex = frontIndex;
    }

    /**
     *
     * @return true if there is next item to retrieve and false otherwise.
     */
    @Override
    public boolean hasNext() {
        return currSize!=0;
    }

    /**
     * calculates frontIndex by using modulo.
     * @return the next item in frontIndex to retrieve from queue
     */
    @Override
    public E next() {
        int returnedIndex=this.frontIndex;
        this.frontIndex=(this.frontIndex+1)%maxSize;
        currSize--;
        return (E)this.ququeList[returnedIndex];
    }
}
