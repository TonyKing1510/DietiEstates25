package com.example.prova2.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateDatiResponseDTO {

    public UpdateDatiResponseDTO() {}

    private boolean emailDuplicate;

    private boolean error;

    public UpdateDatiResponseDTO(boolean emailDuplicate, boolean error) {
        this.emailDuplicate = emailDuplicate;
        this.error = error;
    }


    public boolean getEmailDuplicate() {
        return emailDuplicate;
    }

    public void setEmailDuplicate(boolean emailDuplicate) {
        this.emailDuplicate = emailDuplicate;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
