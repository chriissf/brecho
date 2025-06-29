package com.brecho.SistemasVendas.helpers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {

    // Código HTTP de status
    private int status = 500; // 500 por padrão (Internal Server Error)

    // Construtor padrão (sem status)
    public AppException(String message) {
        super(message);
    }

    // Construtor com status HTTP
    public AppException(String message, int status) {
        super(message);
        this.status = status;
    }

    // Construtor com status HTTP e causa (Throwable)
    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    // Construtor completo com status e causa
    public AppException(String message, int status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    // Método para retornar o código de status HTTP
    public int getStatusCode() {
        return status;
    }
}
