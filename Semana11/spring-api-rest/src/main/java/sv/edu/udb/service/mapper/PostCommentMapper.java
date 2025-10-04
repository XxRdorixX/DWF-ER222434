package sv.edu.udb.service.mapper;

import org.mapstruct.*;
import sv.edu.udb.controller.request.PostCommentRequest;
import sv.edu.udb.controller.response.PostCommentResponse;
import sv.edu.udb.repository.domain.Post;
import sv.edu.udb.repository.domain.PostComment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostCommentMapper {

    @Mapping(target = "post", source = "postId", qualifiedByName = "mapPostId")
    PostComment toEntity(final PostCommentRequest request);

    @Mapping(target = "postId", source = "post.id")
    PostCommentResponse toResponse(final PostComment entity);

    List<PostCommentResponse> toResponseList(final List<PostComment> list);

    @Named("mapPostId")
    default Post mapPostId(Long id) {
        if (id == null) return null;
        Post p = new Post();
        p.setId(id);
        return p;
    }
}
