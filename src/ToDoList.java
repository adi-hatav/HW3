import org.w3c.dom.NodeList;

import java.util.LinkedList;

public class ToDoList implements Cloneable{
    private LinkedList<Task> taskLinkedList;

    public ToDoList(){
        taskLinkedList = new LinkedList<Task>();
    }

    public void addTask(Task task){
        if (checkAppearance(task))
            throw new TaskAlreadyExistsException("Cannot add the task!");
        if (taskLinkedList.isEmpty())
            taskLinkedList.add(task);
        else {
            int i=0;
            for (Task tempTask:this.taskLinkedList){
                if (task.getDate().after(tempTask.getDate())) {
                    i++;
                    continue;
                }
                taskLinkedList.add(i+1,task);
                break;
            }
        }
    }

    private boolean checkAppearance(Task task){
        for (Task tempTask:this.taskLinkedList){
            if (tempTask.equals(task))
                return true;
        }
        return false;
    }

}
