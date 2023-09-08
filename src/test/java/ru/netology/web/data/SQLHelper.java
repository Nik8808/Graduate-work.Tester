package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static String url = System.getProperty("db.url");
    private static String username = System.getProperty("db.username");
    private static String password = System.getProperty("db.password");


    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection(url, username, password);
    }

    @SneakyThrows
    public static String purchaseStatusCard() {
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC";
        var conn = getConn();
        return runner.query(conn, codeSQL, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static String purchaseStatusCredit() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC";
        var conn = getConn();
        return runner.query(conn, codeSQL, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM payment_entity");
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
    }
}
