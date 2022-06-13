import java.util.Date;

public class Task implements Cloneable {
    private String description;
    private Date date;

    public Task(String description, Date date) {
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDueDate() {
        return this.date;
    }

    public void setDueDate(Date dueDate) {
         this.date = dueDate;
    }

    @Override
    public String toString() {
        return  this.description + ", " + String.format("%td.%tm.%tY", date, date, date);
    }

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

    @Override
    public boolean equals(Object otherTask){
        if (!(otherTask instanceof Task))
            return false;
        return (((Task) otherTask).description.equals(this.description) &&
                ((Task) otherTask).date.toString().equals(this.date.toString()));

    }
    @Override
    public int hashCode(){
        return this.description.hashCode()+this.date.hashCode();

    }

}

