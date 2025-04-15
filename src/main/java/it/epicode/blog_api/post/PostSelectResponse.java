package it.epicode.blog_api.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSelectResponse {

    private Long id;
    private String titolo;
//    private String autoreNomeCognome;
    private String categoria;
    private String contenuto;
    private String autoreNomeCognome;
}
