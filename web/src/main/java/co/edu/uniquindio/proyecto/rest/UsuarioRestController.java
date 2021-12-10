package co.edu.uniquindio.proyecto.rest;


import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioServicio usuarioServicio;


    @GetMapping
    public List<Usuario> ListarUsuarios(){
        return  usuarioServicio.listarUsuarios();


    }


    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable("id") String idUsuario) {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario(idUsuario);
            return  ResponseEntity.status(200).body(usuario);

        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Mensaje> crearUsuario(@RequestBody Usuario usuario) {
        try {
             usuarioServicio.registrarUsuario(usuario);
            return  ResponseEntity.status(201).body(new Mensaje("El usuario se registro correctamente"));

        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizarUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioServicio.actualizarUsuario(usuario);
            return  ResponseEntity.status(200).body(new Mensaje("El usuario se actualizo correctamente"));
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<Mensaje> eliminarUsuario(@PathVariable("id")String id){
        try {
            usuarioServicio.eliminarUsuario(id);
            return  ResponseEntity.status(200).body(new Mensaje("El usuario se elimino correctamente"));

        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/favoritos/{id}")
    public ResponseEntity<?> obtenerFavoritos(@PathVariable("id") String id){

        try {
            List<Producto> productos = usuarioServicio.listarProductosFavoritos(id);
            return  ResponseEntity.status(200).body(productos);
        } catch (Exception e) {
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }



}
