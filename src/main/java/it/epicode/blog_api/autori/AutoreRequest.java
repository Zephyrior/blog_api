package it.epicode.blog_api.autori;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoreRequest {

    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String email;

}
