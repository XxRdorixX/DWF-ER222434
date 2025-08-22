// Rodrigo Escobar Semana7

package sv.edu.udb.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.repository.domain.PostComment;

import java.util.List;
import java.util.Objects;

@Repository
public class PostCommentRepository {

    private final SessionFactory sessionFactory;

    public PostCommentRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
    }

    public List<PostComment> findAll() {
        final String QUERY = "from PostComment pc";
        return sessionFactory
                .getCurrentSession()
                .createQuery(QUERY, PostComment.class)
                .getResultList();
    }

    public PostComment findById(final Long id) {
        return sessionFactory
                .getCurrentSession()
                .find(PostComment.class, id);
    }

    @Transactional
    public void save(final PostComment postComment) {
        sessionFactory.getCurrentSession().persist(postComment);
    }

    public void delete(final PostComment postComment) {
        sessionFactory.getCurrentSession().remove(postComment);
    }

    public void deleteById(final Long id) {
        final String QUERY = "delete from PostComment pc where pc.id=:id";
        sessionFactory.getCurrentSession()
                .createMutationQuery(QUERY)
                .setParameter("id", id)
                .executeUpdate();
    }
}
