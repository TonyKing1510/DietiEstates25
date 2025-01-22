package it.unina.webtech.model;

public enum classe_energetica {
    A_PLUS_PLUS("A++"),
    A_PLUS("A+"),
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G");

    private final String valore;

    classe_energetica(String valore) {
        this.valore = valore;
    }

    public String getValore() {
        return valore;
    }

    @Override
    public String toString() {
        return valore;
    }
}

