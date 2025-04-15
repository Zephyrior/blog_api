package it.epicode.blog_api.autori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autori")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @GetMapping("/select")
    public List<AutoreSelectResponse> findAllSelect() {
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
}
