package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer> {


    @Query("select u from Ciudad c join c.usuarios u where c.nombre =:nombre")
    List<Usuario> obtenerListaUsuario(String nombre);

    @Query("select u from Ciudad c join c.usuarios u where c.nombre = :nombre ")
    List<Usuario> listarUsuarios(String nombre);



}
