package it.epicode.blog_api.autori;

import com.cloudinary.Cloudinary;
import it.epicode.blog_api.cloudinary.CloudinaryService;
import it.epicode.blog_api.common.CommonResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired

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

//    public AutoreResponse update(Long id, AutoreRequest autoreRequest) {
//        Autore autore = autoreRepository
//                .findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Autore not found"));
//
//        BeanUtils.copyProperties(autoreRequest, autore, "avatar");
//
//        MultipartFile avatarFile = autoreRequest.getAvatar();
//        if (avatarFile != null && !avatarFile.isEmpty()) {
//            try {
//                Map uploadResult = cloudinary.uploader().upload(
//                        avatarFile.getBytes(),
//                        Cloudinary.asMap("folder", "autori", "public_id", "autore_" + id)
//                );
//                String avatarUrl = uploadResult.get("secure_url").toString();
//                autore.setAvatar(avatarUrl);
//            } catch (IOException e) {
//                throw new RuntimeException("Errore durante l'upload dell'immagine", e);
//            }
//        }
//
//        autoreRepository.save(autore);
//
//        return new AutoreResponse(autore.getId(), autore.getNome() + " " + autore.getCognome());
//    }

    public void uploadAvatar(Long id, MultipartFile file) {
        Autore autore = autoreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autore not found"));

        String url = cloudinaryService.upload(file);

        autore.setAvatar(url);
        autoreRepository.save(autore);
    }
}
