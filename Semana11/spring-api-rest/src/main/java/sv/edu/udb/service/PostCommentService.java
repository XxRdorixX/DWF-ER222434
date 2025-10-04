package sv.edu.udb.service;

import sv.edu.udb.controller.request.PostCommentRequest;
import sv.edu.udb.controller.response.PostCommentResponse;

import java.util.List;

public interface PostCommentService {
    List<PostCommentResponse> findAllByPostId(Long postId);
    PostCommentResponse findById(Long id);
    PostCommentResponse save(PostCommentRequest request);
    PostCommentResponse update(Long id, PostCommentRequest request);
    void delete(Long id);
}
