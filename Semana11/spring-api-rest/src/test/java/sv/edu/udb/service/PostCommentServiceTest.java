package sv.edu.udb.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sv.edu.udb.controller.request.PostCommentRequest;
import sv.edu.udb.controller.response.PostCommentResponse;
import sv.edu.udb.repository.PostCommentRepository;
import sv.edu.udb.repository.PostRepository;
import sv.edu.udb.repository.domain.Post;
import sv.edu.udb.repository.domain.PostComment;
import sv.edu.udb.service.implementation.PostCommentServiceImpl;
import sv.edu.udb.service.mapper.PostCommentMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostCommentServiceTest {

    @InjectMocks
    private PostCommentServiceImpl service;

    @Mock
    private PostCommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostCommentMapper mapper;

    private Post post;
    private PostComment comment;

    @BeforeEach
    void init() {
        post = Post.builder().id(1L).title("t").postDate(LocalDate.now()).build();
        comment = PostComment.builder().id(11L).review("r").commentDate(LocalDate.now()).post(post).build();
    }

    @Test
    @DisplayName("save() should validate Post existence and map correctly")
    void shouldSave() {
        PostCommentRequest req = PostCommentRequest.builder()
                .postId(1L).review("hello").commentDate(LocalDate.now()).build();

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(mapper.toEntity(any(PostCommentRequest.class))).thenReturn(PostComment.builder().build());
        when(commentRepository.save(any(PostComment.class))).thenReturn(comment);
        when(mapper.toResponse(any(PostComment.class))).thenReturn(
                PostCommentResponse.builder().id(11L).review("r").postId(1L).build()
        );

        PostCommentResponse out = service.save(req);
        assertNotNull(out);
        assertEquals(11L, out.getId());
        verify(postRepository, times(1)).findById(1L);
        verify(commentRepository, times(1)).save(any(PostComment.class));
        verify(mapper, times(1)).toEntity(any(PostCommentRequest.class));
        verify(mapper, times(1)).toResponse(any(PostComment.class));
    }

    @Test
    @DisplayName("save() should throw when post not found")
    void shouldThrowWhenPostMissing() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());
        PostCommentRequest req = PostCommentRequest.builder().postId(9L).review("x").commentDate(LocalDate.now()).build();
        assertThrows(EntityNotFoundException.class, () -> service.save(req));
    }
}
