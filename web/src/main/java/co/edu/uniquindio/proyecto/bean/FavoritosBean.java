package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class FavoritosBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter
    @Setter
    private List<Producto> productosFavoritos;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar(){
        try {
            this.productosFavoritos = usuarioServicio.obtenerProductosFavoritos(usuarioSesion.getCodigo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String irADetalleFavorito(String codigo){
        return "/usuario/detalle_favoritos?faces-redirect=true&amp;favorito="+codigo;
    }

    public void eliminarDeFavoritos(int indice){

        productosFavoritos.remove(indice);
        usuarioSesion.setProductosFavoritos(productosFavoritos);
        usuarioServicio.agragarFavorito(usuarioSesion);
    }

}
