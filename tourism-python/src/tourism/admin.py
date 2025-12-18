from tourism.subjects import TravelSubject


class Admin(TravelSubject):
    """
    Клас для представлення адміністратора готелю
    """

    def show_role(self):
        print(f"{self.name} — Адміністратор готелю.")

    def make_reservation(self, room_type: str):
        print(f"Адміністратор отримує запит на бронювання: {room_type}")

    def cancel_reservation(self):
        print("Адміністратор скасовує бронювання в системі.")

    def send_voucher(self):
        print("Адміністратор надсилає ваучер туристу.")
