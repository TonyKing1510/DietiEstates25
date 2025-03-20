package it.unina.webtech.dto.request;
import it.unina.webtech.model.ClasseEnergetica;
import it.unina.webtech.model.TipoVendita;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RicercaImmobileDTORequest {

    private TipoVendita tipologiaVendita;

    private BigDecimal prezzoMinimo;

    private BigDecimal prezzoMaximo;

    private Integer numeroStanze;

    private ClasseEnergetica classeEnergetica;

    private String comune;

    private String citta;

    private String utenteCheRicerca;

    @Size(min = 0,max = 2,message = "Inserisci un numero di sessione pari a 0 1 o 2")
    private Integer sessioneUtente;

}
