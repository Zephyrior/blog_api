package it.epicode.blog_api.post;

import it.epicode.blog_api.autori.Autore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Posts")

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titolo;

    @Column
    private String cover = "https://picsum.photos/200/300";

    @Column
    private String categoria;

    @Column
    private String contenuto;

    @Column
    private int tempoDiLettura;

    @ManyToOne
    private Autore autore;
}