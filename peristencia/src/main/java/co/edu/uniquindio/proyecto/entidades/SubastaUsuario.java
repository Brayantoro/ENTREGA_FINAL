package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class SubastaUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    private Integer valor;

    @Column(nullable = false)
    @Future
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuarioSubastaUsuario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subasta subastaSubastaUsuario;
}
