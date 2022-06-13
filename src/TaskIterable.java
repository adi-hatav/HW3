import java.util.Date;

/**
 * interface for toDoList, to implement setScanningDueDate method
 */
public interface TaskIterable extends Iterable<Task>{
    /**
     *
     * @param date due date of scanning - shows only scans before (or equal) to this date
     */
    public void setScanningDueDate(Date date);
}

