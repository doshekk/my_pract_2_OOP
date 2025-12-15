package com.tourism;

import java.util.List;

import com.google.inject.Inject;

/**
 * Контролер для керування запитами до бази даних та повернення даних моделі
 */
public class TourismController {
    private BookingService bookingService;

    /**
     * Конструктор з впровадженням залежності від сервісу бронювання
     *
     * @param bookingService сервіс для роботи з даними бронювання
     */
    @Inject
    public TourismController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Повертає список всіх позицій бронювання з бази даних
     *
     * @return список позицій бронювання
     */
    public List<BookingItem> getAllBookingItems() {
        return bookingService.getAllBookingItems();
    }
}
