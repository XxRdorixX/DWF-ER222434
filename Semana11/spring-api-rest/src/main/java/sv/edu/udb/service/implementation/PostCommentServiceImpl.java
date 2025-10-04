package sv.edu.udb.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.controller.request.PostCommentRequest;
import sv.edu.udb.controller.response.PostCommentResponse;
import sv.edu.udb.repository.PostCommentRepository;
import sv.edu.udb.repository.PostRepository;
import sv.edu.udb.repository.domain.Post;
import sv.edu.udb.repository.domain.PostComment;
import sv.edu.udb.service.PostCommentService;
import sv.edu.udb.service.mapper.PostCommentMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {

    @NonNull private final PostCommentRepository commentRepository;
    @NonNull private final PostRepository postRepository;
    @NonNull private final PostCommentMapper mapper;

    @Override
    public List<PostCommentResponse> findAllByPostId(Long postId) {
        return mapper.toResponseList(commentRepository.findByPostId(postId));
    }

    @Override
    public PostCommentResponse findById(Long id) {
        return mapper.toResponse(commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found id " + id)));
    }

    @Override
    public PostCommentResponse save(PostCommentRequest request) {
        // ensure post exists
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found id " + request.getPostId()));
        PostComment entity = mapper.toEntity(request);
        entity.setPost(post);
        return mapper.toResponse(commentRepository.save(entity));
    }

    @Override
    public PostCommentResponse update(Long id, PostCommentRequest request) {
        PostComment entity = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found id " + id));
        entity.setReview(request.getReview());
        entity.setCommentDate(request.getCommentDate());
        if (request.getPostId() != null) {
            Post post = postRepository.findById(request.getPostId())
                    .orElseThrow(() -> new EntityNotFoundException("Post not found id " + request.getPostId()));
            entity.setPost(post);
        }
        return mapper.toResponse(commentRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
