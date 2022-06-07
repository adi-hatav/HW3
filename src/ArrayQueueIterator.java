import java.util.Iterator;

public class ArrayQueueIterator  <E extends Cloneable> implements Iterator<E> {
    private int maxSize;
    private int currSize;
    private int frontIndex;
    private E[] ququeList;

    public ArrayQueueIterator(E[]ququeList,int frontIndex,int maxSize,int currSize){
        this.maxSize = maxSize;
        this.currSize = currSize;
        this.ququeList = ququeList;
        this.frontIndex = frontIndex;
    }

    @Override
    public boolean hasNext() {
        return currSize!=0;
    }

    @Override
    public E next() {
        int returnedIndex=this.frontIndex;
        this.frontIndex=(this.frontIndex+1)%maxSize;
        currSize--;
        return (E)this.ququeList[returnedIndex];
    }
}
