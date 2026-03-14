package matrodriguezpa.receiptmanager.dao;

import matrodriguezpa.receiptmanager.Util.DBConectionUtil;
import matrodriguezpa.receiptmanager.model.Month;
import matrodriguezpa.receiptmanager.model.Year;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonthDAO extends DBConectionUtil {

    public MonthDAO() {
        getDataBaseUrl();
    }

    // Crear tabla months si no existe
    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS months ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "year_id INTEGER NOT NULL, "
                + "month INTEGER NOT NULL, "
                + "FOREIGN KEY (year_id) REFERENCES years(id), "
                + "UNIQUE(year_id, month)"
                + ")";

        try {
            connect();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            closeConnection();
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating months table: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Insertar un nuevo mes y retornar el ID generado
    public Long createMonth(Month month) {
        if (month == null || month.getProjectId() == null || month.getProjectId().getId() == null) {
            return null;
        }

        String sql = "INSERT INTO months (year_id, month) VALUES (?, ?)";

        try {
            connect();

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setLong(1, month.getProjectId().getId());
                stmt.setInt(2, month.getMONTH());
                int affected = stmt.executeUpdate();

                if (affected == 0) {
                    closeConnection();
                    return null;
                }
            }

            Long generatedId = getLastInsertRowId();
            closeConnection();
            return generatedId;

        } catch (SQLException e) {
            System.err.println("Error creating month: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException ex) {
                // noop
            }
            return null;
        }
    }

    // Insertar un nuevo mes y actualizar su id (método de conveniencia)
    public Month save(Month month) {
        if (month == null) {
            return null;
        }

        Long generatedId = createMonth(month);
        if (generatedId != null) {
            month.setId(generatedId);
            return month;
        }
        return null;
    }

    // Obtener todos los meses
    public List<Month> findAll() {
        String sql = "SELECT id, year_id, month FROM months ORDER BY year_id, month";
        List<Month> months = new ArrayList<>();

        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Month month = mapResultSetToMonth(rs);
                months.add(month);
            }

            rs.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding all months: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return months;
    }

    // Buscar mes por id
    public Month findById(Long id) {
        if (id == null) {
            return null;
        }

        String sql = "SELECT id, year_id, month FROM months WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            Month month = null;

            if (rs.next()) {
                month = mapResultSetToMonth(rs);
            }

            rs.close();
            statement.close();
            closeConnection();
            return month;

        } catch (SQLException e) {
            System.err.println("Error finding month by id: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return null;
        }
    }

    // Buscar meses por year_id
    public List<Month> findByYearId(Long yearId) {
        List<Month> months = new ArrayList<>();
        if (yearId == null) {
            return months;
        }

        String sql = "SELECT id, year_id, month FROM months WHERE year_id = ? ORDER BY month";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, yearId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Month month = mapResultSetToMonth(rs);
                months.add(month);
            }

            rs.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding months by year id: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return months;
    }

    // Actualizar un mes existente
    public boolean updateMonth(Month month) {
        if (month == null || month.getId() == null || month.getProjectId() == null || month.getProjectId().getId() == null) {
            return false;
        }

        String sql = "UPDATE months SET year_id = ?, month = ? WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, month.getProjectId().getId());
            statement.setInt(2, month.getMONTH());
            statement.setLong(3, month.getId());

            int affectedRows = statement.executeUpdate();
            statement.close();
            closeConnection();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error updating month: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Eliminar mes por id
    public boolean deleteById(Long id) {
        if (id == null) {
            return false;
        }

        String sql = "DELETE FROM months WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();
            statement.close();
            closeConnection();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting month: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Verificar si existe un mes por id
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }

        String sql = "SELECT COUNT(*) FROM months WHERE id = ?";

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
            System.err.println("Error checking if month exists: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Obtener el total de meses
    public int getMonthCount() {
        String sql = "SELECT COUNT(*) FROM months";

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
            System.err.println("Error getting month count: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return 0;
        }
    }

    // Método auxiliar para mapear un ResultSet a un objeto Month
    private Month mapResultSetToMonth(ResultSet rs) throws SQLException {
        // Se crea un objeto Year solo con el ID (para establecer la relación)
        Year year = new Year();
        year.setId(rs.getLong("year_id"));

        return Month.builder()
                .id(rs.getLong("id"))
                .projectId(year)
                .MONTH(rs.getInt("month"))
                .build();
    }

    // Validación de mes (1-12)
    public static void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }
    }

    public static String getMonthName(int monthNumber) {
        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        if (monthNumber >= 1 && monthNumber <= 12) {
            return months[monthNumber - 1];
        } else {
            return "Unknown Month";
        }
    }
    
}
