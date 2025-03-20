package it.unina.webtech.dto.response;

public class ImmobileDTO {
    private boolean immobileNonInserito;

    private boolean immobileInserito;

    private boolean erroreInterno;

    private boolean immobileGiàPresente;


    public ImmobileDTO(boolean immobileInserito,boolean erroreInterno,boolean immobileNonInserito) {
        this.immobileInserito = immobileInserito;
        this.erroreInterno = erroreInterno;
        this.immobileNonInserito = immobileNonInserito;
    }

    public ImmobileDTO(boolean immobileInserito,boolean erroreInterno,boolean immobileNonInserito,boolean duplicato) {
        this.immobileInserito = immobileInserito;
        this.erroreInterno = erroreInterno;
        this.immobileNonInserito = immobileNonInserito;
        this.immobileGiàPresente = duplicato;
    }

    public void setErroreInterno(boolean erroreInterno) {
        this.erroreInterno = erroreInterno;
    }

    public boolean isErroreInterno() {
        return erroreInterno;
    }

    public void setImmobileInserito(boolean immobileInserito) {
        this.immobileInserito = immobileInserito;
    }

    public void setImmobileNonInserito(boolean immobileNonInserito) {
        this.immobileNonInserito = immobileNonInserito;
    }

    public boolean isImmobileInserito() {
        return immobileInserito;
    }

    public boolean isImmobileNonInserito() {
        return immobileNonInserito;
    }
}
