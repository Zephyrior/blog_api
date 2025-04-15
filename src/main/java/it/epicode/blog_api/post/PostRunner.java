package it.epicode.blog_api.post;

import com.github.javafaker.Faker;
import it.epicode.blog_api.autori.Autore;
import it.epicode.blog_api.autori.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
@Order(2)
public class PostRunner implements CommandLineRunner {

    @Autowired
    private Faker faker;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AutoreRepository autoreRepository;


    @Override
    public void run(String... args) throws Exception {

        if (postRepository.count() == 0) {

            List<Autore> autori = autoreRepository.findAll();

            for (int i = 0; i < 10; i++) {
                Post post = new Post();
                post.setTitolo(faker.book().title());
                post.setContenuto(faker.lorem().sentence());
                post.setTempoDiLettura(faker.number().randomDigit());
                post.setCategoria(faker.book().genre());
                post.setAutore(autori.get(i));

                postRepository.save(post);
            }
        }
    }
}
