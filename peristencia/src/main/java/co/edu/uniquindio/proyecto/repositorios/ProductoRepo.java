package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {




    @Query("select p.vendedor.nombre from Producto p where p.codigo = :codigo")
    String obtenerNombreVendedor(Integer codigo);


    @Query("select s from Subasta s where s.productoSubasta.codigo = :codigo")
    Subasta  obtenerSubasta(Integer codigo);


    @Query("select p from Producto p where p.vendedor.codigo = :codigo")
    List<Producto> listarProductosUsuario(String codigo);

    @Query("select p from Subasta s join Producto p on (s.productoSubasta.codigo = p.codigo) where p.vendedor.codigo = :codigo")
    List<Producto> listarProductosSubastados(String codigo);






    @Query("select p.nombre,c.mensaje from Producto p left join p.comentarios c ")
    List<Object[]> listarProductosYComentarios();

    @Query("select distinct c.usuarioComentario from Producto p  join p.comentarios c where p.codigo =:codigo")
    List<Usuario> listarUsuariosComentario(Integer codigo);

    @Query("select p from Producto p where :fechaActual > p.fechaLimite")
    List<Producto> listarProductosVencidos(LocalDateTime fechaActual);

    @Query("select c, count(p) from Producto p join p.categorias c group by c")
    List<Object[]> obtenerTotalProductoCategoria();


    @Query("select count(p) from Producto p  join p.ciudadProducto c group by c")
    ArrayList<Number> obtenerTotalProductosCiudad();

    @Query("select p.ciudadProducto.nombre from Producto p  join p.ciudadProducto c group by c")
    ArrayList<String> obtenerNombreProductosCiudad();

    @Query("select count(p) from Producto p  join p.vendedor c group by c")
    ArrayList<Number> obtenerTotalProductosUsuario();

    @Query("select p.vendedor.nombre from Producto p  join p.vendedor c group by c")
    ArrayList<String> obtenerNombreProductosUsuario();

    @Query("select count(p) from Compra p join p.usuarioCompra c group by c")
    ArrayList<Number> obtenerTotalComprasUsuario();

    @Query("select p.usuarioCompra.nombre from Compra p  join p.usuarioCompra c group by c")
    ArrayList<String> obtenerNombreComprasUsuario();

    @Query("select count(p) from Usuario p join p.ciudadUsuario c group by c")
    ArrayList<Number> obtenerTotalUsuariosCiudad();

    @Query("select p.ciudadUsuario.nombre from Usuario p  join p.ciudadUsuario c group by c")
    ArrayList<String> obtenerNombreCiudadUsuario();

    @Query("select count(p) from Usuario p join p.productosFavoritos c group by c")
    ArrayList<Number> obtenerTotalProductosFavoritos();

    @Query("select p.ciudadUsuario.nombre from Usuario p  join p.productosFavoritos c group by c")
    ArrayList<String> obtenerUsuariosFavoritos();






    @Query("select max(s.valor)  from SubastaUsuario s where s.subastaSubastaUsuario.codigo = :codigo")
    Integer buscarMayorOferta(Integer codigo);

    @Query("select s from SubastaUsuario s where s.valor= :valor")
    SubastaUsuario buscarSubastaUsuario(Integer valor);



    @Query("select p from Producto p where p.vendedor.codigo = :codigo ")
    List<Producto> eliminarProductosUsuario(String codigo);




    @Query("select p.unidades from Producto p where p.codigo = :codigo")
    Integer obtenerUnidadesProducto(Integer codigo);



    @Query("select avg(c.calificacion)from Comentario c where c.productoComentario.codigo = :codigo")
    Integer obtenerPromedioComentario(Integer codigo);

    @Query("select p from Producto p where p.precio <= :precio ")
    List<Producto> listarPrecioMenor(Integer precio);


    @Query("select p from Producto p where p.precio >=  :precio")
    List<Producto> listarPrecioMayor(Integer precio);

    @Query("select p from Producto p where p.nombre like concat('%',:nombre,'%') ")
    List<Producto> buscarProductoPorNombre(String nombre);

    @Query("select p from Producto p where p.ciudadProducto.codigo = :codigo")
    List<Producto> listarPorCiudad(Integer codigo);

    @Query( " select c.productoComentario from Comentario  c where (avg(c.calificacion))  = :calificacion")
    List<Producto> listarPorCalificacion(Integer calificacion);

    @Query("select p from Producto p where :categoria member of p.categorias")
    List<Producto> listarPorCategoria(Categoria categoria);


}
