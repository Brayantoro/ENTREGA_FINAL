package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ComentarioRepo comentarioRepo;
    @Autowired
    private CompraRepo compraRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private ProductoRepo productoRepo;


    @Test
    @Sql("classpath:usuarios.sql")
    public void listarPorCategoria(){
        List<Object[]>productosCategoria = productoRepo.obtenerTotalProductoCategoria();
        productosCategoria.forEach(c -> System.out.println(c));
    }
    @Test
    @Sql("classpath:usuarios.sql")
    public void filtratNombreTest(){
        List<Usuario> listaUsuarios = usuarioRepo.findAllByNombreContains("alba");
        listaUsuarios.forEach( u -> System.out.println(u));
    }

      //metodo para paginar listas
    @Test
    @Sql("classpath:usuarios.sql")
    public void paginarListaTest() {

        Pageable paginador = (Pageable) PageRequest.of(0,2);
        Page<Usuario> lista = usuarioRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList()));

    }
     //metodo para ordenar una lista
    @Test
    @Sql("classpath:usuarios.sql")
    public void ordenarListaTest() {


        List<Usuario> lista= usuarioRepo.findAll(Sort.by("nombre"));
        System.out.println(lista);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void filtrarRangoComentario() {


        List<Comentario> lista = comentarioRepo.listarComentarioRango2(6,1);
        System.out.println(lista);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void ListaProductosCompradosTests() {


        Long totalProductos = compraRepo.obtenerListaProductosComprados("01");
        System.out.println(("El totl es: ----->"+totalProductos));

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void ListaCiudadTest() {


        List<Usuario> usuarios = ciudadRepo.obtenerListaUsuario("Armenia");
        usuarios.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void obtenerNombreVendedorTests() {

     String nombre = productoRepo.obtenerNombreVendedor(12);
     Assertions.assertEquals("fabian ramirez", nombre);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void obtenerProductosFavoritosTests() {

     List<Producto> favoritos = usuarioRepo.obtenerProductosFavoritos("alba@gmail.com");
     favoritos.forEach(System.out::println);
        Assertions.assertEquals(2, favoritos.size());

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosTests() {

        List<Usuario> usuarios = ciudadRepo.listarUsuarios("Armenia");
        usuarios.forEach(System.out::println);
        Assertions.assertEquals(1, usuarios.size());
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosYProductosTest() {

        List<Object[]> respuesta = usuarioRepo.listarUsuariosYProductos();
        for(Object[] objeto: respuesta){
            System.out.println(objeto[0]+"---"+objeto[1]+"---"+objeto[2] );
        }
       Assertions.assertEquals(4, respuesta.size());
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarProductosYComentariosTest() {

        List<Object[]> respuesta = productoRepo.listarProductosYComentarios();
        respuesta.forEach(objeto -> System.out.println(objeto[0]+"---"+objeto[1]));
         Assertions.assertEquals(4, respuesta.size());
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuarioComentarioTest() {

       List<Usuario> usuarios = productoRepo.listarUsuariosComentario(10);
       usuarios.forEach(System.out::println);
        Assertions.assertEquals(2, usuarios.size());

    }



    @Test
    @Sql("classpath:usuarios.sql")
    public void listarProductosPorCategoriaTest() {

        List<Object[]> respuesta = productoRepo.obtenerTotalProductoCategoria();
        respuesta.forEach(o -> System.out.println(o[0]+"----"+o[1]));
       // Assertions.assertEquals(2, respuesta.size());

    }


       //productoRepo.delete();

        //busco el producto para comprobar q no exista
        //Producto productoBuscado = productoRepo.findById(10).orElse(null);

       // Assertions.assertNull(productoBuscado);

    }





/*
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;



    @Test
    public void resgistrarUsuarioTest(){
        //guardar usuario en la base de datos
        Ciudad ciudad = new Ciudad("Armenia");
        ciudadRepo.save(ciudad);
           ciudadRepo.
        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("casa", "17171717");
        telefonos.put("celular", "3138745728");
        Usuario usuario = new Usuario("123","fabian", LocalDate.now(),GeneroPersona.MASCULINO, "fabian@gmail.com",telefonos,ciudad);

        Usuario usuarioGuardado = usuarioRepo.save(usuario);

        Assertions.assertNotNull(usuarioGuardado);


    }

    @Test
    public void eliminarUsuarioTest(){
    //guardar usuario en la base de datos
        Ciudad ciudad = new Ciudad("Armenia");
        ciudadRepo.save(ciudad);

        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("casa", "17171717");
        telefonos.put("celular", "3138745728");
        Usuario usuario = new Usuario("123","fabian", LocalDate.now(),GeneroPersona.MASCULINO, "fabian@gmail.com",telefonos,ciudad);



        usuarioRepo.save(usuario);
        //elimino el usuario
        usuarioRepo.deleteById("123");
        //busco el usuario para comprobar q no exista
        Usuario usuarioBuscado = usuarioRepo.findById("123").orElse(null);

        Assertions.assertNull(usuarioBuscado);

    }

    @Test
    public void actualizarUsuarioTest(){
    //guardar usuario en la base de datos
        Ciudad ciudad = new Ciudad("Armenia");
        ciudadRepo.save(ciudad);

        Map<String, String> telefonos = new HashMap<>();
        telefonos.put("casa", "17171717");
        telefonos.put("celular", "3138745728");
        Usuario usuario = new Usuario("123","fabian", LocalDate.now(),GeneroPersona.MASCULINO, "fabian@gmail.com",telefonos,ciudad);

        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        //modifico el email
        usuarioGuardado.setEmail("mauricio@gmail.com");
        //actualizo el usuario guardandolo nuevamente
        usuarioRepo.save(usuarioGuardado);
        //busco el usuario
        Usuario usuarioBuscado = usuarioRepo.findById("123").orElse(null);
        //comparo q halla tomado la actualizacion
        Assertions.assertEquals("mauricio@gmail.com", usuarioBuscado.getEmail());

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void ListarUsuarioTest(){

        List<Usuario> usuarios = usuarioRepo.findAll();

        Assertions.assertEquals(3, usuarios.size());
    usuarios.forEach(u -> System.out.println(u) );
    }

*/

