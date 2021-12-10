package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleCompraRepo extends JpaRepository<DetalleCompra, Integer> {

    @Query("select d from DetalleCompra d where d.compraDetalleCompra.codigo = :codigo")
    List<DetalleCompra> ListaDetalleCompra(Integer codigo);

    @Query("select sum(d.precioProducto*d.unidades) from DetalleCompra d where d.compraDetalleCompra.codigo = :codigo")
    Integer calcularTotal(Integer codigo);

}
