package error.exception;

import error.ErrorsDeal;

public class ClientNotFound extends  ExceptionDeal {

    public ClientNotFound() {

        super(ErrorsDeal.CLIENT_NOT_FOUND);
    }
}
