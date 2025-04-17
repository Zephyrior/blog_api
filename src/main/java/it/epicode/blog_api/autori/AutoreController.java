package it.epicode.blog_api.autori;

import it.epicode.blog_api.common.CommonResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autori")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @GetMapping("/select")
    public List<AutoreResponse> findAllSelect() {
        return autoreService.findAllSelect();
    }

   @GetMapping("/{id}")
   public Autore findById(@PathVariable Long id) {
        return autoreService.findById(id);
   }


   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void deleteById(Long id) {
        autoreService.deleteById(id);
   }


   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public CommonResponse save(@RequestBody @Valid AutoreRequest autoreRequest) {
        return autoreService.save(autoreRequest);
   }

   @GetMapping
   public Page<AutoreResponse> findAll(@ParameterObject Pageable pageable) {
        return autoreService.findAll(pageable);
   }
}
