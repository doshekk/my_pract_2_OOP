from tourism.booking_item import BookingItem


class BookingService:
    """
    Сервіс для збереження даних про позиції бронювання в БД
    """

    def __init__(self, connection):
        self.connection = connection

    def save_booking_item(self, booking_item: BookingItem):
        sql = """
        INSERT INTO booking_items (room_type, price)
        VALUES (?, ?)
        """
        cursor = self.connection.cursor()
        cursor.execute(sql, (
            booking_item.get_room_type(),
            booking_item.get_price()
        ))
        self.connection.commit()

        print(
            f"✓ Позиція бронювання збережена в БД: "
            f"{booking_item.get_room_type()} | {booking_item.get_price()}$"
        )
