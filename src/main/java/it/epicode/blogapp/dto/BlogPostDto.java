package it.epicode.blogapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BlogPostDto {

    @NotEmpty(message =  "la categotia non puo seere nullo o vuota")
    private String categoria;

    @NotEmpty(message =  "il titolo non puo seere nullo o vuoto")
    private String titolo;

    private String cover;

    @NotEmpty(message =  "il contenuto non puo seere nullo o vuoto")
    private String contenuto;

    private int tempoDiLettura;

    private int autoreId;
}
