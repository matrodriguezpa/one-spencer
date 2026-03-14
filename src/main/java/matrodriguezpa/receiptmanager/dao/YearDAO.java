package matrodriguezpa.receiptmanager.dao;

import matrodriguezpa.receiptmanager.Util.DBConectionUtil;
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

    /**
     * Crea la tabla years con la estructura adecuada. Incluye las columnas: id
     * (PK), project_id, year, tag.
     */
    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS years ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "project_id INTEGER NOT NULL, "
                + "year INTEGER NOT NULL, "
                + "tag TEXT, "
                + "UNIQUE(project_id, year)" // Evita años duplicados para un mismo proyecto
                + ")";
        // Opcional: FOREIGN KEY (project_id) REFERENCES projects(id) si existe la tabla projects

        try {
            connect();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            closeConnection();
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating years table: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    /**
     * Inserta un nuevo año en la base de datos y retorna el ID generado.
     */
    public Long createYear(Year year) {
        if (year == null || year.getProjectId() == null || year.getTag() == null || year.getTag().trim().isEmpty()) {
            return null;
        }

        String sql = "INSERT INTO years (project_id, year, tag) VALUES (?, ?, ?)";

        try {
            connect();
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setLong(1, year.getProjectId());
                stmt.setInt(2, year.getYEAR());
                stmt.setString(3, year.getTag().trim());
                int affected = stmt.executeUpdate();

                if (affected == 0) {
                    closeConnection();
                    return null;
                }
            }

            Long generatedId = getLastInsertRowId(); // Asume que este método existe en DBConectionUtil
            closeConnection();
            return generatedId;

        } catch (SQLException e) {
            System.err.println("Error creating year: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException ex) {
                // noop
            }
            return null;
        }
    }

    /**
     * Inserta un nuevo año y actualiza su ID si la operación es exitosa.
     */
    public Year save(Year year) {
        if (year == null) {
            return null;
        }
        Long generatedId = createYear(year);
        if (generatedId != null) {
            year.setId(generatedId);
            return year;
        }
        return null;
    }

    /**
     * Obtiene todos los años ordenados por año.
     */
    public List<Year> findAll() {
        String sql = "SELECT id, project_id, year, tag FROM years ORDER BY year";

        List<Year> years = new ArrayList<>();

        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Year y = Year.builder()
                        .id(rs.getLong("id"))
                        .projectId(rs.getLong("project_id"))
                        .YEAR(rs.getInt("year"))
                        .tag(rs.getString("tag"))
                        .build();
                years.add(y);
            }

            rs.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding years: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return years;
    }

    /**
     * Busca un año por su ID.
     */
    public Year findById(Long id) {
        if (id == null) {
            return null;
        }

        String sql = "SELECT id, project_id, year, tag FROM years WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            Year year = null;

            if (rs.next()) {
                year = Year.builder()
                        .id(rs.getLong("id"))
                        .projectId(rs.getLong("project_id"))
                        .YEAR(rs.getInt("year"))
                        .tag(rs.getString("tag"))
                        .build();
            }

            rs.close();
            statement.close();
            closeConnection();

            return year;

        } catch (SQLException e) {
            System.err.println("Error finding year by id: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return null;
        }
    }

    /**
     * Obtiene todos los años pertenecientes a un proyecto específico.
     *
     * @param projectId ID del proyecto
     * @return Lista de años del proyecto (vacía si no hay o si projectId es
     * null)
     */
    public List<Year> findByProjectId(Long projectId) {
        if (projectId == null) {
            return new ArrayList<>(); // Retorna lista vacía en lugar de null
        }

        String sql = "SELECT id, project_id, year, tag FROM years WHERE project_id = ? ORDER BY year DESC";
        List<Year> years = new ArrayList<>();

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, projectId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Year y = Year.builder()
                        .id(rs.getLong("id"))
                        .projectId(rs.getLong("project_id"))
                        .YEAR(rs.getInt("year"))
                        .tag(rs.getString("tag"))
                        .build();
                years.add(y);
            }

            rs.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding years by projectId: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return years;
    }

    /**
     * Actualiza un año existente. Se permite modificar project_id, year y tag.
     */
    public boolean updateYear(Year year) {
        if (year == null || year.getId() == null || year.getProjectId() == null
                || year.getTag() == null || year.getTag().trim().isEmpty()) {
            return false;
        }

        String sql = "UPDATE years SET project_id = ?, year = ?, tag = ? WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, year.getProjectId());
            statement.setInt(2, year.getYEAR());
            statement.setString(3, year.getTag().trim());
            statement.setLong(4, year.getId());

            int affectedRows = statement.executeUpdate();
            statement.close();
            closeConnection();

            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error updating year: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    /**
     * Elimina un año por su ID.
     */
    public boolean deleteById(Long id) {
        if (id == null) {
            return false;
        }

        String sql = "DELETE FROM years WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();
            statement.close();
            closeConnection();

            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting year: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    /**
     * Verifica si existe un año con el ID dado.
     */
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }

        String sql = "SELECT COUNT(*) FROM years WHERE id = ?";

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
            System.err.println("Error checking if year exists by id: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    /**
     * Retorna el número total de años registrados.
     */
    public int getYearCount() {
        String sql = "SELECT COUNT(*) FROM years";

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
            System.err.println("Error getting year count: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return 0;
        }
    }

    // Métodos de utilidad para validación
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

    public boolean existsByProjectAndYear(Long projectId, int year) {
        if (projectId == null) {
            return false;
        }
        String sql = "SELECT COUNT(*) FROM years WHERE project_id = ? AND year = ?";
        try {
            connect();
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, projectId);
            stmt.setInt(2, year); // Cambiado a setInt
            ResultSet rs = stmt.executeQuery();
            boolean exists = rs.next() && rs.getInt(1) > 0;
            rs.close();
            stmt.close();
            closeConnection();
            return exists;
        } catch (SQLException e) {
            System.err.println("Error checking exists by project and year: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException ex) {
                // ignore
            }
            return false;
        }
    }
}
