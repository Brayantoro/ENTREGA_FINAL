package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nombre;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false, length = 250)
    @NotBlank
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private Integer precio;

    @Column(nullable = false)
    @Future
    private LocalDateTime fechaLimite;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer descuento;

    @ElementCollection
    @Column(nullable = false)
    private List<String> imagenes ;


    @ElementCollection
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private List<Categoria> categorias;



    @OneToMany(mappedBy = "chatProducto",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Chat> chats;

    @ManyToMany(mappedBy = "productosFavoritos")
    @ToString.Exclude
    @JsonIgnore
    private List<Usuario> favoritos;

    @OneToMany(mappedBy = "productoDetalleCompra",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> detalleCompras;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Ciudad ciudadProducto;

    @OneToMany(mappedBy = "productoComentario",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "productoSubasta",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Subasta> subastas;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Usuario vendedor;


    public String getImagenPrincipal(){
        if(imagenes!= null && !imagenes.isEmpty()){
            return imagenes.get(0);
        }
        return "no_disponible.jpg";
    }



}
