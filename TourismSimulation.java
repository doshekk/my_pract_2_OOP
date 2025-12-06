// Інтерфейс
interface IBookingPlacement {
    void makeReservation(String roomType);
    void cancelReservation();
}

// Абстрактний клас
abstract class TravelSubject implements IBookingPlacement {
    protected String name;

    public TravelSubject(String name) {
        this.name = name;
    }

    public abstract void showRole();
}

// Турист
class Tourist extends TravelSubject {
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

// Менеджер
class Manager extends TravelSubject {
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

// Адміністратор
class Admin extends TravelSubject {
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

// Сервіс оплати (залежність)
class PaymentService {
    public void processPayment(double amount) {
        System.out.println("Оплата " + amount + "$ пройшла успішно.");
    }
}

// Композиція: бронювання → позиція бронювання
class Booking {
    private BookingItem item; // композиція
    private PaymentService payment;

    public Booking(String roomType, double price) {
        this.item = new BookingItem(roomType, price);
        this.payment = new PaymentService(); // залежність
    }

    public void confirm() {
        System.out.println("Підтвердження бронювання...");
        payment.processPayment(item.getPrice());
    }

    public void showBooking() {
        System.out.println("Бронювання: " + item.getRoomType() + " | " + item.getPrice() + "$");
    }
}

// Позиція бронювання
class BookingItem {
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


// Імітація роботи системи

public class TourismSimulation {
    public static void main(String[] args) {

        Tourist tourist = new Tourist("Ростислав");
        Manager manager = new Manager("Ірина");
        Admin admin = new Admin("Олександр");

        System.out.println("=== СТАРТ СИСТЕМИ ТУРИЗМУ ===");

        tourist.showRole();
        manager.showRole();
        admin.showRole();

        System.out.println("\n--- Бронювання ---");
        tourist.makeReservation("Deluxe Room");
        manager.approvePackage();
        admin.makeReservation("Deluxe Room");

        Booking booking = new Booking("Deluxe Room", 120);
        booking.showBooking();
        booking.confirm();

        tourist.setBooking(booking);

        System.out.println("\n--- Підтвердження ---");
        admin.sendVoucher();

        System.out.println("\n--- Зміна дат ---");
        tourist.changeDates("12–15 серпня");

        System.out.println("\n=== КІНЕЦЬ РОБОТИ СИСТЕМИ ===");
    }
}
