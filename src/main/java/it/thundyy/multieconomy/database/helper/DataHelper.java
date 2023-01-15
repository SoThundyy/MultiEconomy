package it.thundyy.multieconomy.database.helper;

import lombok.experimental.UtilityClass;

import javax.annotation.Nullable;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@UtilityClass
public class DataHelper {

    public CachedRowSet executeQuery(Connection connection, String query, Object... args) {
        try (PreparedStatement statement = prepareStatement(connection, query, args);
             CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet()) {
            if (args != null && args.length != 0)
                for (int i = 1; i <= args.length; i++)
                    statement.setObject(i, args[i - 1]);

            cachedRowSet.populate(statement.executeQuery());
            return cachedRowSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void flush(Connection connection, String query, @Nullable Object... fields) {
        try (PreparedStatement preparedStatement = prepareStatement(connection, query, fields)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(Connection connection, String query, @Nullable Object... fields) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);

            if (fields != null) {
                for (int i = 0; i < fields.length; i++) {
                    statement.setObject(i + 1, fields[i]);
                }
            }
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
