package com.tourism;

/**
 * Інтерфейс для бронювання
 */
public interface IBookingPlacement {
    void makeReservation(String roomType);
    void cancelReservation();
}
