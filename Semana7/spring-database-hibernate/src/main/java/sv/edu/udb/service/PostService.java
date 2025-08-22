package sv.edu.udb.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.repository.PostRepository;
import sv.edu.udb.repository.PostCommentRepository;
import sv.edu.udb.repository.domain.Post;
import sv.edu.udb.repository.domain.PostComment;

import java.util.List;

@Service // Marca esta clase como un servicio gestionado por Spring
public class PostService {

    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;

    // Inyección de dependencias vía constructor
    public PostService(PostRepository postRepository, PostCommentRepository postCommentRepository) {
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
    }

    // --- Métodos para Post ---
    @Transactional(readOnly = true) // Solo lectura, más eficiente
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional // Lectura/escritura, se usa en operaciones que modifican datos
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // --- Métodos para PostComment ---
    @Transactional(readOnly = true)
    public List<PostComment> getAllComments() {
        return postCommentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PostComment getCommentById(Long id) {
        return postCommentRepository.findById(id);
    }

    @Transactional
    public void saveComment(PostComment comment) {
        postCommentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        postCommentRepository.deleteById(id);
    }
}
