package error.exception;

import error.ErrorsDeal;

public class EmailFormatException extends  ExceptionDeal {

    public EmailFormatException() {

        super(ErrorsDeal.EMAIL_FORMAT_ERROR);
    }
}
