package it.unina.webtech.dto;

public class AgenteDTO {

    private boolean credenzialiSbagliate;

    private boolean erroreInterno;

    private String token;

    public AgenteDTO() {}

    public AgenteDTO(boolean credenzialiSbagliate,boolean erroreInterno) {
        this.credenzialiSbagliate = credenzialiSbagliate;
        this.erroreInterno = erroreInterno;
    }


    public AgenteDTO(boolean erroreInterno){
        this.erroreInterno = erroreInterno;
    }



    public boolean isCredenzialiSbagliate() {
        return credenzialiSbagliate;
    }

    public boolean isErroreInterno() {
        return erroreInterno;
    }

    public void setErroreInterno(boolean erroreInterno) {
        this.erroreInterno = erroreInterno;
    }

    public void setCredenzialiSbagliate(boolean credenzialiSbagliate) {
        this.credenzialiSbagliate = credenzialiSbagliate;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
