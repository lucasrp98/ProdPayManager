package br.com.prodpaymanager.exceptions;

public class SaleExceptions {

    public static class SaleFoundException extends RuntimeException {
        public SaleFoundException() {
            super("Venda já cadastrado");
        }
    }

}
