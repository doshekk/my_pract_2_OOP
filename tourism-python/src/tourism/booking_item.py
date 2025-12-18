class BookingItem:
    """
    Клас для збереження позиції бронювання
    """

    def __init__(self, room_type: str, price: float):
        self.room_type = room_type
        self.price = price

    def get_room_type(self):
        return self.room_type

    def get_price(self):
        return self.price
