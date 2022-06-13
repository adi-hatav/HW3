/**
 * general queue exception class from which all queue exceptions will inherit/
 */
public class QueueException extends RuntimeException{
    /**
     * QueueException constructor without parameters.
     */
    public QueueException() {
    }
    /**
     * QueueException constructor with message parameter
     *
     * @param msg the exception message
     */
    public QueueException(String msg) {
        super(msg);
    }

    /**
     * QueueException constructor with message and cause parameters
     *
     * @param message the exception message
     * @param cause   the cause of the exception
     */
    public QueueException(String message, Throwable cause) {
        super(message, cause);
    }

}
