package it.epicode.blogapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoreDto {

    @NotEmpty(message =  "il nome non puo seere nullo o vuoto")
    private String nome;

    @NotNull(message =  "il cognome non puo seere nullo o vuoto")
    private String cognome;

    @NotNull(message = "non puo essere null")
    private String email;

    private LocalDate dataDiNascita;
}
