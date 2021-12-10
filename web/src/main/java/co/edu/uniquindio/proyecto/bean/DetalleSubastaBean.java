package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

@ViewScoped
@Component
public class DetalleSubastaBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{param['subastar']}")
    private String codigoProducto;

    @Getter
    @Setter
    private Producto producto;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter @Setter
    private List<Producto> productosFavoritos;

    @Getter  @Setter
    private Subasta subasta;

    @Getter  @Setter
    private LocalDateTime fechaActual;



    @PostConstruct
    public void inicializar(){
        fechaActual = LocalDateTime.now();
        if(codigoProducto!=null && !codigoProducto.isEmpty()){
            Integer codigo= Integer.parseInt(codigoProducto);

            try {
                producto = productoServicio.obtenerProducto(codigo);
                this.subasta = new Subasta();
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    public void subastarProducto(Producto producto){


        try {
            subasta.setProductoSubasta(producto);
            productoServicio.subastarProducto(subasta);
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","El Producto se subasto correctamente");
            FacesContext.getCurrentInstance().addMessage("subastar-msj",fm);


        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("subastar-msj",fm);
        }


    }






}