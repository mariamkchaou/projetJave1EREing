package error.exception;

import error.ErrorsDeal;

/**
 * c est un classe generale on ne doit pas l instantiet
 */

public abstract   class ExceptionDeal extends Exception {

    private ErrorsDeal error;

    public ErrorsDeal getError() {
        return error;
    }

    public void setError(ErrorsDeal error) {
        this.error = error;
    }

    public ExceptionDeal(ErrorsDeal error) {
        super();
        this.error = error;
    }
}
