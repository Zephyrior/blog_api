package it.epicode.blog_api.autori;

import it.epicode.blog_api.common.CommonResponse;
import it.epicode.blog_api.email.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/autori")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @Autowired
    private EmailSenderService emailSenderService;

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
   public CommonResponse save(@RequestBody @Valid AutoreRequest autoreRequest)  {
        CommonResponse response = autoreService.save(autoreRequest);
        String email = autoreRequest.getEmail();

       try {
           emailSenderService.sendEmail(
                   email,
                   "Autore registrato",
                   "Autore " + autoreRequest.getNome() + " " + autoreRequest.getCognome() + " è stato registrato"
           );
       } catch (MessagingException e) {
           // Log the exception or handle it as needed
           System.err.println("Errore durante l'invio dell'email: " + e.getMessage());
       }

        return response;

//        emailSenderService.sendEmail("jackoat2001@gmail.com", "Autore registrato", "Autore " + autoreRequest.getNome() + " " + autoreRequest.getCognome() + " è stato registrato");
   }

   @GetMapping
   public Page<AutoreResponse> findAll(@ParameterObject Pageable pageable) {
        return autoreService.findAll(pageable);
   }

//   @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//   public AutoreResponse update(@PathVariable Long id,
//                                @RequestPart("autore") AutoreRequest autoreRequest,
//                                @RequestPart(value = "avatar", required = false) MultipartFile avatar) {
//
//        autoreRequest.setAvatar(avatar);
//
//        return autoreService.update(id, autoreRequest);
//   }

   @PatchMapping(path = "/avatar/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public void uploadAvatar(@PathVariable Long id,@RequestPart MultipartFile file) {
       autoreService.uploadAvatar(id, file);
   }
}
