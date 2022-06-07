public class QueueOverflowException extends QueueException{
    public QueueOverflowException() {
    }

    public QueueOverflowException(String msg) {
        super(msg);
    }

    public QueueOverflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
