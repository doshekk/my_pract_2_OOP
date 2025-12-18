from tourism.di import TourismModule
from tourism.booking import Booking

class Injector:
    def __init__(self):
        self.module = TourismModule()

    def get_booking(self):
        booking = Booking()
        booking.set_booking_service(self.module.booking_service)
        return booking
