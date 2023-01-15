package it.thundyy.multieconomy.database;

import it.thundyy.multieconomy.database.enums.DatabaseResponse;
import it.thundyy.multieconomy.database.enums.Query;
import it.thundyy.multieconomy.database.helper.DataHelper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RequiredArgsConstructor
public class DatabaseConnector {
    private final String database;
    private final String password;
    private final String username;

    @Getter
    private Connection connection;

    public DatabaseResponse connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return DatabaseResponse.ERROR;
        }

        return DatabaseResponse.OK;
    }

    public void createTables() {
        try (Connection connection = getConnection()) {
            DataHelper.prepareStatement(connection, Query.CREATE_TABLE.getQuery()).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
