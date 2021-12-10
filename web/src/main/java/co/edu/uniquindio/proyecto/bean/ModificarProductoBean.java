package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
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
import java.time.LocalDateTime;
import java.util.List;


@Component
@ViewScoped
public class ModificarProductoBean implements Serializable {

    @Autowired
    ProductoServicio productoServicio;

    @Autowired
    UsuarioServicio usuarioServicio;

    @Getter
    @Setter
    private List<Producto> productos;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter @Setter
    private Usuario usuarioActual;

    @Value("#{param['modificar']}")
    private String codigoProducto;

    @Getter @Setter
    private Producto productoActual;

    @Getter @Setter
    private List<Categoria> categorias;

    @PostConstruct
    public void inicializar(){
        categorias = productoServicio.ListarCategorias();
        if(codigoProducto!=null && !codigoProducto.isEmpty()){
            Integer codigo= Integer.parseInt(codigoProducto);

            try {
                productoActual = productoServicio.obtenerProducto(codigo);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public  void modificarProducto(){
        try {
            if(usuarioSesion!=null) {
                productoServicio.publicarProducto(productoActual);
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Modificacion exitoso");
                FacesContext.getCurrentInstance().addMessage(null, mensaje);
            }
        } catch (Exception e) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,mensaje);
            e.printStackTrace();
        }
    }



}
