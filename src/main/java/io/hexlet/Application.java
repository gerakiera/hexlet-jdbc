package io.hexlet;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws SQLException {

        try (var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {

            var sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";

            try (var statement = conn.createStatement()) {
                statement.execute(sql);
            }
            var sql2 = "INSERT INTO users (username, phone) VALUES (?, ?)";
            try (var preparedStatement = conn.prepareStatement(sql2)) {
                preparedStatement.setString(1, "Tommy");
                preparedStatement.setString(2, "123456789");
                preparedStatement.executeUpdate();
                preparedStatement.setString(1, "Maria");
                preparedStatement.setString(2, "987654321");
                preparedStatement.executeUpdate();
                preparedStatement.setString(1, "Kris");
                preparedStatement.setString(2, "999999999");
                preparedStatement.executeUpdate();
            }
            var sql4 = "DELETE FROM users WHERE username = 'Tommy'";
            try (var preparedStatement = conn.prepareStatement(sql4)) {
                preparedStatement.executeUpdate();
            }
            var sql3 = "SELECT * FROM users";
            try (var statement3 = conn.createStatement()) {
                var resultSet = statement3.executeQuery(sql3);

                while (resultSet.next()) {
                    System.out.println(resultSet.getString("username"));
                    System.out.println(resultSet.getString("phone"));
                }
            }
        }
    }
}
