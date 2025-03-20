package it.unina.webtech.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUtenteResponse {
    private String role;

    private String token;

    private boolean admin;

}
