package it.epicode.blog_api.autori;

import it.epicode.blog_api.common.CommonResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    public List<AutoreResponse> findAllSelect() {
        List<Autore> autori = autoreRepository.findAll();
        return autori.stream()
                .map(a -> new AutoreResponse(a.getId(), a.getNome() + " " + a.getCognome()))
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

    public Autore save(Autore autore) {

        return autoreRepository.save(autore);
    }

//    public CommonResponse save(Autore autore) {
//        autoreRepository.save(autore);
//        return new CommonResponse(autore.getId());
//    }

    public CommonResponse save(AutoreRequest autoreRequest) {
        Autore autore = new Autore();
        BeanUtils.copyProperties(autoreRequest, autore);

        autoreRepository.save(autore);
        return new CommonResponse(autore.getId());
    }

    public Page<AutoreResponse> findAll(Pageable pageable) {
        Page<Autore> autori = autoreRepository.findAll(pageable);
        return autori.map(a -> new AutoreResponse(a.getId(), a.getNome() + " " + a.getCognome()));
    }
}
