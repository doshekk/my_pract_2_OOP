package com.tourism;

/**
 * Клас для збереження позиції бронювання
 */
public class BookingItem {
    private String roomType;
    private double price;

    public BookingItem(String roomType, double price) {
        this.roomType = roomType;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }
}
