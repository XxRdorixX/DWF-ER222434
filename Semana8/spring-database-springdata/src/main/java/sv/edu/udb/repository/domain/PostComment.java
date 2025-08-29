// Rodrigo Escobar Semana8

package sv.edu.udb.repository.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String review;   // reseña

    @Column(nullable = false)
    private LocalDate commentDate; // fecha del comentario

    @Column(nullable = false)
    private String comment;  // contenido del comentario

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
