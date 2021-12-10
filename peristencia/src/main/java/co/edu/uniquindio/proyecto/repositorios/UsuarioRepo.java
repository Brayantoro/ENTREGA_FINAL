package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String> {


    List<Usuario> findAllByNombreContains(String nombre);

    List<Producto> findAllByProductosFavoritos(String codigo);

    Page<Usuario> findAll(Pageable paginador);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsername(String username);



    Optional<Usuario> findByEmailAndPassword(String email, String password);


    @Query("select p from Usuario u, IN (u.productosFavoritos) p where u.codigo = :codigo")
    List<Producto> obtenerProductosFavoritos(String codigo);



    @Query("select u.email,u.nombre,p from Usuario u left join u.productoVendedores p")
    List<Object[]> listarUsuariosYProductos();

    @Query("select c from Compra c where c.usuarioCompra.codigo = :codigo")
    List<Compra> listarComprasPorUsuario(String codigo);






}
