/**
 * represents exception to raise when negative capacity is given
 */
public class NegativeCapacityException extends QueueException {
    /**
     * NegativeCapacityException constructor without parameters.
     */
    public NegativeCapacityException() {
    }

    /**
     * NegativeCapacityException constructor with message parameter
     *
     * @param msg the exception message
     */
    public NegativeCapacityException(String msg) {
        super(msg);
    }

    /**
     * NegativeCapacityException constructor with message and cause parameters
     *
     * @param message the exception message
     * @param cause   the cause of the exception
     */
    public NegativeCapacityException(String message, Throwable cause) {
        super(message, cause);
    }
}
