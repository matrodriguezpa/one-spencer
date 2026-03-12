package matrodriguezpa.receiptmanager.dao;

import matrodriguezpa.receiptmanager.Util.DBConectionUtil;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import matrodriguezpa.receiptmanager.model.Project;

public class ProjectDAO extends DBConectionUtil {


    public ProjectDAO() {
        getDataBaseUrl();
    }

    // Create projects table if it does not exist
    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS projects ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL"
                + ")";

        try {
            connect();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            closeConnection();
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Create new project
    public Long createProject(Project project) {
        if (project == null) {
            return null;
        }

        String sql = "INSERT INTO projects (name) VALUES (?)";

        try {
            connect(); // abre la conexión y deja 'connection' activa

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, project.getName());
                int affected = stmt.executeUpdate();

                if (affected == 0) {
                    // no insertó
                    closeConnection();
                    return null;
                }
            }

            // IMPORTANTE: obtener last_insert_rowid() antes de cerrar la conexión
            Long generatedId = getLastInsertRowId();

            closeConnection(); // ahora sí cerramos

            return generatedId;

        } catch (SQLException e) {
            System.err.println("Error creating project: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException ex) {
                /* noop */ }
            return null;
        }
    }

    // Actualizar usuario existente
    public boolean updateProject(Project project) {
        if (project == null || project.getId() == null || project.getName() == null || project.getName().trim().isEmpty()) {
            return false;
        }

        String sql = "UPDATE projects SET name = ? WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName().trim());
            statement.setLong(2, project.getId());

            int affectedRows = statement.executeUpdate();
            statement.close();
            closeConnection();

            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error updating project: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Buscar usuario por ID
    public Project findById(Long id) {
        if (id == null) {
            return null;
        }

        String sql = "SELECT id, name FROM projects WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            Project project = null;

            if (result.next()) {
                project = Project.builder()
                        .id(result.getLong("id"))
                        .name(result.getString("name"))
                        .build();
            }

            result.close();
            statement.close();
            closeConnection();

            return project;

        } catch (SQLException e) {
            System.err.println("Error finding projects by id: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return null;
        }
    }

    // Buscar usuarios por nombre (búsqueda parcial)
    public List<Project> findByName(String name) {
        List<Project> projects = new ArrayList<>();

        if (name == null || name.trim().isEmpty()) {
            return projects;
        }

        String sql = "SELECT id, name FROM projects WHERE name LIKE ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name.trim() + "%");

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Project project = Project.builder()
                        .id(result.getLong("id"))
                        .name(result.getString("name"))
                        .build();
                projects.add(project);
            }

            result.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding projects by name: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return projects;
    }

    // Obtener todos los usuarios
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT id, name FROM projects ORDER BY name";

        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                Project project = Project.builder()
                        .id(result.getLong("id"))
                        .name(result.getString("name"))
                        .build();
                projects.add(project);
            }

            result.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding all projects: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return projects;
    }

    // Eliminar usuario por ID
    public boolean deleteById(Long id) {
        if (id == null) {
            return false;
        }

        String sql = "DELETE FROM projects WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();
            statement.close();
            closeConnection();

            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting projects: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Verificar si existe un usuario con el nombre dado
    public boolean existsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        String sql = "SELECT COUNT(*) FROM projects WHERE name = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name.trim());

            ResultSet result = statement.executeQuery();
            boolean exists = false;

            if (result.next()) {
                exists = result.getInt(1) > 0;
            }

            result.close();
            statement.close();
            closeConnection();

            return exists;

        } catch (SQLException e) {
            System.err.println("Error checking if project exists: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Obtener el total de usuarios
    public int getProjectCount() {
        String sql = "SELECT COUNT(*) FROM projects";

        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            int count = 0;
            if (result.next()) {
                count = result.getInt(1);
            }

            result.close();
            statement.close();
            closeConnection();

            return count;

        } catch (SQLException e) {
            System.err.println("Error getting project count: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return 0;
        }
    }
}
