package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().
                getResourceAsStream("app.properties")) {
            config.load(in);

        }

        try (TableEditor tableEditor = new TableEditor(config)) {
            String tableName = "book";
            tableEditor.createTable(tableName);
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.addColumn(tableName, "author", "varchar(255)");
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.renameColumn(tableName, "author", "author_name");
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.dropColumn(tableName, "author_name");
            System.out.println(tableEditor.getTableScheme(tableName));
            tableEditor.dropTable(tableName);
            try {
                System.out.println(tableEditor.getTableScheme(tableName));
            } catch (SQLException e) {
                System.out.println("Таблица была удалена.");
            }
        }
    }

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver_class"));
        String url = properties.getProperty("url");
        String user = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, user, password);
    }

    public void sqlExecute(String querry) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(querry);
        }
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format("create table if not exists %s("
                    + "id serial primary key)", tableName);
        sqlExecute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("drop table %s", tableName);
        sqlExecute(sql);
    }

    public void addColumn(String tableName, String columnName, String columnDefinition) throws SQLException {
        String sql = String.format("alter table %s add column %s %s",
                    tableName, columnName, columnDefinition);
        sqlExecute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("alter table %s drop column %s", tableName, columnName);
        sqlExecute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format("alter table %s rename column %s to %s",
                    tableName, columnName, newColumnName);
        sqlExecute(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}