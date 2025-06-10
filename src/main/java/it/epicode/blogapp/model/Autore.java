package it.epicode.blogapp.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Autore {

    private int id;

    private String nome;

    private String cognome;

    private String email;

    private LocalDate dataDiNascita;

    private String avatar = "https://ui-avatars.com/api/";
}
