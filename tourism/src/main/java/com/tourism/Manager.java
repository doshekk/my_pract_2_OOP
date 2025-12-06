package com.tourism;

/**
 * Клас для представлення менеджера турфірми
 */
public class Manager extends TravelSubject {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void showRole() {
        System.out.println(name + " — Менеджер турфірми.");
    }

    @Override
    public void makeReservation(String roomType) {
        System.out.println("Менеджер " + name + " підбирає пакет з номером: " + roomType);
    }

    @Override
    public void cancelReservation() {
        System.out.println("Менеджер " + name + " скасовує бронювання клієнта.");
    }

    public void approvePackage() {
        System.out.println("Менеджер погоджує умови пакету.");
    }
}
