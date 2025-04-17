package it.epicode.blog_api.post;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/select")
    public List<PostSelectResponse> findAllSelect() {
        return postService.findAllSelect();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable Long id) {

        return postService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Long id) {

        postService.deleteById(id);
    }


    @GetMapping
    public Page<Post> findAll(@ParameterObject Pageable pageable) {
        return postService.findAll(pageable);
    }
}
