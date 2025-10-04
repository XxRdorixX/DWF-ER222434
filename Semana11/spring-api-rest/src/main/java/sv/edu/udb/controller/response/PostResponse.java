package sv.edu.udb.controller.response;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Getter @Setter @Builder(toBuilder = true)
@FieldNameConstants
public class PostResponse {
    private String title;
    private LocalDate postDate;
}
