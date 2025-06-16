package it.epicode.blogapp.exeption;

public class UnAuthorizedExeption extends RuntimeException{
    public UnAuthorizedExeption(String message) {
        super(message);
    }
}
