package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.SubastaUsuario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
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
public class SubastaBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Getter
    @Setter
    private List<Producto> productos;

    @Value("#{param['codigoSubasta']}")
    private String codigoSubasta;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Subasta subasta;

    @Getter @Setter
    private List<Subasta> subastas;

    @Getter @Setter
    private SubastaUsuario subastaUsuario;



    @PostConstruct
    public void inicializar(){
        this.subastaUsuario = new SubastaUsuario();
        this.productos = productoServicio.listarProductosSubastados();
        subastas = productoServicio.listarSubastas();
        for (Subasta s : subastas) {
            Integer valorMayor = productoServicio.buscarMayorOferta(s.getCodigo());
            subastaUsuario = productoServicio.buscarSubastaUsuario(valorMayor);
        }



        if(codigoSubasta!=null && !codigoSubasta.isEmpty()){
            Integer codigo= Integer.parseInt(codigoSubasta);

            try {
                producto = productoServicio.obtenerProducto(codigo);
                subasta = productoServicio.obtenerSubasata(codigo);

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

    public String irADetalleSubasta(String codigo){
        return "/usuario/detalle_subasta?faces-redirect=true&amp;codigoSubasta="+codigo;
    }

    public void registrarSubastaUsuario(){

        try {

            subastaUsuario.setFecha(LocalDateTime.now().plusHours(1));
            subastaUsuario.setUsuarioSubastaUsuario(usuarioSesion);
            subastaUsuario.setSubastaSubastaUsuario(subasta);
            productoServicio.registrarSubastaUsuario(subastaUsuario);
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Compra Realizada");
            FacesContext.getCurrentInstance().addMessage("sub-usuario-msj",fm);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage("sub-usuario-msj",fm);
        }


    }



}