package com.tourism;

import com.google.inject.Inject;

/**
 * Клас для управління бронюванням (композиція з BookingItem та агрегація з BookingService)
 */
public class Booking {
    private BookingItem item; // композиція
    private PaymentService payment;
    private BookingService bookingService;

    /**
     * Конструктор з впровадженням залежності від сервісу збереження даних
     *
     * @param bookingService сервіс для збереження бронювання
     */
    @Inject
    public Booking(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Створює позицію бронювання та сервіс оплати
     *
     * @param roomType тип номера
     * @param price ціна
     */
    public void initialize(String roomType, double price) {
        this.item = new BookingItem(roomType, price);
        this.payment = new PaymentService();
    }

    /**
     * Підтверджує бронювання та зберігає його в базу даних
     */
    public void confirm() {
        System.out.println("Підтвердження бронювання...");
        payment.processPayment(item.getPrice());
        // Збереження позиції бронювання в базу даних
        bookingService.saveBookingItem(this.item);
    }

    /**
     * Виводить інформацію про бронювання
     */
    public void showBooking() {
        System.out.println("Бронювання: " + item.getRoomType() + " | " + item.getPrice() + "$");
    }

    public BookingItem getItem() {
        return item;
    }
}
