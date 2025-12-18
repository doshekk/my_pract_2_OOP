from tourism.booking_item import BookingItem
from tourism.payment_service import PaymentService

class Booking:
    """
    Клас для управління бронюванням
    композиція: BookingItem
    агрегація: BookingService
    """

    def __init__(self):
        self.item = None
        self.payment = None
        self.booking_service = None  # буде injected через setter

    # === Setter-based DI (аналог @Inject setter) ===
    def set_booking_service(self, booking_service):
        self.booking_service = booking_service

    def initialize(self, room_type: str, price: float):
        self.item = BookingItem(room_type, price)
        self.payment = PaymentService()

    def confirm(self):
        print("Підтвердження бронювання...")
        self.payment.process_payment(self.item.price)
        self.booking_service.save_booking_item(self.item)

    def show_booking(self):
        print(f"Бронювання: {self.item.room_type} | {self.item.price}$")

    def get_item(self):
        return self.item
