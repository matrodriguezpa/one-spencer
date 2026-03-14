package matrodriguezpa.receiptmanager.dao;

import matrodriguezpa.receiptmanager.util.DBConectionUtil;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import matrodriguezpa.receiptmanager.model.Expense;

public class ExpenseDAO extends DBConectionUtil {

    public ExpenseDAO() {
        getDataBaseUrl();
    }

    // Crear tabla expenses si no existe (nota: si ya existe la tabla no se alterará automáticamente)
    public boolean createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS expenses ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "project_id INTEGER, "
                + "year_id INTEGER, "
                + "month_id INTEGER, "
                + "year INTEGER NOT NULL, "
                + "month INTEGER NOT NULL, "
                + "day INTEGER NOT NULL, "
                + "company TEXT, "
                + "amount REAL NOT NULL, "
                + "type TEXT, "
                + "matrix TEXT, "
                + "payment TEXT, "
                + "FOREIGN KEY (project_id) REFERENCES projects(id)"
                + ")";

        try {
            connect();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            closeConnection();
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating expenses table: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Insertar nuevo gasto y retornar el ID generado
    public Long createExpense(Expense expense) {
        if (expense == null) {
            return null;
        }

        // ahora insertamos project_id, year_id, month_id además de year/month/day y demás campos
        String sql = "INSERT INTO expenses (project_id, year_id, month_id, year, month, day, company, amount, type, matrix, payment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connect();

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                // 1 project_id
                if (expense.getProjectId() != null) {
                    stmt.setLong(1, expense.getProjectId());
                } else {
                    stmt.setNull(1, java.sql.Types.BIGINT);
                }

                // 2 year_id
                if (expense.getYearId() != null) {
                    stmt.setLong(2, expense.getYearId());
                } else {
                    stmt.setNull(2, java.sql.Types.BIGINT);
                }

                // 3 month_id
                if (expense.getMonthId() != null) {
                    stmt.setLong(3, expense.getMonthId());
                } else {
                    stmt.setNull(3, java.sql.Types.BIGINT);
                }

                // 4 year (int)
                stmt.setInt(4, expense.getYEAR());

                // 5 month (int)
                stmt.setInt(5, expense.getMONTH());

                // 6 day (int)
                stmt.setInt(6, expense.getDAY());

                // 7 company
                stmt.setString(7, expense.getCompany());

                // 8 amount
                stmt.setDouble(8, expense.getAmount());

                // 9 type
                stmt.setString(9, expense.getType());

                // 10 matrix
                stmt.setString(10, expense.getMatrix());

                // 11 payment
                stmt.setString(11, expense.getPayment());

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
            System.err.println("Error creating expense: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException ex) {
                /* noop */
            }
            return null;
        }
    }

    // Insertar nuevo gasto y actualizar su id si se genera (mantener compatibilidad)
    public Expense save(Expense expense) {
        if (expense == null) {
            return null;
        }

        Long generatedId = createExpense(expense);
        if (generatedId != null) {
            expense.setId(generatedId);
            return expense;
        }
        return null;
    }

    // Obtener todos los gastos
    public List<Expense> findAll() {
        String sql = "SELECT id, project_id, year_id, month_id, year, month, day, company, amount, type, matrix, payment FROM expenses ORDER BY id";

        List<Expense> expenses = new ArrayList<>();

        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                // obtener Longs de forma segura (nullable)
                Number pNum = (Number) rs.getObject("project_id");
                Long projectId = pNum != null ? pNum.longValue() : null;

                Number yIdNum = (Number) rs.getObject("year_id");
                Long yearId = yIdNum != null ? yIdNum.longValue() : null;

                Number mIdNum = (Number) rs.getObject("month_id");
                Long monthId = mIdNum != null ? mIdNum.longValue() : null;

                Expense exp = Expense.builder()
                        .id(rs.getLong("id"))
                        .ProjectId(projectId)
                        .yearId(yearId)
                        .MonthId(monthId)
                        .YEAR(rs.getInt("year"))
                        .MONTH(rs.getInt("month"))
                        .DAY(rs.getInt("day"))
                        .company(rs.getString("company"))
                        .amount(rs.getDouble("amount"))
                        .type(rs.getString("type"))
                        .matrix(rs.getString("matrix"))
                        .payment(rs.getString("payment"))
                        .build();
                expenses.add(exp);
            }

