package it.epicode.blog_api.autori;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
public class AutoreRunner implements CommandLineRunner {

    @Autowired
    private Faker faker;

    @Autowired
    private AutoreRepository autoreRepository;

    @Override
    public void run(String... args) throws Exception {

        if(autoreRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Autore autore = new Autore();
                autore.setNome(faker.name().firstName());
                autore.setCognome(faker.name().lastName());
                autore.setEmail(faker.internet().emailAddress());
                autore.setDataNascita(faker.date().birthday());
                autoreRepository.save(autore);
            }
        }
    }
}
