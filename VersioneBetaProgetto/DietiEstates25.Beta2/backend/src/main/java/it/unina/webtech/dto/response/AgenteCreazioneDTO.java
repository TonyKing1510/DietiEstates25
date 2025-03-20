package it.unina.webtech.dto.response;

public class AgenteCreazioneDTO {
    private boolean isEmailGiàRegistrata;

    private boolean isCfGiàRegistrato;

    private  boolean errore;
    public AgenteCreazioneDTO(boolean email,boolean cf){
        this.isEmailGiàRegistrata = email;this.isCfGiàRegistrato=cf;
    }

    public AgenteCreazioneDTO(boolean errore){
        this.errore = errore;
    }

    public AgenteCreazioneDTO(){}

    public boolean isCfGiàRegistrato() {
        return isCfGiàRegistrato;
    }

    public boolean isEmailGiàRegistrata() {
        return isEmailGiàRegistrata;
    }

    public void setCfGiàRegistrato(boolean cfGiàRegistrato) {
        isCfGiàRegistrato = cfGiàRegistrato;
    }

    public void setEmailGiàRegistrata(boolean emailGiàRegistrata) {
        isEmailGiàRegistrata = emailGiàRegistrata;
    }

    public void setErrore(boolean errore) {
        this.errore = errore;
    }

    public boolean isErrore() {
        return errore;
    }
}
