package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class SQLHelper {

    private static final String url = System.getProperty("datasource.url");
    private static final String username = System.getProperty("username");
    private static final String password = System.getProperty("password");
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }


    @SneakyThrows
    public static String getPaymentStatus() {
        var codesSQL = "SELECT status FROM payment_entity;";
        var result = runner.query( codesSQL, new ScalarHandler<String>());
        return result;
    }
    @SneakyThrows
    public static String getCreditStatus() {
        var codesSQL = "SELECT status FROM credit_request_entity;";
        var result = runner.query(codesSQL, new ScalarHandler<String>());
        return result;
    }
    @SneakyThrows
    public static String getOrderCount() {
        Long count;
        var codesSQL = " SELECT COUNT(*) FROM order_entity;";
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(url, username, password);
            count = runner.query(conn, codesSQL, new ScalarHandler<>());

        return Long.toString(count);

    }
    @SneakyThrows
    private static String getData(String query) {
        String data;
        var runner = new QueryRunner();
        var conn = DriverManager.getConnection(url, username, password);
            data = runner.query(conn, query, new ScalarHandler<>());

        return data;

    }
    @SneakyThrows
    public static void cleanDataBase() {

        var runner = new QueryRunner();

        var conn = DriverManager.getConnection(url, username, password);

            runner.update(conn, "DELETE FROM credit_request_entity");
            runner.update(conn, "DELETE FROM order_entity");
            runner.update(conn, "DELETE FROM payment_entity");
            System.out.println("SQL exception in cleanDataBase");
        }
    }

