package jpabook.jpashop.exception;

import net.bytebuddy.implementation.bytecode.Throw;
import org.aspectj.weaver.ast.Not;

public class NotEnoughStockException extends RuntimeException{

    public NotEnoughStockException(){
        super();
    }

    public NotEnoughStockException(String message){
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause){
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause){
        super(cause);
    }

    protected NotEnoughStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
