package error.exception;

import error.ErrorsDeal;

public class CINException  extends ExceptionDeal {

    public CINException() {
        super(ErrorsDeal.CIN_FORMAT_ERROR);
    }
}
