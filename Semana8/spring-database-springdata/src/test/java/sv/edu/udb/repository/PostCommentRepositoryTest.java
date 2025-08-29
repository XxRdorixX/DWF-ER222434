// Rodrigo Escobar Semana8

package sv.edu.udb.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sv.edu.udb.repository.domain.Post;
import sv.edu.udb.repository.domain.PostComment;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostCommentRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;

    private Post p1;
    private Post p2;

    @BeforeEach
    void init() {
        p1 = postRepository.save(Post.builder()
                .id(1L)
                .title("Post de prueba")
                .postDate(LocalDate.of(2024, 8, 24))
                .comment("Comentario en Post 1")
                .build());

        p2 = postRepository.save(Post.builder()
                .id(2L)
                .title("Otro Post")
                .postDate(LocalDate.of(2024, 8, 25))
                .comment("Comentario en Post 2")
                .build());

        postCommentRepository.save(PostComment.builder()
                .id(10L)
                .comment("Muy buena guía de Spring Data")
                .review("Review 1")
                .commentDate(LocalDate.now())
                .post(p1)
                .build());

        postCommentRepository.save(PostComment.builder()
                .id(11L)
                .comment("Tomar en cuenta los Query Methods")
                .review("Review 2")
                .commentDate(LocalDate.now())
                .post(p1)
                .build());

        postCommentRepository.save(PostComment.builder()
                .id(12L)
                .comment("Comentario en el segundo post")
                .review("Review 3")
                .commentDate(LocalDate.now())
                .post(p2)
                .build());
    }

    @AfterEach
    void clean() {
        postCommentRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    void shouldFindCommentsByPostId() {
        List<PostComment> results = postCommentRepository.findByPost_Id(p1.getId());
        assertNotNull(results);
        assertEquals(2, results.size());
        assertTrue(results.stream().allMatch(pc -> pc.getPost().getId().equals(p1.getId())));
    }

    @Test
    void shouldFindByCommentContainingIgnoreCase() {
        List<PostComment> results = postCommentRepository.findByCommentContainingIgnoreCase("query methods");
        assertNotNull(results);
        assertEquals(1, results.size());
        assertTrue(results.get(0).getComment().toLowerCase().contains("query methods"));
    }
}
