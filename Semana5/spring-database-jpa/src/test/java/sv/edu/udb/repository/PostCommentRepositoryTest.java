//Rodrigo Ernesto Escobar Rivas ER222434

package sv.edu.udb.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.repository.domain.Post;
import sv.edu.udb.repository.domain.PostComment;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostCommentRepositoryTest {

    @Autowired
    private PostCommentRepository postCommentRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    void shouldSaveComment_When_Valid() {
        Post post = Post.builder()
                .id(99L)
                .title("Post de Prueba")
                .postDate(LocalDate.now())
                .build();
        postRepository.save(post);

        PostComment comment = PostComment.builder()
                .id(1L)
                .review("Buen post")
                .commentDate(LocalDate.now())
                .post(post)
                .build();

        postCommentRepository.save(comment);

        PostComment saved = postCommentRepository.findById(1L);
        assertNotNull(saved);
        assertEquals("Buen post", saved.getReview());

        postCommentRepository.delete(saved);
        postRepository.delete(post);
    }

    @Test
    @Transactional
    void shouldDeleteComment_When_Exists() {
        Post post = Post.builder()
                .id(100L)
                .title("Post para eliminar")
                .postDate(LocalDate.now())
                .build();
        postRepository.save(post);

        PostComment comment = PostComment.builder()
                .id(2L)
                .review("Comentario a eliminar")
                .commentDate(LocalDate.now())
                .post(post)
                .build();

        postCommentRepository.save(comment);
        postCommentRepository.delete(comment);

        PostComment deleted = postCommentRepository.findById(2L);
        assertNull(deleted);
        postRepository.delete(post);
    }

    @Test
    void shouldReturnEmptyList_When_NoComments() {
        List<PostComment> comments = postCommentRepository.findAll();
        assertNotNull(comments);
    }
}
