package it.unina.webtech.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
public class AddGestoreRequestDTO {

    @NotNull
    @NotEmpty
    private String nome;


    @NotNull
    @NotEmpty
    private String cognome;


    @NotNull
    @NotEmpty
    @Size(min = 16,max = 16)
    private String cf;

    @NotNull
    @NotEmpty
    @Size
    private String telefono;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String email;


    @NotNull
    @NotEmpty
    @Size(min = 16,max = 16)
    private String cfGestoreAdmin;


    @NotNull
    @NotEmpty
    private String nomeAgenzia;
}
