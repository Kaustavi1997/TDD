package cabinvoice.exception;

public class CabInvoiceGeneratorException extends Exception {
    public ExceptionType e;
    public enum ExceptionType{
        EMPTY_MAP, NO_SUCH_KEY, KEY_ALREADY_EXISTS,
    }
    public CabInvoiceGeneratorException(ExceptionType e, String message) {
        super(message);
        this.e = e;
    }
}
