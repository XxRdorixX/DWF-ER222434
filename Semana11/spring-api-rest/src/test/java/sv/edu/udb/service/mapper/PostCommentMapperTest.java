package sv.edu.udb.service.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import sv.edu.udb.controller.request.PostCommentRequest;
import sv.edu.udb.controller.response.PostCommentResponse;
import sv.edu.udb.repository.domain.PostComment;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PostCommentMapperTest {

    private final PostCommentMapper mapper = Mappers.getMapper(PostCommentMapper.class);

    @Test
    void shouldMapRequestToEntity() {
        PostCommentRequest req = PostCommentRequest.builder()
                .postId(5L).review("ok").commentDate(LocalDate.of(2024,1,1)).build();
        PostComment entity = mapper.toEntity(req);
        assertNotNull(entity);
        assertEquals("ok", entity.getReview());
        assertEquals(LocalDate.of(2024,1,1), entity.getCommentDate());
        assertNotNull(entity.getPost());
        assertEquals(5L, entity.getPost().getId());
    }

    @Test
    void shouldMapEntityToResponse() {
        PostComment entity = PostComment.builder()
                .id(9L).review("r").commentDate(LocalDate.of(2024,1,2))
                .post(sv.edu.udb.repository.domain.Post.builder().id(3L).build())
                .build();
        PostCommentResponse res = mapper.toResponse(entity);
        assertEquals(9L, res.getId());
        assertEquals("r", res.getReview());
        assertEquals(3L, res.getPostId());
    }
}
