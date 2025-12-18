from tourism.injector import Injector
from tourism.tourist import Tourist
from tourism.manager import Manager
from tourism.admin import Admin


def main():
    injector = Injector()

    tourist1 = Tourist("Ростислав")
    tourist2 = Tourist("Марія")
    tourist3 = Tourist("Іван")
    manager = Manager("Ірина")
    admin = Admin("Олександр")

    print("=== СТАРТ СИСТЕМИ ТУРИЗМУ ===")

    tourist1.show_role()
    tourist2.show_role()
    tourist3.show_role()
    manager.show_role()
    admin.show_role()

    print("\n--- Бронювання 1 ---")
    tourist1.make_reservation("Deluxe Room")
    manager.approve_package()
    admin.make_reservation("Deluxe Room")

    booking1 = injector.get_booking()
    booking1.initialize("Deluxe Room", 120)
    booking1.show_booking()
    booking1.confirm()
    tourist1.set_booking(booking1)

    print("\n--- Підтвердження ---")
    admin.send_voucher()

    print("\n=== КІНЕЦЬ ===")


if __name__ == "__main__":
    main()
