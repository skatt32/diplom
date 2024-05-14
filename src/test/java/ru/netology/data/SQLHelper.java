package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static final String DBSQLUrl = System.getProperty("datasource.url");
    private static final String DBPostgresQLUrl = System.getProperty("datasource.url2");
    private static final String username = System.getProperty("username");
    private static final String password = System.getProperty("password");

    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnMySql() throws SQLException {
        return DriverManager.getConnection(DBSQLUrl,username,password);
    }

    private static Connection getConnPostgreSQL() throws SQLException {
        return DriverManager.getConnection(DBPostgresQLUrl,username,password);
    }


    @SneakyThrows
    public static String getLastPayUserStatusMySQL() {
        Thread.sleep(10000);
        var payStatus = "SELECT status FROM payment_entity order by created desc LIMIT 1";
        var conn = getConnMySql();
        var result = runner.query(conn, payStatus, new ScalarHandler<String>());
        return result;

    }

    @SneakyThrows
    public static int getLastPayUserAmountMySQL() {
        Thread.sleep(10000);
        var amount = "SELECT amount FROM payment_entity order by created desc LIMIT 1";
        var conn = getConnMySql();
        var result = runner.query(conn, amount, new ScalarHandler<Integer>());
        return result;

    }

    @SneakyThrows
    public static String getLastPayOnCreditUserStatusMySQL() {
        Thread.sleep(10000);
        var creditStatus = "SELECT status FROM credit_request_entity order by created desc LIMIT 1";
        var conn = getConnMySql();
        var result = runner.query(conn, creditStatus, new ScalarHandler<String>());
        return result;

    }

    @SneakyThrows
    public static String getLastPayUserStatusPostgreSQL() {
        Thread.sleep(10000);
        var payStatus = "SELECT status FROM payment_entity order by created desc LIMIT 1";
        var conn = getConnPostgreSQL();
        var result = runner.query(conn, payStatus, new ScalarHandler<String>());
        return result;

    }

    @SneakyThrows
    public static int getLastPayUserAmountPostgreSQL() {
        Thread.sleep(10000);
        var amount = "SELECT amount FROM payment_entity order by created desc LIMIT 1";
        var conn = getConnPostgreSQL();
        var result = runner.query(conn, amount, new ScalarHandler<Integer>());
        return result;

    }

    @SneakyThrows
    public static String getLastPayOnCreditUserStatusPostgreSQL() {
        Thread.sleep(10000);
        var creditStatus = "SELECT status FROM credit_request_entity order by created desc LIMIT 1";
        var conn = getConnPostgreSQL();
        var result = runner.query(conn, creditStatus, new ScalarHandler<String>());
        return result;

    }
}
