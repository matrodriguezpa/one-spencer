package matrodriguezpa.receiptmanager;

import matrodriguezpa.receiptmanager.dao.ProjectDAO;
import matrodriguezpa.receiptmanager.model.Project;
import java.util.List;

public class UserTest {
    public static void main(String[] args) {
        ProjectDAO userDAO = new ProjectDAO();

        // 1. Crear tabla
        System.out.println("Creando tabla: " + userDAO.createTable());

        // 2. Crear usuario
        Project user1 = Project.builder().name("Mateo").build();
        Long id1 = userDAO.createProject(user1);
        System.out.println("Usuario creado con id: " + id1);

        // 3. Crear otro usuario
        Project user2 = Project.builder().name("Ana").build();
        Long id2 = userDAO.createProject(user2);
        System.out.println("Usuario creado con id: " + id2);

        // 4. Buscar usuario por id
        Project foundUser = userDAO.findById(id1);
        System.out.println("Usuario encontrado: " + foundUser);

        // 5. Buscar usuarios por nombre
        List<Project> usersByName = userDAO.findByName("Ma");
        System.out.println("Usuarios encontrados con 'Ma': " + usersByName);

        // 6. Obtener todos los usuarios
        List<Project> allUsers = userDAO.findAll();
        System.out.println("Todos los usuarios: " + allUsers);

        // 7. Verificar existencia de usuario
        boolean exists = userDAO.existsByName("Ana");
        System.out.println("¿Existe Ana?: " + exists);

        // 8. Actualizar usuario
        if (foundUser != null) {
            foundUser.setName("Mateo Actualizado");
            boolean updated = userDAO.updateProject(foundUser);
            System.out.println("Usuario actualizado: " + updated);
        }

        // 9. Contar usuarios
        int count = userDAO.getProjectCount();
        System.out.println("Total de usuarios: " + count);

        // 10. Eliminar usuario por id
        boolean deleted = userDAO.deleteById(id2);
        System.out.println("Usuario con id " + id2 + " eliminado: " + deleted);

        // 11. Mostrar usuarios finales
        allUsers = userDAO.findAll();
        System.out.println("Usuarios finales: " + allUsers);
    }
}
