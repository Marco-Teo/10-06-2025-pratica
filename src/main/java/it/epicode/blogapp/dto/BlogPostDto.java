package it.epicode.blogapp.dto;

import lombok.Data;

@Data
public class BlogPostDto {

    private String categoria;

    private String titolo;

    private String cover;

    private String contenuto;

    private int tempoDiLettura;

    private int autoreId;
}