            rs.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding expenses: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return expenses;
    }

    // Buscar gasto por id
    public Expense findById(Long id) {
        if (id == null) {
            return null;
        }

        String sql = "SELECT id, project_id, year_id, month_id, year, month, day, company, amount, type, matrix, payment FROM expenses WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            Expense expense = null;

            if (rs.next()) {
                Number pNum = (Number) rs.getObject("project_id");
                Long projectId = pNum != null ? pNum.longValue() : null;

                Number yIdNum = (Number) rs.getObject("year_id");
                Long yearId = yIdNum != null ? yIdNum.longValue() : null;

                Number mIdNum = (Number) rs.getObject("month_id");
                Long monthId = mIdNum != null ? mIdNum.longValue() : null;

                expense = Expense.builder()
                        .id(rs.getLong("id"))
                        .ProjectId(projectId)
                        .yearId(yearId)
                        .MonthId(monthId)
                        .YEAR(rs.getInt("year"))
                        .MONTH(rs.getInt("month"))
                        .DAY(rs.getInt("day"))
                        .company(rs.getString("company"))
                        .amount(rs.getDouble("amount"))
                        .type(rs.getString("type"))
                        .matrix(rs.getString("matrix"))
                        .payment(rs.getString("payment"))
                        .build();
            }

            rs.close();
            statement.close();
            closeConnection();

            return expense;

        } catch (SQLException e) {
            System.err.println("Error finding expense by id: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return null;
        }
    }

    // Buscar gastos por project_id
    public List<Expense> findByProjectId(Long projectId) {
        List<Expense> expenses = new ArrayList<>();

        if (projectId == null) {
            return expenses;
        }

        String sql = "SELECT id, project_id, year_id, month_id, year, month, day, company, amount, type, matrix, payment FROM expenses WHERE project_id = ? ORDER BY year, month, day";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, projectId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Number pNum = (Number) rs.getObject("project_id");
                Long pId = pNum != null ? pNum.longValue() : null;

                Number yIdNum = (Number) rs.getObject("year_id");
                Long yearId = yIdNum != null ? yIdNum.longValue() : null;

                Number mIdNum = (Number) rs.getObject("month_id");
                Long monthId = mIdNum != null ? mIdNum.longValue() : null;

                Expense exp = Expense.builder()
                        .id(rs.getLong("id"))
                        .ProjectId(pId)
                        .yearId(yearId)
                        .MonthId(monthId)
                        .YEAR(rs.getInt("year"))
                        .MONTH(rs.getInt("month"))
                        .DAY(rs.getInt("day"))
                        .company(rs.getString("company"))
                        .amount(rs.getDouble("amount"))
                        .type(rs.getString("type"))
                        .matrix(rs.getString("matrix"))
                        .payment(rs.getString("payment"))
                        .build();
                expenses.add(exp);
            }

            rs.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding expenses by project id: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return expenses;
    }

    // Buscar gastos por compañía
    public List<Expense> findByCompany(String company) {
        List<Expense> expenses = new ArrayList<>();

        if (company == null || company.trim().isEmpty()) {
            return expenses;
        }

        String sql = "SELECT id, project_id, year_id, month_id, year, month, day, company, amount, type, matrix, payment FROM expenses WHERE company LIKE ? ORDER BY year, month, day";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + company.trim() + "%");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Number pNum = (Number) rs.getObject("project_id");
                Long projectId = pNum != null ? pNum.longValue() : null;

                Number yIdNum = (Number) rs.getObject("year_id");
                Long yearId = yIdNum != null ? yIdNum.longValue() : null;

                Number mIdNum = (Number) rs.getObject("month_id");
                Long monthId = mIdNum != null ? mIdNum.longValue() : null;

                Expense exp = Expense.builder()
                        .id(rs.getLong("id"))
                        .ProjectId(projectId)
                        .yearId(yearId)
                        .MonthId(monthId)
                        .YEAR(rs.getInt("year"))
                        .MONTH(rs.getInt("month"))
                        .DAY(rs.getInt("day"))
                        .company(rs.getString("company"))
                        .amount(rs.getDouble("amount"))
                        .type(rs.getString("type"))
                        .matrix(rs.getString("matrix"))
                        .payment(rs.getString("payment"))
                        .build();
                expenses.add(exp);
            }

            rs.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding expenses by company: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return expenses;
    }

    // Actualizar gasto
    public boolean updateExpense(Expense expense) {
        if (expense == null || expense.getId() == null) {
            return false;
        }

        String sql = "UPDATE expenses SET project_id = ?, year_id = ?, month_id = ?, year = ?, month = ?, day = ?, company = ?, amount = ?, type = ?, matrix = ?, payment = ? WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);

            // 1 project_id
            if (expense.getProjectId() != null) {
                statement.setLong(1, expense.getProjectId());
            } else {
                statement.setNull(1, java.sql.Types.BIGINT);
            }

            // 2 year_id
            if (expense.getYearId() != null) {
                statement.setLong(2, expense.getYearId());
            } else {
                statement.setNull(2, java.sql.Types.BIGINT);
            }

            // 3 month_id
            if (expense.getMonthId() != null) {
                statement.setLong(3, expense.getMonthId());
            } else {
                statement.setNull(3, java.sql.Types.BIGINT);
            }

            // 4 year
            statement.setInt(4, expense.getYEAR());

            // 5 month
            statement.setInt(5, expense.getMONTH());

            // 6 day
            statement.setInt(6, expense.getDAY());

            // 7 company
            statement.setString(7, expense.getCompany());

            // 8 amount
            statement.setDouble(8, expense.getAmount());

            // 9 type
            statement.setString(9, expense.getType());

            // 10 matrix
            statement.setString(10, expense.getMatrix());

            // 11 payment
            statement.setString(11, expense.getPayment());

            // 12 id (where)
            statement.setLong(12, expense.getId());

            int affectedRows = statement.executeUpdate();
            statement.close();
            closeConnection();

            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error updating expense: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Eliminar gasto por id
    public boolean deleteById(Long id) {
        if (id == null) {
            return false;
        }

        String sql = "DELETE FROM expenses WHERE id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            int affectedRows = statement.executeUpdate();
            statement.close();
            closeConnection();

            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting expense: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Verificar si existe gasto por id
    public boolean existsById(Long id) {
        if (id == null) {
            return false;
        }

        String sql = "SELECT COUNT(*) FROM expenses WHERE id = ?";

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
            System.err.println("Error checking if expense exists: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return false;
        }
    }

    // Obtener el total de gastos
    public int getExpenseCount() {
        String sql = "SELECT COUNT(*) FROM expenses";

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
            System.err.println("Error getting expense count: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return 0;
        }
    }

    // Obtener suma total de gastos
    public double getTotalAmount() {
        String sql = "SELECT SUM(amount) FROM expenses";

        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            double total = 0.0;
            if (result.next()) {
                total = result.getDouble(1);
            }

            result.close();
            statement.close();
            closeConnection();

            return total;

        } catch (SQLException e) {
            System.err.println("Error getting total amount: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return 0.0;
        }
    }

    // Obtener suma total de gastos por proyecto
    public double getTotalAmountByProject(Long projectId) {
        if (projectId == null) {
            return 0.0;
        }

        String sql = "SELECT SUM(amount) FROM expenses WHERE project_id = ?";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, projectId);

            ResultSet result = statement.executeQuery();

            double total = 0.0;
            if (result.next()) {
                total = result.getDouble(1);
            }

            result.close();
            statement.close();
            closeConnection();

            return total;

        } catch (SQLException e) {
            System.err.println("Error getting total amount by project: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
            return 0.0;
        }
    }

    public List<Expense> findByMonthId(Long monthId) {
        List<Expense> expenses = new ArrayList<>();

        if (monthId == null) {
            return expenses;
        }

        String sql = "SELECT id, project_id, year_id, month_id, year, month, day, company, amount, type, matrix, payment FROM expenses WHERE month_id = ? ORDER BY year, month, day";

        try {
            connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, monthId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Number pNum = (Number) rs.getObject("project_id");
                Long projectId = pNum != null ? pNum.longValue() : null;

                Number yIdNum = (Number) rs.getObject("year_id");
                Long yearId = yIdNum != null ? yIdNum.longValue() : null;

                Number mIdNum = (Number) rs.getObject("month_id");
                Long monthIdFromDb = mIdNum != null ? mIdNum.longValue() : null; // but we could just use monthId param

                Expense exp = Expense.builder()
                        .id(rs.getLong("id"))
                        .ProjectId(projectId)
                        .yearId(yearId)
                        .MonthId(monthIdFromDb)
                        .YEAR(rs.getInt("year"))
                        .MONTH(rs.getInt("month"))
                        .DAY(rs.getInt("day"))
                        .company(rs.getString("company"))
                        .amount(rs.getDouble("amount"))
                        .type(rs.getString("type"))
                        .matrix(rs.getString("matrix"))
                        .payment(rs.getString("payment"))
                        .build();
                expenses.add(exp);
            }

            rs.close();
            statement.close();
            closeConnection();

        } catch (SQLException e) {
            System.err.println("Error finding expenses by month id: " + e.getMessage());
            try {
                closeConnection();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }

        return expenses;
    }

    /**
     * Validates that the date is in valid day/month range.
     *
     * @param day
     * @param month
     * @throws IllegalArgumentException if invalid date
     */
    public static void validateDate(int month, int day) {
        if (month == 2 && day > 29) {
            throw new IllegalArgumentException("Invalid date: February cannot have more than 29 days.");
        }
        if (day < 1 || day > 31 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    /**
     * Validates that the amount is non-negative.
     *
     * @param amt
     * @throws IllegalArgumentException if amount < 0
     */
    public static void validateAmount(double amt) {
        if (amt < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
    }
}
