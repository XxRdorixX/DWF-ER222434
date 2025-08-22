package sv.edu.udb.service;

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

@SpringBootTest // Levanta el contexto de Spring para inyectar beans reales
class PostServiceTest {

    @Autowired
    private PostService postService;

    @BeforeEach
    @Transactional
    void initData() {
        // Se asegura que exista un Post con id=1
        Post post = postService.getPostById(1L);
        if (post == null) {
            post = Post.builder()
                    .id(1L)
                    .title("Post Service Test")
                    .postDate(LocalDate.now())
                    .build();
            postService.savePost(post);
        }

        // Se asegura que exista un Comment con id=1
        PostComment comment = postService.getCommentById(1L);
        if (comment == null) {
            comment = PostComment.builder()
                    .id(1L)
                    .review("Service layer comment")
                    .post(post)
                    .createdOn(LocalDate.now())
                    .build();
            postService.saveComment(comment);
        }
    }

    @AfterEach
    @Transactional
    void cleanData() {
        // Limpieza después de cada prueba
        postService.deleteComment(1L);
        postService.deletePost(1L);
    }

    @Test
    @Transactional
    void shouldGetPosts_When_ServiceCalled() {
        List<Post> posts = postService.getAllPosts();
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
    }

    @Test
    @Transactional
    void shouldGetPostById_When_Exists() {
        Post post = postService.getPostById(1L);
        assertNotNull(post);
        assertEquals(1L, post.getId());
    }

    @Test
    @Transactional
    void shouldGetComments_When_ServiceCalled() {
        List<PostComment> comments = postService.getAllComments();
        assertNotNull(comments);
        assertFalse(comments.isEmpty());
    }

    @Test
    @Transactional
    void shouldGetCommentById_When_Exists() {
        PostComment comment = postService.getCommentById(1L);
        assertNotNull(comment);
        assertEquals(1L, comment.getId());
        assertEquals("Service layer comment", comment.getReview());
    }
}
