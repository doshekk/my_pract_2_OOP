package com.tourism;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Клас для запуску імітації системи туризму з використанням Google Guice для впровадження залежностей через setter
 */
public class App {
    public static void main(String[] args) {
        // Створення ін'єктора з модулем TourismModule для управління залежностями
        Injector injector = Guice.createInjector(new TourismModule());

        // Учасники системи
        Tourist tourist1 = new Tourist("Ростислав");
        Tourist tourist2 = new Tourist("Марія");
        Tourist tourist3 = new Tourist("Іван");
        Manager manager = new Manager("Ірина");
        Admin admin = new Admin("Олександр");

        System.out.println("=== СТАРТ СИСТЕМИ ТУРИЗМУ (впровадження через setter) ===");

        tourist1.showRole();
        tourist2.showRole();
        tourist3.showRole();
        manager.showRole();
        admin.showRole();

        System.out.println("\n--- Бронювання 1: Ростислав ---");
        tourist1.makeReservation("Deluxe Room");
        manager.approvePackage();
        admin.makeReservation("Deluxe Room");

        // Створення Booking через getInstance (Guice впроважде залежність через setter)
        Booking booking1 = injector.getInstance(Booking.class);
        booking1.initialize("Deluxe Room", 120);
        booking1.showBooking();
        booking1.confirm();
        tourist1.setBooking(booking1);

        System.out.println("\n--- Бронювання 2: Марія ---");
        tourist2.makeReservation("Standard Room");
        manager.approvePackage();
        admin.makeReservation("Standard Room");

        Booking booking2 = injector.getInstance(Booking.class);
        booking2.initialize("Standard Room", 80);
        booking2.showBooking();
        booking2.confirm();
        tourist2.setBooking(booking2);

        System.out.println("\n--- Бронювання 3: Іван ---");
        tourist3.makeReservation("Suite Room");
        manager.approvePackage();
        admin.makeReservation("Suite Room");

        Booking booking3 = injector.getInstance(Booking.class);
        booking3.initialize("Suite Room", 200);
        booking3.showBooking();
        booking3.confirm();
        tourist3.setBooking(booking3);

        System.out.println("\n--- Підтвердження ---");
        admin.sendVoucher();

        System.out.println("\n--- Зміна дат ---");
        tourist1.changeDates("12–15 серпня");
        tourist2.changeDates("20–25 серпня");
        tourist3.changeDates("1–7 вересня");

        System.out.println("\n=== КІНЕЦЬ РОБОТИ СИСТЕМИ ===");
    }
}


