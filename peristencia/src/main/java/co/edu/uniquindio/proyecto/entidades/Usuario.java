package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {



    @ElementCollection
    @Column(nullable = false)
    private List<String> Telefonos ;

    @Column(nullable = false,unique = true,length = 100)
    @Length(max = 100)
    private String username;


    @ManyToOne
    @ToString.Exclude
    private Ciudad ciudadUsuario;

    @OneToMany(mappedBy = "chatUsuario")
    @ToString.Exclude
    @JsonIgnore
    private List<Chat> chats;

    @OneToMany(mappedBy = "usuarioCompra")
    @ToString.Exclude
    @JsonIgnore
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuarioComentario")
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuarioSubastaUsuario")
    @ToString.Exclude
    @JsonIgnore
    private List<SubastaUsuario> subastaUsuarios;

    @ManyToMany
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(nullable = false)
    private List<Producto> productosFavoritos;

    @OneToMany(mappedBy = "vendedor")
    @ToString.Exclude
    @JsonIgnore
    private List<Producto> productoVendedores;

}
