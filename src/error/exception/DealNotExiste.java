package error.exception;

import error.ErrorsDeal;

public class DealNotExiste extends  ExceptionDeal {

    public DealNotExiste() {

        super(ErrorsDeal.DEAL_NOT_EXISTE);
    }
}
