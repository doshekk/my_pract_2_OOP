from tourism.subjects import TravelSubject


class Tourist(TravelSubject):
    """
    Клас для представлення туриста
    """

    def __init__(self, name: str):
        super().__init__(name)
        self.booking = None

    def show_role(self):
        print(f"{self.name} — Турист.")

    def make_reservation(self, room_type: str):
        print(f"{self.name} бронює номер: {room_type}")

    def cancel_reservation(self):
        print(f"{self.name} скасовує бронювання.")

    def set_booking(self, booking):
        self.booking = booking

    def change_dates(self, new_dates: str):
        print(f"{self.name} змінює дати: {new_dates}")
