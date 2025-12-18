from abc import ABC, abstractmethod


class IBookingPlacement(ABC):
    """
    Інтерфейс для бронювання
    """

    @abstractmethod
    def make_reservation(self, room_type: str):
        pass

    @abstractmethod
    def cancel_reservation(self):
        pass
