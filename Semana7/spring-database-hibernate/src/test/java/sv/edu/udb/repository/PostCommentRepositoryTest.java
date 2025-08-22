// Rodrigo Escobar Semana7

package sv.edu.udb.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    private PostRepository postRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;

    @BeforeEach
    @Transactional
    void initData() {
        // Aseguramos que exista un Post con id=1 para relacionar comentarios
        final Long postId = 1L;
        final String postTitle = "Post for comments";
        final LocalDate postDate = LocalDate.of(2024, 1, 15);

        Post post = postRepository.findById(postId);
        if (post == null) {
            post = Post.builder()
                    .id(postId)
                    .title(postTitle)
                    .postDate(postDate)
                    .build();
            postRepository.save(post);
        }

        // Insertamos un comentario base con id=1
        final Long commentId = 1L;
        final String commentText = "First comment";

        PostComment comment = postCommentRepository.findById(commentId);
        if (comment == null) {
            comment = PostComment.builder()
                    .id(commentId)
                    .review(commentText)        // <-- corregido
                    .post(post)
                    .createdOn(LocalDate.now()) // <-- necesario
                    .build();
            postCommentRepository.save(comment);
        }
    }

    @AfterEach
    @Transactional
    void cleanData() {
        // Borramos lo que insertamos en initData
        postCommentRepository.deleteById(1L);
        postRepository.deleteById(1L);
    }

    @Test
    @Transactional
    void shouldHasOneComment_When_FindAll() {
        List<PostComment> list = postCommentRepository.findAll();
        assertNotNull(list);
        assertTrue(list.size() >= 1);
    }

    @Test
    @Transactional
    void shouldGetComment_When_IdExist() {
        final Long expectedId = 1L;
        final PostComment actual = postCommentRepository.findById(expectedId);
        assertNotNull(actual);
        assertEquals(expectedId, actual.getId());
        assertEquals("First comment", actual.getReview()); // usar review
        assertNotNull(actual.getPost());
        assertEquals(1L, actual.getPost().getId());
    }

    @Test
    @Transactional
    void shouldSaveComment_When_CommentIsNew() {
        final Long newId = 2L;
        final String newText = "Second comment";

        final Post post = postRepository.findById(1L);
        assertNotNull(post);

        PostComment newComment = PostComment.builder()
                .id(newId)
                .review(newText)              // <-- corregido
                .post(post)
                .createdOn(LocalDate.now())   // <-- necesario
                .build();

        postCommentRepository.save(newComment);

        PostComment saved = postCommentRepository.findById(newId);
        assertNotNull(saved);
        assertEquals(newId, saved.getId());
        assertEquals(newText, saved.getReview());
        assertEquals(post.getId(), saved.getPost().getId());

        // Limpieza puntual
        postCommentRepository.delete(newComment);
    }

    @Test
    @Transactional
    void shouldDeleteComment_When_CommentExist() {
        final Long tempId = 3L;

        final Post post = postRepository.findById(1L);
        assertNotNull(post);

        PostComment temp = PostComment.builder()
                .id(tempId)
                .review("Temp to delete")    // <-- corregido
                .post(post)
                .createdOn(LocalDate.now())  // <-- necesario
                .build();

        postCommentRepository.save(temp);

        PostComment exists = postCommentRepository.findById(tempId);
        assertNotNull(exists);

        postCommentRepository.delete(temp);

        PostComment deleted = postCommentRepository.findById(tempId);
        assertNull(deleted);
    }
}
