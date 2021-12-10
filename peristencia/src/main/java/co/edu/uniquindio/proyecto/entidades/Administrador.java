package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Administrador extends Persona implements Serializable {


    //@Max(9999)
    //@Positive
    //@Column(nullable = false)
    //private Integer anioNacimiento;

    //@ManyToMany(mappedBy = "autores")
    //private List<Libro> libros;


    public Administrador(String codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }
}
