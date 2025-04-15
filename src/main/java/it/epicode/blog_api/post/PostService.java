package it.epicode.blog_api.post;

import org.springframework.beans.factory.annotation.Autowired;
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
}
