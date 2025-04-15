package it.epicode.blog_api.autori;

import it.epicode.blog_api.common.CommonResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    public List<AutoreSelectResponse> findAllSelect() {
        List<Autore> autori = autoreRepository.findAll();
        return autori.stream()
                .map(a -> new AutoreSelectResponse(a.getId(), a.getNome() + " " + a.getCognome()))
                .toList();
    }

    public Autore findById(Long id) {
        return autoreRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        Autore autore = autoreRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Autore not found"));
        autoreRepository.delete(autore);
    }

//    public CommonResponse save(Autore autore) {
//        autoreRepository.save(autore);
//        return new CommonResponse(autore.getId());
//    }
}
