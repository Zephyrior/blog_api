package it.epicode.blog_api.autori;

import it.epicode.blog_api.post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Autori")

public class Autore {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String cognome;

    @Column(nullable = false, length = 100)
    private String email;

    @Column
    private LocalDate dataNascita;

    @Column
    private String avatar = "https://ui-avatars.com/api/?name=Mario+Rossi";

    @OneToMany(mappedBy = "autore", cascade = CascadeType.ALL)
    private List<Post> post = new ArrayList<>();

}