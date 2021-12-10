package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Component
public class DetalleProductoFavoritoBean implements Serializable {


    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{param['favorito']}")
    private String codigoProducto;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter @Setter
    private List<Producto> productosFavoritos;



    @PostConstruct
    public void inicializar(){
        nuevoComentario = new Comentario();
        if(codigoProducto!=null && !codigoProducto.isEmpty()){
            Integer codigo= Integer.parseInt(codigoProducto);

            try {
                producto = productoServicio.obtenerProducto(codigo);
                this.comentarios = producto.getComentarios();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void crearComentario(){

        try {
            if(usuarioSesion!=null){
                nuevoComentario.setProductoComentario(producto);
                nuevoComentario.setUsuarioComentario((usuarioSesion));
                productoServicio.comentarProducto(nuevoComentario);
                this.comentarios.add(nuevoComentario);
                nuevoComentario = new Comentario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer calcularPromedioComentario(Integer codigo){

        try {
            return productoServicio.obtenerPromedioComentario(codigo);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
