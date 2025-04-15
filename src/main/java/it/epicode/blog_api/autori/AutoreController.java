package it.epicode.blog_api.autori;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
