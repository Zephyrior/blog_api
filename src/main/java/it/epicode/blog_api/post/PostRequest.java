package it.epicode.blog_api.post;

import it.epicode.blog_api.autori.Autore;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

public class PostRequest {


    private String titolo;

    private String categoria;

    private String contenuto;

    private int tempoDiLettura;

    private Autore autore;
}
