package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter @Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private List<Categoria> categorias;

    @Getter @Setter
    private List<Ciudad> ciudades;


    private ArrayList<String> imagenes;

    @Value("${upload.url}")
    private String urlUploads;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar(){
        this.producto = new Producto();
        this.imagenes= new ArrayList<>();
        categorias = productoServicio.ListarCategorias();
        ciudades = ciudadServicio.listarCiudades();
    }

    public  void registrarProducto(){
        try {
            if(usuarioSesion!=null) {
                if (!imagenes.isEmpty()) {
                    producto.setVendedor(usuarioSesion);
                    producto.setImagenes(imagenes);
                    producto.setFechaLimite(LocalDateTime.now().plusMonths(2));
                    productoServicio.publicarProducto(producto);
                    FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso");
                    FacesContext.getCurrentInstance().addMessage(null, mensaje);
                } else {
                    FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "necesita subir almenos una imagen");
                    FacesContext.getCurrentInstance().addMessage(null, mensaje);
                }
            }
        } catch (Exception e) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,mensaje);
            e.printStackTrace();
        }
    }

    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = guardarImagen(imagen);
        if(nombreImagen!= null){
            imagenes.add(nombreImagen);
        }
    }

    public String guardarImagen(UploadedFile imagen){
        try {
            File archivo = new File(urlUploads+"/"+imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(),outputStream);
            return imagen.getFileName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

