package com.tourism;

/**
 * Клас для представлення туриста
 */
public class Tourist extends TravelSubject {
    private Booking booking;

    public Tourist(String name) {
        super(name);
    }

    @Override
    public void showRole() {
        System.out.println(name + " — Турист.");
    }

    @Override
    public void makeReservation(String roomType) {
        System.out.println(name + " бронює номер: " + roomType);
    }

    @Override
    public void cancelReservation() {
        System.out.println(name + " скасовує бронювання.");
    }

    public void setBooking(Booking booking) {  // Агрегація
        this.booking = booking;
    }

    public void changeDates(String newDates) {
        System.out.println(name + " змінює дати: " + newDates);
    }
}
