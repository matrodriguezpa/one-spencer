package matrodriguezpa.receiptmanager;

import matrodriguezpa.receiptmanager.dao.ProjectDAO;
import matrodriguezpa.receiptmanager.model.Project;
import java.util.List;

public class ProjectTest {
    public static void main(String[] args) {
        ProjectDAO projectDAO = new ProjectDAO();

        // 1. Crear tabla
        System.out.println("Creando tabla: " + projectDAO.createTable());

        // 2. Crear proyecto
        Project user1 = Project.builder().name("Mateo").build();
        Long id1 = projectDAO.createProject(user1);
        System.out.println("Projectoprojecto creado con id: " + id1);

        // 3. Crear otro proyecto
        Project user2 = Project.builder().name("Ana").build();
        Long id2 = projectDAO.createProject(user2);
        System.out.println("Projecto creado con id: " + id2);

        // 4. Buscar proyecto por id
        Project foundUser = projectDAO.findById(id1);
        System.out.println("Projecto encontrado: " + foundUser);

        // 5. Buscar proyectos por nombre
        List<Project> projectsByName = projectDAO.findByName("Ma");
        System.out.println("Projectos encontrados con 'Ma': " + projectsByName);

        // 6. Obtener todos los proyectos
        List<Project> allProjects = projectDAO.findAll();
        System.out.println("Todos los Projectos: " + allProjects);

        // 7. Verificar existencia de proyecto
        boolean exists = projectDAO.existsByName("Ana");
        System.out.println("¿Existe Ana?: " + exists);

        // 8. Actualizar proyecto
        if (foundUser != null) {
            foundUser.setName("Mateo Actualizado");
            boolean updated = projectDAO.updateProject(foundUser);
            System.out.println("Projecto actualizado: " + updated);
        }

        // 9. Contar proyectos
        int count = projectDAO.getProjectCount();
        System.out.println("Total de Projectos: " + count);

        // 10. Eliminar proyecto por id
        boolean deleted = projectDAO.deleteById(id2);
        System.out.println("Projecto con id " + id2 + " eliminado: " + deleted);

        // 11. Mostrar proyectos finales
        allProjects = projectDAO.findAll();
        System.out.println("Projectos finales: " + allProjects);
    }
}
