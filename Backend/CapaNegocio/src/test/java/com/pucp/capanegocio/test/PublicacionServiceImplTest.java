    package com.pucp.capanegocio.test;

    import com.pucp.capadominio.categorias.Curso;
    import com.pucp.capadominio.categorias.Especialidad;
    import com.pucp.capadominio.categorias.Facultad;

    import com.pucp.capadominio.publicacion.EstadoPublicacion;
    import com.pucp.capadominio.publicacion.Publicacion;
    import com.pucp.capadominio.usuarios.EstadoUsuario;
    import com.pucp.capadominio.usuarios.Usuario;

    import com.pucp.capanegocio.interfacesService.PublicacionService;
    import com.pucp.capanegocio.publicaciones.PublicacionServiceImpl;

    import com.pucp.da.categorias.CursoCRUD;
    import com.pucp.da.categorias.EspecialidadCRUD;
    import com.pucp.da.categorias.FacultadCRUD;
    import com.pucp.da.usuarios.UsuarioCRUD;
    import com.pucp.interfacesDAO.CursoDAO;
    import com.pucp.interfacesDAO.EspecialidadDAO;
    import com.pucp.interfacesDAO.FacultadDAO;
    import com.pucp.interfacesDAO.UsuarioDAO;

    import org.junit.jupiter.api.*;
    import static org.junit.jupiter.api.Assertions.*;

    import java.util.*;

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class PublicacionServiceImplTest {
        private static PublicacionService publicacionService;
        private static UsuarioDAO usuarioDAO;
        private static CursoDAO cursoDAO;
        private static EspecialidadDAO especialidadDAO;
        private static FacultadDAO facultadDAO;
        private static int publicacionId;

        private static Usuario usuarioPrueba;
        private static Curso cursoPrueba;
        private static Especialidad especialidadPrueba;
        private static Facultad facultadPrueba;

    @BeforeAll
    public static void setUp() {
        publicacionService = new PublicacionServiceImpl();

        usuarioDAO = new UsuarioCRUD();
        cursoDAO = new CursoCRUD();
        especialidadDAO = new EspecialidadCRUD();
        facultadDAO = new FacultadCRUD();

        // Crear e insertar usuario de prueba
        usuarioPrueba = new Usuario();
        usuarioPrueba.setCodigoPUCP(20249999);
        usuarioPrueba.setNombreUsuario("usuarioAtaque");
        usuarioPrueba.setContrasena("123456");
        usuarioPrueba.setNombre("Usuario Test");
        usuarioPrueba.setCorreo("usuario@test.com");
        usuarioPrueba.setEstado(EstadoUsuario.HABILITADO);
        usuarioPrueba.setActivo(true);
        usuarioDAO.insertar(usuarioPrueba); // <- esto garantiza que sí existe

        // Lo mismo aplica si curso, especialidad o facultad también podrían estar vacíos
        cursoPrueba = cursoDAO.obtenerPorId(1);
        especialidadPrueba = especialidadDAO.obtenerPorId(1);
        facultadPrueba = facultadDAO.obtenerPorId(1);

    }


        private static Publicacion crearPublicacionPrueba() {
            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo("Título prueba");
            publicacion.setDescripcion("Contenido prueba");
            publicacion.setEstado(EstadoPublicacion.VISIBLE);
            //publicacion.setFechaPublicacion();
            publicacion.setRutaImagen("/images/prueba.jpg");
            publicacion.setImagen(publicacion.getRutaImagen());
            publicacion.setUsuario(usuarioPrueba);
            publicacion.setActivo(true);

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
                .filter(p -> p.getTitulo().equals("Título prueba"))
                .findFirst()
                .orElse(null);

            System.err.println(registrada.getTitulo());


            assertNotNull(registrada);
            publicacionId = registrada.getIdPublicacion();
            assertEquals("Contenido prueba", registrada.getDescripcion());
            System.err.println("ID que estoy insertando: " + publicacionId);

        }

        @Test
        @Order(2)
        void obtenerPublicacion() throws Exception {
            System.err.println("ID que se busca: " + publicacionId);
            Publicacion publicacion = publicacionService.obtenerPublicacion(publicacionId);
            System.err.println("¿Publicación es null? " + (publicacion == null));

            assertNotNull(publicacion);
            assertEquals("Título prueba", publicacion.getTitulo());
            assertEquals("Contenido prueba", publicacion.getDescripcion());
            assertEquals(usuarioPrueba.getIdUsuario(), publicacion.getUsuario().getIdUsuario());
        }

        @Test
        @Order(3)
        void actualizarPublicacion() throws Exception {
            Publicacion publicacion = publicacionService.obtenerPublicacion(publicacionId);

            System.err.println("ID Publicación obtenida: " + publicacion.getIdPublicacion());
            System.err.println("Cursos relacionados:");
            if (publicacion.getPublicacionesCursos() != null) {
                publicacion.getPublicacionesCursos().forEach(c -> System.err.println("Curso: " + c.getIdCurso() + " - " + c.getNombre()));
            } else {
                System.err.println("La lista de cursos es NULL");
            }
            publicacion.setTitulo("Nuevo Título");
            publicacion.setDescripcion("Nuevo contenido");
            publicacionService.actualizarPublicacion(publicacion);

            Publicacion actualizada = publicacionService.obtenerPublicacion(publicacionId);
            assertEquals("Nuevo Título", actualizada.getTitulo());
            assertEquals("Nuevo contenido", actualizada.getDescripcion());
        }


        @Test
        @Order(4)
        void listarPublicaciones() throws Exception {
            List<Publicacion> publicaciones = publicacionService.listarPublicacion();
            assertNotNull(publicaciones);
            assertTrue(publicaciones.size() >= 0); // Puede estar vacía si eliminaste la única publicación
        }

        @Test
        @Order(5)
        void listarPorFacultad() throws Exception {
            List<Publicacion> publicaciones = publicacionService.listarPorFacultad(facultadPrueba.getIdFacultad());
            assertNotNull(publicaciones);
            // Puedes validar si contiene algún título esperado si ya insertaste antes
             assertTrue(publicaciones.stream().anyMatch(p -> p.getTitulo().equals("Nuevo Título")));
        }

        @Test
        @Order(6)
        void listarPorEspecialidad() throws Exception {
            List<Publicacion> publicaciones = publicacionService.listarPorEspecialidad(especialidadPrueba.getIdEspecialidad());
            assertNotNull(publicaciones);
        }

        @Test
        @Order(7)
        void listarPorCurso() throws Exception {
            List<Publicacion> publicaciones = publicacionService.listarPorCurso(cursoPrueba.getIdCurso());
            assertNotNull(publicaciones);
        }


        @Test
        @Order(8)
        void eliminarPublicacion() throws Exception {
            publicacionService.eliminarPublicacion(publicacionId);
            Publicacion publicacionEliminada = publicacionService.obtenerPublicacion(publicacionId);

            assertNotNull(publicacionEliminada);
            assertFalse(publicacionEliminada.isActivo());
        }
    }