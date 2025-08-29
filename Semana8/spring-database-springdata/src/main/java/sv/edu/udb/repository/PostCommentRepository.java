// Rodrigo Escobar Semana8

package sv.edu.udb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.repository.domain.PostComment;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    List<PostComment> findByPost_Id(Long postId);

    List<PostComment> findByCommentContainingIgnoreCase(String text);
}
