/**
 * represents exception to raise when queue is over flow
 */
public class QueueOverflowException extends QueueException {
    /**
     * QueueOverflowException constructor without parameters.
     */
    public QueueOverflowException() {
    }

    /**
     * QueueOverflowException constructor with message parameter
     *
     * @param msg the exception message
     */
    public QueueOverflowException(String msg) {
        super(msg);
    }

    /**
     * QueueOverflowException constructor with message and cause parameters
     *
     * @param message the exception message
     * @param cause   the cause of the exception
     */
    public QueueOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
