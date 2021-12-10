package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario u) throws Exception;

    Usuario actualizarUsuario(Usuario u) throws Exception;

    void eliminarUsuario(String codigo) throws Exception;

    void agragarFavorito(Usuario u);


    Usuario obtenerUsuario(String codigo) throws Exception;

    Usuario obtenerUsuarioPorEmail(String email) throws Exception;

    Administrador obtenerAdminPorEmail(String email) throws Exception;

    Usuario iniciarSesion(String email,String password) throws Exception;

    Administrador iniciarSesionAdmin(String email,String password) throws Exception;

    List<Compra> listarComprasUsuario(String codigoUsuario) throws Exception;

    List<DetalleCompra> listaDetalleCompra(Integer codigoCompra) throws Exception;

    List<Usuario> listarUsuarios();

    Integer calcularTotalDetalle(Integer codigoCompra)throws Exception;

    List<Producto> obtenerProductosFavoritos(String codigo) throws Exception;

    List<Producto> listarProductosFavoritos(String codigo) throws Exception ;


}
