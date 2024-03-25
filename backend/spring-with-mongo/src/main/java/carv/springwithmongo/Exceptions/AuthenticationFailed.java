package carv.springwithmongo.Exceptions;

public class AuthenticationFailed extends Exception {
    public AuthenticationFailed(){}
    public AuthenticationFailed(String message){
        super(message);
    }
}
