package matrodriguezpa.receiptmanager.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import matrodriguezpa.receiptmanager.model.Year;

public class YearDAO extends DBConectionUtil {

    public YearDAO() {
        getDataBaseUrl();
    }

    // Crear tabla projects si no existe
    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS projects ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL UNIQUE"
                + ")";

        try {
            connect();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            closeConnection();
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating projects table: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Crear un nuevo proyecto y retornar el ID generado
    public Long createYear(Year project) {
        if (project == null || project.getName() == null || project.getName().trim().isEmpty()) {
            return null;
        }

        String sql = "INSERT INTO projects (name) VALUES (?)";

        try {
            connect();

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, project.getName().trim());
                int affected = stmt.executeUpdate();

                if (affected == 0) {
                    closeConnection();
                    return null;
                }
            }

            // Obtener el ID generado antes de cerrar la conexión
            Long generatedId = getLastInsertRowId();
            closeConnection();

            return generatedId;

        } catch (SQLException e) {
            System.err.println("Error creating project: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException ex) {
                /* noop */
            }
            return null;
        }
    }

    // Insertar nuevo proyecto y actualizar su id si se genera (mantener compatibilidad)
    public Year save(Year project) {
        if (project == null) {
            return null;
        }

        Long generatedId = createYear(project);
        if (generatedId != null) {
            project.setId(generatedId);
            return project;
        }
        return null;
    }

    // Obtener todos los proyectos
    public List<Year> findAll() {
        String sql = "SELECT id, name FROM projects ORDER BY name";

        List<Year> projects = new ArrayList<>();

        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Year p = Year.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build();
                projects.add(p);
            }

            rs.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding projects: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return projects;
    }

    // Buscar proyecto por id
    public Year findById(Long id) {
        if (id == null) {
            return null;
        }

        String sql = "SELECT id, name FROM projects WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            Year project = null;

            if (rs.next()) {
                project = Year.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build();
            }

            rs.close();
            statement.close();
            closeConnection();

            return project;

        } catch (SQLException e) {
            System.err.println("Error finding project by id: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return null;
        }
    }

    // Actualizar proyecto
    public boolean updateYear(Year project) {
        if (project == null || project.getId() == null
                || project.getName() == null || project.getName().trim().isEmpty()) {
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

    // Eliminar proyecto por id
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
            System.err.println("Error deleting project: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Verificar si existe proyecto por id
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }

        String sql = "SELECT COUNT(*) FROM projects WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

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
            System.err.println("Error checking if project exists by id: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Obtener el total de proyectos
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

    // Obtener proyectos con información de gastos
    public List<Year> findAllWithExpenseInfo() {
        String sql = "SELECT p.id, p.name, "
                + "COUNT(e.id) as expense_count, "
                + "COALESCE(SUM(e.amount), 0) as total_amount "
                + "FROM projects p "
                + "LEFT JOIN expenses e ON p.id = e.project_id "
                + "GROUP BY p.id, p.name "
                + "ORDER BY p.name";

        List<Year> projects = new ArrayList<>();

        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Year p = Year.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build();

                // Nota: Si tu modelo Project tiene campos para expense_count y total_amount,
                // puedes agregarlos aquí. De lo contrario, esta información se puede obtener
                // por separado cuando se necesite.
                projects.add(p);
            }

            rs.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding projects with expense info: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return projects;
    }

    public static void isEmpty(String yearText) {
        if (yearText == null || yearText.trim().isEmpty()) {
            throw new IllegalArgumentException("Year cannot be empty");
        }
    }

    public static int validateYear(String yearText) {
        int y;
        try {
            y = Integer.parseInt(yearText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid number");
        }
        if (y < 0 || y > 9999) {
            throw new IllegalArgumentException("Year must be between 0 and 9999");
        }
        return y;
    }
}
