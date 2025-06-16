package it.epicode.blogapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty(message = "il nome non puo essere vuoto")
    private String nome;
    @NotEmpty(message = "il cognome non puo essere vuoto")
    private String cognome;
    @NotEmpty(message = "lo username non puo essere vuoto")
    private String username;
    @NotEmpty(message = "la password non puo essere vuota")
    private String password;
}
