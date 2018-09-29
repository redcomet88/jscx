package jssvc.base.exception;

public class BusinessException extends Exception {
    private static final long serialVersionUID = 480484463519946949L;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable e) {
        super(e);
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
    }

}
