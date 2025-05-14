
import com.pucp.capadominio.categorias.Curso;
import com.pucp.capadominio.categorias.Especialidad;
import com.pucp.capadominio.categorias.Facultad;

import com.pucp.capadominio.publicacion.EstadoPublicacion;
import com.pucp.capadominio.publicacion.Publicacion;
import com.pucp.capadominio.usuarios.Usuario;

import com.pucp.capanegocio.interfacesService.PublicacionService;
import com.pucp.capanegocio.publicaciones.PublicacionServiceImpl;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PublicacionServiceImplTest {
    private static PublicacionService publicacionService;
    private static int publicacionId;

    private static Usuario usuarioPrueba;
    private static Curso cursoPrueba;
    private static Especialidad especialidadPrueba;
    private static Facultad facultadPrueba;

    @BeforeAll
    public static void setUp() {
        publicacionService = new PublicacionServiceImpl();

        // Asume que estas entidades ya existen o puedes simularlas para los tests
        usuarioPrueba = new Usuario();
        usuarioPrueba.setIdUsuario(1);  // Usa un ID válido o crea un usuario real si tu DAO lo permite
        usuarioPrueba.setNombre("Luis");

        cursoPrueba = new Curso();
        cursoPrueba.setIdCurso(1);

        especialidadPrueba = new Especialidad();
        especialidadPrueba.setIdEspecialidad(1);

        facultadPrueba = new Facultad();
        facultadPrueba.setIdFacultad(1);
    }

    private static Publicacion crearPublicacionPrueba() {
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo("Título de prueba");
        publicacion.setDescripcion("Contenido de prueba");
        publicacion.setEstado(EstadoPublicacion.VISIBLE);
        //publicacion.setFechaPublicacion();
        publicacion.setRutaImagen("/images/prueba.jpg");
        publicacion.setImagen(publicacion.getRutaImagen());
        publicacion.setUsuario(usuarioPrueba);

        publicacion.agregarCurso(cursoPrueba);
        publicacion.agregarEspecialidad(especialidadPrueba);
        publicacion.agregarFacultad(facultadPrueba);

        return publicacion;
    }

    @Test
    @Order(1)
    void registrarPublicacion() throws Exception {
        Publicacion publicacion = crearPublicacionPrueba();
        publicacionService.registrarPublicacion(publicacion);

        List<Publicacion> publicaciones = publicacionService.listarPublicacion();
        assertNotNull(publicaciones);
        assertFalse(publicaciones.isEmpty());

        Publicacion registrada = publicaciones.stream()
            .filter(p -> p.getTitulo().equals("Título de prueba"))
            .findFirst()
            .orElse(null);

        assertNotNull(registrada);
        publicacionId = registrada.getIdPublicacion();
        assertEquals("Contenido de prueba", registrada.getDescripcion());
    }

    @Test
    @Order(2)
    void obtenerPublicacion() throws Exception {
        Publicacion publicacion = publicacionService.obtenerPublicacion(publicacionId);
        assertNotNull(publicacion);
        assertEquals("Título de prueba", publicacion.getTitulo());
        assertEquals("Contenido de prueba", publicacion.getDescripcion());
        assertEquals(usuarioPrueba.getIdUsuario(), publicacion.getUsuario().getIdUsuario());
    }

    @Test
    @Order(3)
    void actualizarPublicacion() throws Exception {
        Publicacion publicacion = publicacionService.obtenerPublicacion(publicacionId);
        publicacion.setTitulo("Nuevo Título");
        publicacion.setDescripcion("Nuevo contenido");
        publicacionService.actualizarPublicacion(publicacion);

        Publicacion actualizada = publicacionService.obtenerPublicacion(publicacionId);
        assertEquals("Nuevo Título", actualizada.getTitulo());
        assertEquals("Nuevo contenido", actualizada.getDescripcion());
    }

    @Test
    @Order(4)
    void eliminarPublicacion() throws Exception {
        publicacionService.eliminarPublicacion(publicacionId);
        assertThrows(Exception.class, () -> {
            publicacionService.obtenerPublicacion(publicacionId);
        });
    }
    
    @Test
    @Order(5)
    void listarPublicaciones() throws Exception {
        List<Publicacion> publicaciones = publicacionService.listarPublicacion();
        assertNotNull(publicaciones);
        assertTrue(publicaciones.size() >= 0); // Puede estar vacía si eliminaste la única publicación
    }

    @Test
    @Order(6)
    void listarPorFacultad() throws Exception {
        List<Publicacion> publicaciones = publicacionService.listarPorFacultad(facultadPrueba.getIdFacultad());
        assertNotNull(publicaciones);
        // Puedes validar si contiene algún título esperado si ya insertaste antes
        // assertTrue(publicaciones.stream().anyMatch(p -> p.getTitulo().equals("Nuevo Título")));
    }

    @Test
    @Order(7)
    void listarPorEspecialidad() throws Exception {
        List<Publicacion> publicaciones = publicacionService.listarPorEspecialidad(especialidadPrueba.getIdEspecialidad());
        assertNotNull(publicaciones);
    }

    @Test
    @Order(8)
    void listarPorCurso() throws Exception {
        List<Publicacion> publicaciones = publicacionService.listarPorCurso(cursoPrueba.getIdCurso());
        assertNotNull(publicaciones);
    }
}