package com.ultra.excetion;

/**
 * @author admin
 */
public class TcpToServerException extends RuntimeException {

    public TcpToServerException() {
        super();
    }

    public TcpToServerException(String message) {
        super(message);
    }

    public TcpToServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TcpToServerException(Throwable cause) {
        super(cause);
    }

    protected TcpToServerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
