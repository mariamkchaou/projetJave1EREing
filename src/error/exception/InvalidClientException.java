package error.exception;

import error.ErrorsDeal;

public class InvalidClientException  extends ExceptionDeal {

    public InvalidClientException() {

        super(ErrorsDeal.INVALID_CLIENT);
    }
}
