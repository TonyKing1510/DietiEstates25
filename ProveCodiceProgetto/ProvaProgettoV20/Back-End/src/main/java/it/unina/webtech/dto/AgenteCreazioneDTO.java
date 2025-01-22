package it.unina.webtech.dto;

public class AgenteCreazioneDTO {
    private boolean isEmailGiàRegistrata;

    private boolean isCfGiàRegistrato;


    public AgenteCreazioneDTO(boolean email){
        this.isEmailGiàRegistrata = email;
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
}
