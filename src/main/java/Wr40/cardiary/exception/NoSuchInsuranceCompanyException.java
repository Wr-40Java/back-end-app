package Wr40.cardiary.exception;

public class NoSuchInsuranceCompanyException extends RuntimeException {
    public NoSuchInsuranceCompanyException() {
        super("There is no company with given id");
    }
}
