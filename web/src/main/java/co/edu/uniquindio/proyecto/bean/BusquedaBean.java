package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    @Getter @Setter
    private BusquedaBean busquedaBean;


    @Getter @Setter
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter @Setter
    @Value("#{param['busquedaCategoria']}")
    private String busquedaCategoria;

    @Getter @Setter
    @Value("#{param['busquedaCiudad']}")
    private String busquedaCiudad;

    @Getter @Setter
    @Value("#{param['busquedaPrecio']}")
    private String busquedaPrecio;

    @Getter @Setter
    @Value("#{param['busquedaCalificacion']}")
    private String busquedaCalificacion;



    @Autowired
    private ProductoServicio productoServicio;


    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private Ciudad ciudad;


    @PostConstruct
    public void inicializar(){
        //this.ciudad = new Ciudad();
        this.busquedaBean = new BusquedaBean();
        if(busquedaParam!=null && !busquedaParam.isEmpty()){
            productos=productoServicio.buscarProducto(busquedaParam,null);
        }
        if(busquedaCategoria!=null && !busquedaCategoria.isEmpty()){
            Categoria categoria = Categoria.valueOf(busquedaCategoria);
            productos=productoServicio.listarPorCategoria(categoria);
        }

        if(busquedaPrecio !=null && !busquedaPrecio.isEmpty()){
            Integer precio= Integer.parseInt(busquedaPrecio);
            if(precio<1000000){
                productos=productoServicio.ListarPorPrecioMenor(precio);
            }else{
                productos=productoServicio.ListarPorPrecioMayor(precio);
            }
        }
        if(busquedaCiudad !=null && !busquedaCiudad.isEmpty()){
            Integer codigo= Integer.parseInt(busquedaCiudad);
            productos= productoServicio.ListarPorCiudad(codigo);
        }
        if(busquedaCalificacion !=null && !busquedaCalificacion.isEmpty()){
            Integer calificacion= Integer.parseInt(busquedaCalificacion);

            productos= productoServicio.ListarPorCalificacion(calificacion);
        }
    }

    public String buscar(){
        return "/resultado_busqueda?faces-redirect=true&amp;busqueda="+busqueda;
    }

    public String buscarPorCategoria(){
        return "/resultado_busqueda_categoria?faces-redirect=true&amp;busquedaCategoria="+busqueda;
    }

    @Getter @Setter
    private Object value; // +getter+setter.

    public  String buscarPorPrecio() {
        return "/resultado_busqueda_precio?faces-redirect=true&amp;busquedaPrecio="+busqueda;
    }
    public  String buscarPorCalificacion() {
        return "/resultado_busqueda_calificacion?faces-redirect=true&amp;busquedaCalificacion="+busqueda;
    }
    public  void buscarPorCiudadEvento(final AjaxBehaviorEvent event) {
    }


    public String buscarPorCiudad(){
        return "/resultado_busqueda_ciudad?faces-redirect=true&amp;busquedaCiudad="+ciudad.getCodigo();

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
