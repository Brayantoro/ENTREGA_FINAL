package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    private final UsuarioRepo usuarioRepo;

    @Autowired
    private AdministradorRepo administradorRepo;

    @Autowired
    private  ProductoRepo productoRepo;

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;


    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());
        if(buscado.isPresent()){
            throw new Exception("El codigo del usuario ya existe");
        }

        buscado = buscarPorEmail(u.getEmail());
        if(buscado.isPresent()){
            throw new Exception("El email del usuario ya existe");
        }
        buscado = usuarioRepo.findByUsername(u.getUsername());
        if(buscado.isPresent()){
            throw new Exception("El username del usuario ya existe");
        }
        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());
        if(buscado.isEmpty()){
            throw new Exception("El  usuario No existe");
        }


        return usuarioRepo.save(u);

    }



    private Optional<Usuario> buscarPorEmail(String email){
        return usuarioRepo.findByEmail(email);
    }

    @Override
    public void eliminarUsuario(String codigo) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if(buscado.isEmpty()){
            throw new Exception("El codigo del usuario no existe");
        }

        List<Producto> productos = productoRepo.eliminarProductosUsuario(codigo);

        productoRepo.deleteAll(productos);

        usuarioRepo.deleteById(codigo);

    }


    @Override
    public void agragarFavorito(Usuario u) {
        usuarioRepo.save(u);
    }


    @Override
    public Usuario obtenerUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if(buscado.isEmpty()){
            throw new Exception("El codigo del usuario No existe");
        }
        return buscado.get();
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findByEmail(email);
        if(buscado.isEmpty()){
            throw new Exception("El correr  No existe");
        }
        return buscado.get();
    }

    @Override
    public Administrador obtenerAdminPorEmail(String email) throws Exception {
        Optional<Administrador> buscado = administradorRepo.findByEmail(email);
        if(buscado.isEmpty()){
            throw new Exception("El correr  No existe");
        }
        return buscado.get();
    }

    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {
        return usuarioRepo.findByEmailAndPassword(email,password).orElseThrow(()-> new Exception("los datos de autenticacion son incorrectos"));


    }

    @Override
    public Administrador iniciarSesionAdmin(String email, String password) throws Exception {
        return administradorRepo.findByEmailAndPassword(email,password).orElseThrow(()-> new Exception("los datos de autenticacion son incorrectos"));
    }

    @Override
    public List<Compra> listarComprasUsuario(String codigoUsuario) throws Exception {
        return usuarioRepo.listarComprasPorUsuario(codigoUsuario);
    }

    @Override
    public List<DetalleCompra> listaDetalleCompra(Integer codigoCompra) throws Exception {
        return detalleCompraRepo.ListaDetalleCompra(codigoCompra);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public Integer calcularTotalDetalle(Integer codigoCompra) throws Exception {
        return detalleCompraRepo.calcularTotal(codigoCompra);
    }

    @Override
    public List<Producto> obtenerProductosFavoritos(String codigo) throws Exception {
        return usuarioRepo.obtenerProductosFavoritos(codigo);
    }

    @Override
    public List<Producto> listarProductosFavoritos(String codigo) throws Exception {
        return usuarioRepo.findAllByProductosFavoritos(codigo);
    }

}
