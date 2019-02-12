package error.exception;

import error.ErrorsDeal;

public class ExceptionDeal extends Exception {

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
