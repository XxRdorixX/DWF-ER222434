package sv.edu.udb.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sv.edu.udb.controller.request.PostCommentRequest;
import sv.edu.udb.controller.response.PostCommentResponse;
import sv.edu.udb.repository.domain.Post;
import sv.edu.udb.repository.domain.PostComment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-03T19:26:16-0600",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Amazon.com Inc.)"
)
@Component
public class PostCommentMapperImpl implements PostCommentMapper {

    @Override
    public PostComment toEntity(PostCommentRequest request) {
        if ( request == null ) {
            return null;
        }

        PostComment.PostCommentBuilder postComment = PostComment.builder();

        postComment.post( mapPostId( request.getPostId() ) );
        postComment.review( request.getReview() );
        postComment.commentDate( request.getCommentDate() );

        return postComment.build();
    }

    @Override
    public PostCommentResponse toResponse(PostComment entity) {
        if ( entity == null ) {
            return null;
        }

        PostCommentResponse.PostCommentResponseBuilder postCommentResponse = PostCommentResponse.builder();

        postCommentResponse.postId( entityPostId( entity ) );
        postCommentResponse.id( entity.getId() );
        postCommentResponse.review( entity.getReview() );
        postCommentResponse.commentDate( entity.getCommentDate() );

        return postCommentResponse.build();
    }

    @Override
    public List<PostCommentResponse> toResponseList(List<PostComment> list) {
        if ( list == null ) {
            return null;
        }

        List<PostCommentResponse> list1 = new ArrayList<PostCommentResponse>( list.size() );
        for ( PostComment postComment : list ) {
            list1.add( toResponse( postComment ) );
        }

        return list1;
    }

    private Long entityPostId(PostComment postComment) {
        Post post = postComment.getPost();
        if ( post == null ) {
            return null;
        }
        return post.getId();
    }
}
