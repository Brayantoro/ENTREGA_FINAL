package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
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


@ViewScoped
@Component
public class DetalleCompraBean implements Serializable {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{param['compra']}")
    private String codigoCompra;

    @Getter @Setter
    private List<DetalleCompra> detalleCompras;



    @Getter @Setter
    private Integer subtotal ;

    @PostConstruct
    public void inicializar(){

        if(codigoCompra!=null && !codigoCompra.isEmpty()){
            Integer codigo= Integer.parseInt(codigoCompra);

            try {

                this.detalleCompras = usuarioServicio.listaDetalleCompra(codigo);
                this.subtotal = usuarioServicio.calcularTotalDetalle(codigo);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
