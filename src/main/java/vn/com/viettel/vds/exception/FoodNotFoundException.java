package vn.com.viettel.vds.exception;

public class FoodNotFoundException extends RuntimeException {

    public FoodNotFoundException(String message){
        super(message);
    }
}
