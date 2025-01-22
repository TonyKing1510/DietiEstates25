package it.unina.webtech.dto;

public class ClienteDTO {
    private String token;

    private boolean duplicato;

    private boolean emailNonEsistente;

    private boolean erroreInterno;

    public ClienteDTO() {}

    public ClienteDTO(String token, boolean duplicato, boolean emailNonEsistente) {
        this.token = token;
        this.duplicato = duplicato;
        this.emailNonEsistente = emailNonEsistente;
    }

    public ClienteDTO(boolean duplicato) {
        this.duplicato = duplicato;
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

    public void setErroreInterno(boolean erroreInterno) {
        this.erroreInterno = erroreInterno;
    }

    public boolean isErroreInterno() {
        return erroreInterno;
    }
}

