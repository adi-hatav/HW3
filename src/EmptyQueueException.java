public class EmptyQueueException extends QueueException {
    public EmptyQueueException() {
    }

    public EmptyQueueException(String msg) {
        super(msg);
    }
    public EmptyQueueException(String message, Throwable cause) {
        super(message, cause);
    }
}
