package sv.edu.udb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.controller.request.PostCommentRequest;
import sv.edu.udb.controller.response.PostCommentResponse;
import sv.edu.udb.service.PostCommentService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "comments")
public class PostCommentController {

    private final PostCommentService service;

    @GetMapping(params = "postId")
    public List<PostCommentResponse> findAllByPost(@RequestParam Long postId) {
        return service.findAllByPostId(postId);
    }

    @GetMapping("{id}")
    public PostCommentResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public PostCommentResponse save(@Valid @RequestBody PostCommentRequest request) {
        return service.save(request);
    }

    @PutMapping("{id}")
    public PostCommentResponse update(@PathVariable Long id, @Valid @RequestBody PostCommentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
