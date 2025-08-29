package sv.edu.udb.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sv.edu.udb.repository.domain.Post;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void init() {
        postRepository.save(Post.builder()
                .id(1L)
                .title("Spring Data con Query Methods")
                .postDate(LocalDate.of(2024, 8, 24))
                .comment("Comentario 1")
                .build());

        postRepository.save(Post.builder()
                .id(2L)
                .title("Hibernate y JPA Basics")
                .postDate(LocalDate.of(2024, 8, 20))
                .comment("Comentario 2")
                .build());
    }

    @AfterEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    void shouldFindByTitleContainingIgnoreCase() {
        List<Post> results = postRepository.findByTitleContainingIgnoreCase("spring");
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("Spring Data con Query Methods", results.get(0).getTitle());
    }
}
