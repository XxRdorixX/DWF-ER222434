package sv.edu.udb.controller.response;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Getter @Setter @Builder(toBuilder = true)
@FieldNameConstants
public class PostCommentResponse {
    private Long id;
    private String review;
    private LocalDate commentDate;
    private Long postId;
}
