package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;

    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private CompraRepo compraRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Autowired
    private SubastaUsuarioRepo subastaUsuarioRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo) {
        this.productoRepo = productoRepo;
    }


    @Override
    public ArrayList<Number> obtenerTotalProductosFavoritos() {
        return productoRepo.obtenerTotalProductosFavoritos();
    }

    @Override
    public ArrayList<String> obtenerUsuariosFavoritos() {
        return productoRepo.obtenerUsuariosFavoritos();
    }

    @Override
    public ArrayList<Number> obtenerTotalUsuariosCiudad() {
        return productoRepo.obtenerTotalUsuariosCiudad();
    }

    @Override
    public ArrayList<String> obtenerNombreCiudadUsuario() {
        return productoRepo.obtenerNombreCiudadUsuario();
    }

    @Override
    public ArrayList<Number> obtenerTotalProductoCiudad() {
        return productoRepo.obtenerTotalProductosCiudad();
    }

    @Override
    public ArrayList<String> obtenerNombreProductosCiudad() {
        return productoRepo.obtenerNombreProductosCiudad();
    }

    @Override
    public ArrayList<Number> obtenerTotalProductosUsuario() {
        return productoRepo.obtenerTotalProductosUsuario();
    }

    @Override
    public ArrayList<String> obtenerNombreProductosUsuario() {
        return productoRepo.obtenerNombreProductosUsuario();
    }

    @Override
    public ArrayList<Number> obtenerTotalComprasUsuario() {
        return productoRepo.obtenerTotalComprasUsuario();
    }

    @Override
    public ArrayList<String> obtenerNombreComprasUsuario() {
        return productoRepo.obtenerNombreComprasUsuario();
    }


    @Override
    public Producto publicarProducto(Producto p) throws Exception {

        try {
            return productoRepo.save(p);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void actualizarProducto(Producto p) throws Exception {

    }

    @Override
    public void eliminarProductoSubasta(Integer codigo) throws Exception {
        Subasta subasta= productoRepo.obtenerSubasta(codigo);
        subastaRepo.delete(subasta);
    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);

        if (producto.isEmpty()) {
            throw new Exception("el producto que desea eliminar no existe");
        }
        productoRepo.deleteById(codigo);
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {
        return productoRepo.findById(codigo).orElseThrow(() -> new Exception("El codio del producto no es valido"));
    }

    @Override
    public List<Categoria> ListarCategorias() {
        return Arrays.asList(Categoria.values());
    }

    @Override
    public List<Producto> listarPorCategoria(Categoria categoria) {
        return productoRepo.listarPorCategoria(categoria);
    }

    @Override
    public List<Producto> ListarPorCiudad(Integer codigo) {

        return productoRepo.listarPorCiudad(codigo);
    }

    @Override
    public List<Producto> ListarPorPrecioMenor(Integer precio) {
        return productoRepo.listarPrecioMenor(precio);
    }

    @Override
    public List<Producto> ListarPorPrecioMayor(Integer precio) {
        return productoRepo.listarPrecioMayor(precio);
    }

    @Override
    public List<Producto> ListarPorCalificacion(Integer calificacion) {
        return productoRepo.listarPorCalificacion(calificacion);
    }

    @Override
    public List<Producto> listarSubastasPorUsuario(String codigo) {
        return productoRepo.listarProductosSubastados(codigo);
    }

    @Override
    public List<Producto> listarProductosSubastados() {
        return subastaRepo.listarProductosSubastados();
    }

    @Override
    public Integer buscarMayorOferta(Integer codigo) {
        return productoRepo.buscarMayorOferta(codigo);
    }

    @Override
    public SubastaUsuario buscarSubastaUsuario(Integer valor) {
        return productoRepo.buscarSubastaUsuario(valor);
    }

    @Override
    public Subasta obtenerSubasata(Integer codigo) throws Exception {
        return productoRepo.obtenerSubasta(codigo);
    }

    @Override
    public void subastarProducto(Subasta subasta) throws Exception{

        subastaRepo.save(subasta);
    }

    @Override
    public Categoria obtenerCategoria(String categoria) throws Exception {
        return Categoria.valueOf(categoria);
    }

    @Override
    public List<Producto> listarProductos() {
        LocalDateTime fechaActual = LocalDateTime.now();

        List<Producto> productosVencidos = productoRepo.listarProductosVencidos(fechaActual);
        for (Producto p : productosVencidos) {
            productoRepo.deleteById(p.getCodigo());
        }
        return productoRepo.findAll();
    }

    @Override
    public Integer obtenerUnidadesProducto(Integer codigo) throws Exception {
        return productoRepo.obtenerUnidadesProducto(codigo);
    }

    @Override
    public Integer obtenerPromedioComentario(Integer codigo) throws Exception {
        return productoRepo.obtenerPromedioComentario(codigo);
    }


    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFechaComentario(LocalDateTime.now());
        comentarioRepo.save(comentario);

    }

    @Override
    public void guardarProductoFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarProductoFavoritos(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void comprarProducto(Compra compra) throws Exception {

    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto, String[] filtros) {

        return productoRepo.buscarProductoPorNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductoUsuario(String codigoUsuario) {

        return productoRepo.listarProductosUsuario(codigoUsuario);
    }

    @Override
    public List<Subasta> listarSubastas() {
        return subastaRepo.findAll();
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception {



        try {
            Compra c = new Compra();
            c.setFechaCompra(LocalDateTime.now(ZoneId.of("America/Bogota")));
            c.setUsuarioCompra(usuario);
            c.setMedioPago(medioPago);
            Compra compraGuardada = compraRepo.save(c);
            Integer uCompradas = 0;
            Integer uExistentes=0;
            Integer uActuales=0;


            DetalleCompra dc;
            for (ProductoCarrito p : productos) {
                dc = new DetalleCompra();
                dc.setCompraDetalleCompra(compraGuardada);
                dc.setPrecioProducto(p.getPrecio());
                dc.setUnidades(p.getUnidades());
                uCompradas = p.getUnidades();
                uExistentes = productoRepo.obtenerUnidadesProducto(p.getCodigo());
                uActuales=uExistentes-uCompradas;
                if (uActuales!=0){
                    Producto productoBuscado = productoRepo.findById(p.getCodigo()).orElseThrow(() -> new Exception("El codio del producto no es valido"));
                    productoBuscado.setUnidades(uActuales);
                    productoRepo.save(productoBuscado);
                }
                dc.setProductoDetalleCompra(productoRepo.findById(p.getCodigo()).get());
                detalleCompraRepo.save(dc);
                if(uActuales==0){
                    productoRepo.deleteById(p.getCodigo());
                }
            }

            return compraGuardada;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void registrarSubastaUsuario(SubastaUsuario subastaUsuario) throws Exception {
        subastaUsuarioRepo.save(subastaUsuario);

    }

}
