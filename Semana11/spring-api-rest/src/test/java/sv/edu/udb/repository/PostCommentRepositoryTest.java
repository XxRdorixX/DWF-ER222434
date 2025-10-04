package sv.edu.udb.repository;

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

    @Test
    void shouldSaveAndFindByPostId() {
        Post post = Post.builder().title("Title").postDate(LocalDate.now()).build();
        post = postRepository.saveAndFlush(post);

        PostComment c1 = PostComment.builder()
                .review("Nice")
                .commentDate(LocalDate.now())
                .post(post)
                .build();
        postCommentRepository.saveAndFlush(c1);

        List<PostComment> list = postCommentRepository.findByPostId(post.getId());
        assertEquals(1, list.size());
        assertEquals("Nice", list.get(0).getReview());
        assertEquals(post.getId(), list.get(0).getPost().getId());
    }
}
