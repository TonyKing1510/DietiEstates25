package com.example.prova2.dto;

public class ClienteDTO {
    private String token;

    private boolean duplicato;

    private boolean emailNonEsistente;

    private boolean erroreInterno;

    public boolean isErroreInterno() {
        return erroreInterno;
    }

    public void setErroreInterno(boolean erroreInterno) {
        this.erroreInterno = erroreInterno;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isDuplicato() {
        return duplicato;
    }

    public void setDuplicato(boolean duplicato) {
        this.duplicato = duplicato;
    }

    public boolean isEmailNonEsistente() {
        return emailNonEsistente;
    }

    public void setEmailNonEsistente(boolean emailNonEsistente) {
        this.emailNonEsistente = emailNonEsistente;
    }
}
