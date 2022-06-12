import java.util.Date;

public interface TaskIterable extends Iterable<Task>{
    public void setScanningDueDate(Date date);
}

