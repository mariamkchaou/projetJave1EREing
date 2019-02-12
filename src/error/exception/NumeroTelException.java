package error.exception;

import error.ErrorsDeal;

public class NumeroTelException extends ExceptionDeal {

    public NumeroTelException() {

        super(ErrorsDeal.Tel_FORMAT_ERROR);
    }

}
