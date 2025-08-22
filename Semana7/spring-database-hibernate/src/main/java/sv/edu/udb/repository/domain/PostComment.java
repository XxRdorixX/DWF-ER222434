// Rodrigo Escobar Semana7

package sv.edu.udb.repository.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {
    @Id
    private Long id;

    @Column(nullable = false)
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    @Column(name = "created_on", nullable = false)
    private LocalDate createdOn;
}
