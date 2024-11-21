package com.xadmin.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.usermanagement.bean.User;

public class UserDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_USERS_SQL = "INSERT INTO users (nrp, name, email, alamat, jurusan, gender) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_USER_BY_ID = "SELECT id, nrp, name, email, alamat, jurusan, gender FROM users WHERE id = ?";

    private static final String SELECT_ALL_USERS = "SELECT id, nrp, name, email, alamat, jurusan, gender FROM users";

    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?";

    private static final String UPDATE_USERS_SQL = "UPDATE users SET nrp = ?, name = ?, email = ?, alamat = ?, jurusan = ?, gender = ? WHERE id = ?";


    public void UserDAO() {}
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
        public void insertUser(User user) throws SQLException {
            System.out.println(INSERT_USERS_SQL);
            // try-with-resource statement will auto close the connection.
            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
                preparedStatement.setString(1, user.getNrp());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getAlamat());
                preparedStatement.setString(5, user.getJurusan());
                preparedStatement.setString(6, user.getGender());
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                printSQLException(e);
            }
        }     
        
        public User selectUser(int id) {
            User user = null;
            // Step 1: Establishing a Connection
            try (Connection connection = getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();

                // Step 4: Process the ResultSet object.
                while (rs.next()) {
                	int pk = rs.getInt(id);
                    String nrp = rs.getString("nrp");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String alamat = rs.getString("alamat");
                    String jurusan = rs.getString("jurusan");
                    String gender = rs.getString("gender");
                    user = new User(pk, nrp, name, email, alamat, jurusan, gender);
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return user;
        }
        
        public List < User > selectAllUsers() {

            // using try-with-resources to avoid closing resources (boiler plate code)
            List < User > users = new ArrayList < > ();
            // Step 1: Establishing a Connection
            try (Connection connection = getConnection();

                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();

                // Step 4: Process the ResultSet object.
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nrp = rs.getString("nrp");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String alamat = rs.getString("alamat");
                    String jurusan = rs.getString("jurusan");
                    String gender = rs.getString("gender");
                    users.add(new User(id, nrp, name, email, alamat, jurusan, gender));
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            return users;
        }
        
        public boolean deleteUser(int id) throws SQLException {
            boolean rowDeleted;
            try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
                statement.setInt(1, id);
                rowDeleted = statement.executeUpdate() > 0;
            }
            return rowDeleted;
        }
        
        public boolean updateUser(User user) throws SQLException {
            boolean rowUpdated = false;
            try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            	statement.setString(1, user.getNrp());
            	statement.setString(2, user.getName());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getAlamat());
                statement.setString(5, user.getJurusan());
                statement.setString(6, user.getGender());
                statement.setInt(7, user.getId());

             // Debugging: Log the query with parameters
                System.out.println("Executing Update Query:");
                System.out.println(statement);

                // Execute the update and log the result
                int rowsUpdated = statement.executeUpdate();
                System.out.println("Rows Updated: " + rowsUpdated);
                
                rowUpdated = rowsUpdated > 0;
            } catch (SQLException e) {
                System.err.println("Error while updating user with ID " + user.getId());
                printSQLException(e);
            }
            return rowUpdated;
        }
        
        public void deleteAllUsers() throws SQLException {
            String sql = "DELETE FROM users"; // Replace 'users' with your table name
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeUpdate();
            }
        }

        
        private void printSQLException(SQLException ex) {
            for (Throwable e: ex) {
                if (e instanceof SQLException) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while (t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
       
}

