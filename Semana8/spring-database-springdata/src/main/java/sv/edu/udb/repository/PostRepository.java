// Rodrigo Escobar Semana8

package sv.edu.udb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.repository.domain.Post;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContainingIgnoreCase(String titlePart);

    List<Post> findByPostDateBetween(LocalDate start, LocalDate end);
}
