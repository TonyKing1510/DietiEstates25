package it.unina.webtech.model;


public class GestoreAgenziaImmobiliare extends Utente {
    private String partitaIva;

    private Agenzia agenziaAppartenente;

    private Admin adminAppartenente;

    private AccountGestore accountGestore;

    private boolean isAdmin;

    public GestoreAgenziaImmobiliare(boolean isAdmin, Agenzia agenzia,String cf) {
        this.isAdmin = isAdmin;
        this.setAgenziaAppartenente(agenzia);
        this.setCf(cf);
    }

    public GestoreAgenziaImmobiliare(String nome, String cognome,String societa, String cf){
        this.setNome(nome);
        this.setCognome(cognome);
        this.setCf(cf);
        this.agenziaAppartenente= new Agenzia(societa);
    }

    public GestoreAgenziaImmobiliare(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public boolean getisAdmin() {
        return isAdmin;
    }

    public void setisAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    public GestoreAgenziaImmobiliare(String nome, String cognome, String cf, String telefono, String via,String numeroCivico) {
        super(nome, cognome, cf, telefono, via,numeroCivico);
    }


    public GestoreAgenziaImmobiliare() {
    }

    public Agenzia getAgenziaAppartenente() {
        return agenziaAppartenente;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public void setAgenziaAppartenente(Agenzia agenziaAppartenente) {
        this.agenziaAppartenente = agenziaAppartenente;
    }

    public AccountGestore getAccountGestore() {
        return accountGestore;
    }

    public void setAccountGestore(AccountGestore accountGestore) {
        this.accountGestore = accountGestore;
    }

    public Admin getAdminAppartenente() {
        return adminAppartenente;
    }

    public void setAdminAppartenente(Admin adminAppartenente) {
        this.adminAppartenente = adminAppartenente;
    }
}
