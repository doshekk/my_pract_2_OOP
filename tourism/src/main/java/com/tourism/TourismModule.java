package com.tourism;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * Google Guice модуль для впровадження залежностей та налаштування SQLite бази даних
 */
public class TourismModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(String.class)
            .annotatedWith(Names.named("JDBC URL"))
            .toInstance("jdbc:sqlite:target/tourism.db");
    }

    /**
     * Постачальник для створення та налаштування з'єднання з SQLite базою даних
     *
     * @param url JDBC URL для підключення до бази даних
     * @return з'єднання з базою даних SQLite
     */
    @Provides
    @Singleton
    Connection provideConnection(@Named("JDBC URL") String url) {
        try {
            Connection connection = DriverManager.getConnection(url);
            createTableIfNotExists(connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    /**
     * Створює таблицю для збереження даних про позиції бронювання
     *
     * @param connection з'єднання з базою даних
     */
    private void createTableIfNotExists(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS booking_items (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "room_type TEXT NOT NULL, " +
                                "price REAL NOT NULL, " +
                                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create table", e);
        }
    }
}
