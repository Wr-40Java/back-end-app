package Wr40.cardiary.exception;

public class InsuranceCompanyAlreadyExistsException extends RuntimeException{
    public InsuranceCompanyAlreadyExistsException() {
        super("Insurance company by this name already exists!");
    }
}
