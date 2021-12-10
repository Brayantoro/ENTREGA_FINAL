package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {


    @Query("select  count(distinct d.productoDetalleCompra) from Compra c join c.detalleCompras d where c.usuarioCompra.codigo = :codigo")
    Long obtenerListaProductosComprados(String codigo);


}
