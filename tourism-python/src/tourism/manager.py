from tourism.subjects import TravelSubject


class Manager(TravelSubject):
    """
    Клас для представлення менеджера турфірми
    """

    def show_role(self):
        print(f"{self.name} — Менеджер турфірми.")

    def make_reservation(self, room_type: str):
        print(f"Менеджер {self.name} підбирає пакет з номером: {room_type}")

    def cancel_reservation(self):
        print(f"Менеджер {self.name} скасовує бронювання клієнта.")

    def approve_package(self):
        print("Менеджер погоджує умови пакету.")
