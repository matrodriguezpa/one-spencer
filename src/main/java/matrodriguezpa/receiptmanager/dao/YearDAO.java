package matrodriguezpa.receiptmanager.dao;

import matrodriguezpa.receiptmanager.Util.DBConectionUtil;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import matrodriguezpa.receiptmanager.model.Project;

import matrodriguezpa.receiptmanager.model.Year;

public class YearDAO extends DBConectionUtil {

    public YearDAO() {
        getDataBaseUrl();
    }

    // Crear tabla projects si no existe
    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS years ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "tag TEXT NOT NULL UNIQUE"
                + ")";

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

    // Crear un nuevo proyecto y retornar el ID generado
    public Long createYear(Year year) {
        if (year == null || year.getTag() == null || year.getTag().trim().isEmpty()) {
            return null;
        }

        String sql = "INSERT INTO years (tag) VALUES (?)";

        try {
            connect();

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, year.getTag().trim());
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
            System.err.println("Error creating year: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException ex) {
                /* noop */
            }
            return null;
        }
    }

    // Insertar nuevo proyecto y actualizar su id si se genera (mantener compatibilidad)
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

    // Obtener todos los proyectos
    public List<Year> findAll() {
        String sql = "SELECT id, tag FROM years ORDER BY tag";

        List<Year> years = new ArrayList<>();

        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Year p = Year.builder()
                        .id(rs.getLong("id"))
                        .tag(rs.getString("tag"))
                        .build();
                years.add(p);
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

    // Buscar proyecto por id
    public Year findById(Long id) {
        if (id == null) {
            return null;
        }

        String sql = "SELECT id, tag FROM years WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            Year year = null;

            if (rs.next()) {
                year = Year.builder()
                        .id(rs.getLong("id"))
                        .tag(rs.getString("tag"))
                        .build();
            }

            rs.close();
            statement.close();
            closeConnection();

            return year;

        } catch (SQLException e) {
            System.err.println("Error finding years by id: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return null;
        }
    }

    // Actualizar proyecto
    public boolean updateYear(Year year) {
        if (year == null || year.getId() == null
                || year.getTag() == null || year.getTag().trim().isEmpty()) {
            return false;
        }

        String sql = "UPDATE years SET tag = ? WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, year.getTag().trim());
            statement.setLong(2, year.getId());

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

    // Eliminar proyecto por id
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

    // Verificar si existe proyecto por id
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

    // Obtener el total de proyectos
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

    // Obtener proyectos con información de gastos
    public List<Year> findAllWithExpenseInfo() {
        String sql = "SELECT p.id, p.tag, "
                + "COUNT(e.id) as expense_count, "
                + "COALESCE(SUM(e.amount), 0) as total_amount "
                + "FROM years p "
                + "LEFT JOIN expenses e ON p.id = e.year_id "
                + "GROUP BY p.id, p.tag "
                + "ORDER BY p.tag";

        List<Year> years = new ArrayList<>();

        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Year p = Year.builder()
                        .id(rs.getLong("id"))
                        .tag(rs.getString("tag"))
                        .build();

                // Nota: Si tu modelo Project tiene campos para expense_count y total_amount,
                // puedes agregarlos aquí. De lo contrario, esta información se puede obtener
                // por separado cuando se necesite.
                years.add(p);
            }

            rs.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding years with expense info: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return years;
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

    public boolean existsByProjectAndYear(Long projectId, int year) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean existsByProjectAndYear(Project project, int yearValue) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
