public class QueueException extends RuntimeException{
    public QueueException() {
    }

    public QueueException(String msg) {
        super(msg);
    }

    public QueueException(String message, Throwable cause) {
        super(message, cause);
    }

}
