public class TaskAlreadyExistsException extends RuntimeException {
    public TaskAlreadyExistsException() {
    }

    public TaskAlreadyExistsException(String msg) {
        super(msg);
    }

    public TaskAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
