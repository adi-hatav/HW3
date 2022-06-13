import org.w3c.dom.NodeList;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class ToDoList implements Cloneable,TaskIterable {
    private LinkedList<Task> taskLinkedList;
    private LinkedList<Task> taskSortedLinkedList;
    private int taskNumber;
    private Date dueDate;

    public ToDoList() {
        taskLinkedList = new LinkedList<Task>();
        taskSortedLinkedList = new LinkedList<Task>();
        taskNumber = 0;
        dueDate = null;
    }

    public int getTaskNumber() {
        return this.taskNumber;
    }

    public void addTask(Task task) {
        if (checkAppearance(task)) {
            throw new TaskAlreadyExistsException("Cannot add the task!");
        }
        taskLinkedList.add(task);
        taskNumber++;
        if (taskSortedLinkedList.isEmpty()) {
            taskSortedLinkedList.add(task);
        } else {
            int i = 0;
            for (Task tempTask : this.taskSortedLinkedList) {
                if (task.getDueDate().after(tempTask.getDueDate())) {
                    i++;
                }
                else if (task.getDueDate().equals(tempTask.getDueDate()) &&
                        task.getDescription().compareTo(tempTask.getDescription()) > 0) {
                    i++;
                }
            }
                taskSortedLinkedList.add(i , task);

        }
    }

    private boolean checkAppearance(Task task) {
        for (Task tempTask : this.taskLinkedList) {
            if (tempTask.getDescription().equals(task.getDescription()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String returnedString = "[";
        int i = 0;
        if (taskNumber >0) {
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

    @Override
    public ToDoList clone() {
        try {
            ToDoList newToDoList = (ToDoList) super.clone();
            newToDoList.taskNumber=0;
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

    @Override
    public int hashCode(){
        int returnedHash = 0;
        for(Task tempTask: this.taskLinkedList){
            returnedHash += tempTask.hashCode();
        }
        return returnedHash;
    }

    @Override
    public void setScanningDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public Iterator<Task> iterator() {
        ToDoList newToDoList = new ToDoList();
        for(Task tempTask: this.taskLinkedList)
            newToDoList.addTask(tempTask);

        return new ToDoListIterator(newToDoList.taskSortedLinkedList,this.dueDate,this.taskNumber);
    }
}
