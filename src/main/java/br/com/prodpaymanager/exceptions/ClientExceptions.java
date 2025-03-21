package br.com.prodpaymanager.exceptions;

public class ClientExceptions {
    public static class ClientFoundException extends RuntimeException {
        public ClientFoundException() {
            super("Cliente já cadastrado");
        }
    }

    public static class ClientNotFoundException extends RuntimeException {
        public ClientNotFoundException() {
            super("Client não cadastrado");
        }
    }
}
