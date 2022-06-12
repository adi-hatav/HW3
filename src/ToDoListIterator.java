import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class ToDoListIterator implements Iterator<Task> {
    private LinkedList<Task> taskSortedLinkedList;
    private int currPlace;
    private Date dueDate;
    private int taskNumber;

    public ToDoListIterator(LinkedList<Task> taskSortedLinkedList, Date dueDate,int taskNumber){
        this.taskSortedLinkedList = taskSortedLinkedList;
        this.currPlace = 1;
        this.dueDate = dueDate;
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean hasNext() {
        if(dueDate==null)
            return currPlace!=taskNumber;
        return currPlace!=taskNumber && taskSortedLinkedList.get(currPlace-1).getDueDate().before(dueDate);
    }

    @Override
    public Task next() {
        Task returnedIndex=this.taskSortedLinkedList.get(currPlace-1);
        this.currPlace++;
        return returnedIndex;
    }
}