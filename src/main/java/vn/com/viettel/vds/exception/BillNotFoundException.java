package vn.com.viettel.vds.exception;

public class BillNotFoundException extends RuntimeException{
    public BillNotFoundException(String message){
        super(message);
    }
}
