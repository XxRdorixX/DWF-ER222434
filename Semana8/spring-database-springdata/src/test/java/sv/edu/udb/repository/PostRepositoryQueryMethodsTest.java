// Rodrigo Escobar Semana8

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
class PostRepositoryQueryMethodsTest {

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void init() {
        postRepository.save(Post.builder()
                .id(1L)
                .title("Spring Data con Query Methods")
                .postDate(LocalDate.of(2024, 8, 24))
                .comment("Primer comentario")
                .build());

        postRepository.save(Post.builder()
                .id(2L)
                .title("Hibernate y JPA Basics")
                .postDate(LocalDate.of(2024, 8, 20))
                .comment("Segundo comentario")
                .build());

        postRepository.save(Post.builder()
                .id(3L)
                .title("Probando Spring Boot")
                .postDate(LocalDate.of(2024, 8, 27))
                .comment("Tercer comentario")
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
        assertEquals(2, results.size());
    }

    @Test
    void shouldFindByPostDateBetween() {
        LocalDate start = LocalDate.of(2024, 8, 21);
        LocalDate end = LocalDate.of(2024, 8, 28);

        List<Post> results = postRepository.findByPostDateBetween(start, end);
        assertNotNull(results);
        assertEquals(2, results.size());
    }
}
