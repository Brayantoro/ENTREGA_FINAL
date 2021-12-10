package co.edu.uniquindio.proyecto.rest;


import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @GetMapping
    public List<Producto> ListarProductos(){
        return  productoServicio.listarProductos();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProducto(@PathVariable("id") Integer idProducto) {
        try {

            return  ResponseEntity.status(200).body(productoServicio.obtenerProducto(idProducto));

        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Mensaje> crearProducto(@RequestBody Producto producto) {
        try {
            productoServicio.publicarProducto(producto);
            return  ResponseEntity.status(201).body(new Mensaje("El usuario se registro correctamente"));

        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizarPoducto(@RequestBody Producto producto) {
        try {
            productoServicio.actualizarProducto(producto);
            return  ResponseEntity.status(200).body(new Mensaje("El usuario se actualizo correctamente"));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<Mensaje> eliminarProducto(@PathVariable("id")Integer id){
        try {
            productoServicio.eliminarProducto(id);
            return  ResponseEntity.status(200).body(new Mensaje("El usuario se elimino correctamente"));

        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/favoritos/{id}")
    public ResponseEntity<?> obtenerProductosFavoritos(@PathVariable("id") String id){

        try {
            List<Producto> productos = usuarioServicio.listarProductosFavoritos(id);
            return  ResponseEntity.status(200).body(productos);
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }



    @GetMapping("/extra/{id}")
    public ResponseEntity<?> obtenerUnidadesProducto(@PathVariable("id") Integer id){
        try {

            return  ResponseEntity.status(200).body(productoServicio.obtenerUnidadesProducto(id));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/extra2/{id}")
    public ResponseEntity<?> obtenerPromedio(@PathVariable("id") Integer id){
        try {

            return  ResponseEntity.status(200).body(productoServicio.obtenerPromedioComentario(id));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> buscarCategoria(@PathVariable("categoria") String categoria){
        try {
            Categoria categoriaEnum = Categoria.valueOf(categoria);
            return  ResponseEntity.status(200).body(productoServicio.listarPorCategoria(categoriaEnum));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/precioMenor/{menor}")
    public ResponseEntity<?> buscarPorPrecioMenor(@PathVariable("menor") String menor){
        try {
            Integer precio= Integer.parseInt(menor);
            return  ResponseEntity.status(200).body(productoServicio.ListarPorPrecioMenor(precio));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/precioMayor/{menor}")
    public ResponseEntity<?> buscarPorPrecioMayor(@PathVariable("mayor") String mayor){
        try {
            Integer precio= Integer.parseInt(mayor);
            return  ResponseEntity.status(200).body(productoServicio.ListarPorPrecioMayor(precio));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/ciudades/")
    public List<Ciudad> ListarCiudades() {
        return ciudadServicio.listarCiudades();
    }

}
