package br.com.prodpaymanager.exceptions;

public class SellerExceptions {

    public static class SellerFoundException extends RuntimeException {
        public SellerFoundException() {
            super("Vendedor já cadastrado");
        }
    }

    public static class SellerNotFoundException extends RuntimeException {
        public SellerNotFoundException() {
            super("Vendedor não cadastrado");
        }
    }
}
