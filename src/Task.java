import java.util.Date;

/**
 * represents singal task that contains description and due date of task
 */
public class Task implements Cloneable {
    private String description;
    private Date date;

    /**
     * Task constructor.
     * @param description description of the task
     * @param date due date of the task
     */
    public Task(String description, Date date) {
        this.description = description;
        this.date = date;
    }

    /**
     *
     * @return description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @return due date of task
     */
    public Date getDueDate() {
        return this.date;
    }

    /**
     * set due date of task
     * @param dueDate the new dues date of the task
     */
    public void setDueDate(Date dueDate) {
         this.date = dueDate;
    }

    /**
     *
     * @return task represented by a string.
     */
    @Override
    public String toString() {
        return  this.description + ", " + String.format("%td.%tm.%tY", date, date, date);
    }

    /**
     * deep clones the task using covariant return type.
     * @return the cloned object.
     */
    @Override
    public Task clone() {
        try {
            Task newTask = (Task) super.clone();
            newTask.date = (Date) this.date.clone();
            return newTask;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param otherTask otehr task to compare this task to
     * @return true if the other task has the same date and description
     * as this task and false otherwise.
     */
    @Override
    public boolean equals(Object otherTask){
        if (!(otherTask instanceof Task))
            return false;
        return (((Task) otherTask).description.equals(this.description) &&
                ((Task) otherTask).date.toString().equals(this.date.toString()));

    }

    /**
     *
     * @return unique hash code that represents this task
     */
    @Override
    public int hashCode(){
        return this.description.hashCode()+this.date.hashCode();

    }

}

