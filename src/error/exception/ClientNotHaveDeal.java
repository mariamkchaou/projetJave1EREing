package error.exception;

import error.ErrorsDeal;

public class ClientNotHaveDeal extends  ExceptionDeal {

    public ClientNotHaveDeal() {

        super(ErrorsDeal.CLIENT_NOT_HAVE_DEAL);
    }
}