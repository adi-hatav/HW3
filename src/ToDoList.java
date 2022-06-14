
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * represents list of tasks, implements cloneable and iterator
 */
public class ToDoList implements Cloneable, TaskIterable {
    private LinkedList<Task> taskLinkedList;
    private LinkedList<Task> taskSortedLinkedList;
    private int taskNumber;
    private Date dueDate;

    /**
     * ToDoList constructor, sets to do list parameters.
     * taskLinkedList - all tasks arranged by enter order
     * taskSortedLinkedList - all tasks sorted by date and description
     * default dueDate is null.
     */
    public ToDoList() {
        taskLinkedList = new LinkedList<Task>();
        taskSortedLinkedList = new LinkedList<Task>();
        taskNumber = 0;
        dueDate = null;
    }

    /**
     * @return number of tasks in list.
     */
    public int getTaskNumber() {
        return this.taskNumber;
    }

    /**
     * add new task to list. throws TaskAlreadyExistsException if the task already exist in list.
     * add new task to sorted list in correct place based on its date and description.
     *
     * @param task the new task to add
     */
    public void addTask(Task task) {
        if (checkAppearance(task)) {
            throw new TaskAlreadyExistsException("Cannot add the task!");
        }
        taskLinkedList.add(task);
        taskNumber++;

        // add to sorted list
        if (taskSortedLinkedList.isEmpty()) {
            taskSortedLinkedList.add(task);
        } else {
            int i = 0;
            for (Task tempTask : this.taskSortedLinkedList) {
                // checks if the added task is after task in index i
                if (task.getDueDate().after(tempTask.getDueDate())) {
                    i++;
                }
                // checks if the added task is at the same time as task in index i and its
                // description is alphabetically after
                else if (task.getDueDate().equals(tempTask.getDueDate()) &&
                        task.getDescription().compareTo(tempTask.getDescription()) > 0) {
                    i++;
                }
            }
            taskSortedLinkedList.add(i, task);

        }
    }

    /**
     * @param task new task to add to the list
     * @return true if task already exists in list and false otherwise
     */
    private boolean checkAppearance(Task task) {
        for (Task tempTask : this.taskLinkedList) {
            if (tempTask.getDescription().equals(task.getDescription()))
                return true;
        }
        return false;
    }

    /**
     *
     * @return string that represents the toDoList according to the assignment orders.
     */
    @Override
    public String toString() {
        String returnedString = "[";
        int i = 0;
        if (taskNumber > 0) {
            for (Task tempTask : this.taskLinkedList) {
                if (i < taskNumber - 1) {
                    returnedString = returnedString.concat("(");
                    returnedString = returnedString.concat(tempTask.toString());
                    returnedString = returnedString.concat(")");
                    returnedString = returnedString.concat(", ");
                    i++;
                }
            }
            returnedString = returnedString.concat("(");
            returnedString = returnedString.concat(this.taskLinkedList.get(taskNumber - 1).toString());
            returnedString = returnedString.concat(")");
        }
        returnedString = returnedString.concat("]");
        return returnedString;
    }

    /**
     * deep clones the toDoList
     * @return the cloned object
     */
    @Override
    public ToDoList clone() {
        try {
            ToDoList newToDoList = (ToDoList) super.clone();
            newToDoList.taskNumber = 0;
            newToDoList.taskLinkedList = new LinkedList<>();
            newToDoList.taskSortedLinkedList = new LinkedList<>();
            for (int i = 0; i < taskNumber; i++) {
                newToDoList.taskLinkedList.add(this.taskLinkedList.get(i).clone());
                newToDoList.taskSortedLinkedList.add(this.taskSortedLinkedList.get(i).clone());
                newToDoList.taskNumber++;
            }
            return newToDoList;

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * compares this toDoList to given other toDoList according to given assignment.
     * if a task exists in one of the lists and not in another one then returns false.
     * @param otherToDoList other toDoList object to compare to
     * @return true if objects are equeal and false otherwise.
     */
    @Override
    public boolean equals(Object otherToDoList) {
        if (!(otherToDoList instanceof ToDoList))
            return false;
        if (((ToDoList) otherToDoList).getTaskNumber() != this.taskNumber)
            return false;
        boolean exists;
        for (int i = 0; i < this.taskNumber; i++) {
            exists = false;
            for (int j = 0; j < this.taskNumber; j++)
                if (this.taskLinkedList.get(i).equals(((ToDoList) otherToDoList).taskLinkedList.get(j))) {
                    exists = true;
                    break;
                }
            if (!exists) return false;
        }
        return true;
    }

    /**
     *
     * @return unique hash code that represents this toDoList
     */
    @Override
    public int hashCode() {
        int returnedHash = 0;
        for (Task tempTask : this.taskLinkedList) {
            returnedHash += tempTask.hashCode();
        }
        return returnedHash;
    }

    /**
     * sets due date of scanning.
     * @param dueDate due date of scanning - shows only scans before (or equal) to this date
     */
    @Override
    public void setScanningDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * creates iterator for toDoList, creates new toDoList object and uses its sorted list.
     * it is done because the due dates of the tasks can be changed and the
     * list needs to be sorted again before iterating over it.
     * @return iterator object
     */
    @Override
    public Iterator<Task> iterator() {
        ToDoList newToDoList = new ToDoList();
        for (Task tempTask : this.taskLinkedList)
            newToDoList.addTask(tempTask);

        return new ToDoListIterator(newToDoList.taskSortedLinkedList, this.dueDate, this.taskNumber);
    }
}
