package com.tourism;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.inject.Inject;

/**
 * Сервіс для збереження даних про позиції бронювання в базу даних
 */
public class BookingService {
    private Connection connection;

    /**
     * Конструктор з впровадженням залежності від драйвера бази даних.
     *
     * @param connection з'єднання з базою даних
     */
    @Inject
    public BookingService(Connection connection) {
        this.connection = connection;
    }

    /**
     * Зберігає позицію бронювання в таблицю booking_items
     *
     * @param bookingItem позиція бронювання для збереження
     */
    public void saveBookingItem(BookingItem bookingItem) {
        String sql = "INSERT INTO booking_items (room_type, price) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookingItem.getRoomType());
            statement.setDouble(2, bookingItem.getPrice());
            statement.executeUpdate();
            System.out.println("✓ Позиція бронювання збережена в базу даних: " + 
                             bookingItem.getRoomType() + " | " + bookingItem.getPrice() + "$");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save booking item", e);
        }
    }
}
