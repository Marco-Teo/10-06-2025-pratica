package it.epicode.blogapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LogInDto {

    @NotEmpty(message = "lo username non puo essere vuoto")
    private String username;
    @NotEmpty(message = "la password non puo essere vuota")
    private String password;
}
