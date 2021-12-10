package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.Query;


import java.util.ArrayList;
import java.util.List;

public interface ProductoServicio {


    ArrayList<Number> obtenerTotalProductosFavoritos();


    ArrayList<String> obtenerUsuariosFavoritos();


    ArrayList<Number> obtenerTotalUsuariosCiudad();


    ArrayList<String> obtenerNombreCiudadUsuario();

    ArrayList<Number> obtenerTotalProductoCiudad();

    ArrayList<String> obtenerNombreProductosCiudad();


    ArrayList<Number> obtenerTotalProductosUsuario();


    ArrayList<String> obtenerNombreProductosUsuario();


    ArrayList<Number> obtenerTotalComprasUsuario();


    ArrayList<String> obtenerNombreComprasUsuario();

    Producto publicarProducto(Producto p) throws  Exception;

    void actualizarProducto(Producto p) throws  Exception;

    void eliminarProductoSubasta(Integer codigo) throws  Exception;

    void eliminarProducto(Integer codigo) throws  Exception;

    Producto obtenerProducto(Integer codigo) throws Exception;

    List<Categoria> ListarCategorias();

    List<Producto> listarPorCategoria(Categoria categoria);

    List<Producto> ListarPorCiudad(Integer codigo);

    List<Producto> ListarPorPrecioMenor(Integer precio);

    List<Producto> ListarPorPrecioMayor(Integer precio);

    List<Producto> ListarPorCalificacion(Integer calificacion);

    List<Producto> listarSubastasPorUsuario(String codigo);

    List<Producto> listarProductosSubastados();

    Integer buscarMayorOferta(Integer codigo);

    SubastaUsuario buscarSubastaUsuario(Integer valor);


    Subasta obtenerSubasata(Integer subasata) throws Exception;




    void subastarProducto(Subasta subasta) throws Exception;



    Categoria obtenerCategoria(String categoria) throws Exception;

    List<Producto> listarProductos();


    Integer obtenerUnidadesProducto(Integer codigo) throws Exception;

    Integer obtenerPromedioComentario(Integer codigo) throws Exception;


    void comentarProducto(Comentario comentario) throws Exception;

    void guardarProductoFavoritos(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoFavoritos(Producto producto, Usuario usuario) throws Exception;

    void comprarProducto(Compra compra) throws Exception;

    List<Producto> buscarProducto(String nombreProducto, String[] filtros);

    List<Producto> listarProductoUsuario(String codigoUsuario);

    List<Subasta> listarSubastas();

    Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos,String medioPago) throws Exception;


    void registrarSubastaUsuario(SubastaUsuario subastaUsuario) throws Exception;

}
