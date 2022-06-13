import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * represents ToDoList Iterator, to iterate over toDoList using foreach
 */
public class ToDoListIterator implements Iterator<Task> {
    private LinkedList<Task> taskSortedLinkedList;
    private int currPlace;
    private Date dueDate;
    private int taskNumber;

    /**
     * ToDoListIterator constructor.
     * currPlace - the current place of item to retrieve
     * @param taskSortedLinkedList list to iterate over
     * @param dueDate the due date of the scanning
     * @param taskNumber the number of tasks in list
     */
    public ToDoListIterator(LinkedList<Task> taskSortedLinkedList, Date dueDate,int taskNumber){
        this.taskSortedLinkedList = taskSortedLinkedList;
        this.currPlace = 0;
        this.dueDate = dueDate;
        this.taskNumber = taskNumber;
    }

    /**
     * if due date is null then returns if there are any more item to retrieve.
     * else, check if there are any more item to retrieve that did not pass the given due date.
     * @return true if there are any more item to retrieve and false otherwise.
     */
    @Override
    public boolean hasNext() {
        if(dueDate==null)
            return currPlace!=taskNumber;
        return currPlace!=taskNumber && !taskSortedLinkedList.get(currPlace).getDueDate().after(dueDate);
    }

    /**
     * increments currPlace by one.
     * @return the next  task to retrieve
     */
    @Override
    public Task next() {
        Task returnedIndex=this.taskSortedLinkedList.get(currPlace);
        this.currPlace++;
        return returnedIndex;
    }
}
