package br.com.prodpaymanager.exceptions;

public class ProductExceptions {

    public static class ProductFoundException extends RuntimeException {
        public ProductFoundException() {
            super("Produto já cadastrado");
        }
    }

    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException() {
            super("Produto não cadastrado");
        }
    }

}
