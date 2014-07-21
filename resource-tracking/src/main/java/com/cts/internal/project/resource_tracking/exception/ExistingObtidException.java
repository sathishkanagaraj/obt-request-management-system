package com.cts.internal.project.resource_tracking.exception;

public class ExistingObtidException extends Exception {
    private static final long serialVersionUID = 1L;

    public ExistingObtidException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ExistingObtidException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ExistingObtidException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ExistingObtidException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
