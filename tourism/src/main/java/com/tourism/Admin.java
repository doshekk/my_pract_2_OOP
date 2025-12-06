package com.tourism;

/**
 * Клас для представлення адміністратора готелю
 */
public class Admin extends TravelSubject {
    public Admin(String name) {
        super(name);
    }

    @Override
    public void showRole() {
        System.out.println(name + " — Адміністратор готелю.");
    }

    @Override
    public void makeReservation(String roomType) {
        System.out.println("Адміністратор отримує запит на бронювання: " + roomType);
    }

    @Override
    public void cancelReservation() {
        System.out.println("Адміністратор скасовує бронювання в системі.");
    }

    public final void sendVoucher() {   // фінальний метод
        System.out.println("Адміністратор надсилає ваучер туристу.");
    }
}
