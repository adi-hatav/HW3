/**
 * represents exception to raise when queue is empty.
 */
public class EmptyQueueException extends QueueException {
    /**
     * EmptyQueueException constructor without parameters.
     */
    public EmptyQueueException() {
    }

    /**
     * EmptyQueueException constructor with message parameter
     * @param msg the exception message
     */
    public EmptyQueueException(String msg) {
        super(msg);
    }

    /**
     * EmptyQueueException constructor with message and cause parameters
     * @param message the exception message
     * @param cause the cause of the exception
     */
    public EmptyQueueException(String message, Throwable cause) {
        super(message, cause);
    }
}
