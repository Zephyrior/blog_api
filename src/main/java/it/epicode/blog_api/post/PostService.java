package it.epicode.blog_api.post;

import jakarta.persistence.EntityNotFoundException;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<PostSelectResponse> findAllSelect() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(p -> new PostSelectResponse(p.getId(), p.getTitolo(), p.getCategoria(), p.getContenuto(), p.getAutore().getNome() + " " + p.getAutore().getCognome()))
                .toList();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Post not found"));

        postRepository.delete(post);
    }

    public Page<Post> findAll(Pageable pageable) {

        return postRepository.findAll(pageable);
    }
}
