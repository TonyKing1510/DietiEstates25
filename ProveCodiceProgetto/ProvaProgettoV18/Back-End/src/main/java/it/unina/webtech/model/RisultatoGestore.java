package it.unina.webtech.model;

public class RisultatoGestore {
    private boolean isGestoreSemplice;

    private boolean isAdmin;

    private boolean credenzialiErrate;


    public RisultatoGestore(){}

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setCredenzialiErrate(boolean credenzialiErrate) {
        this.credenzialiErrate = credenzialiErrate;
    }

    public void setGestoreSemplice(boolean gestoreSemplice) {
        isGestoreSemplice = gestoreSemplice;
    }

    public boolean isGestoreSemplice() {
        return isGestoreSemplice;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isCredenzialiErrate() {
        return credenzialiErrate;
    }
}
