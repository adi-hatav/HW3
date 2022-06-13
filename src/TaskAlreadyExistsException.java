/**
 * represents exception to raise when task already exists in to do list
 */
public class TaskAlreadyExistsException extends RuntimeException {
    /**
     * TaskAlreadyExistsException constructor without parameters.
     */
    public TaskAlreadyExistsException() {
    }
    /**
     * TaskAlreadyExistsException constructor with message parameter
     *
     * @param msg the exception message
     */
    public TaskAlreadyExistsException(String msg) {
        super(msg);
    }
    /**
     * TaskAlreadyExistsException constructor with message and cause parameters
     *
     * @param message the exception message
     * @param cause   the cause of the exception
     */
    public TaskAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
