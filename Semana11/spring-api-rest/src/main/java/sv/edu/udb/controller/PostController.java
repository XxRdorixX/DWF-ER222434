package sv.edu.udb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.controller.request.PostRequest;
import sv.edu.udb.controller.response.PostResponse;
import sv.edu.udb.service.PostService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostResponse> findAllPost() {
        return postService.findAll();
    }

    @GetMapping(path = "{id}")
    public PostResponse findPostById(@PathVariable(name = "id") final Long id) {
        return postService.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public PostResponse savePost(@Valid @RequestBody final PostRequest request) {
        return postService.save(request);
    }

    @PutMapping(path = "{id}")
    public PostResponse updatePost(@PathVariable(name = "id") final Long id,
                                   @Valid @RequestBody final PostRequest request) {
        return postService.update(id, request);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletePost(@PathVariable(name = "id") final Long id) {
        postService.delete(id);
    }
}
