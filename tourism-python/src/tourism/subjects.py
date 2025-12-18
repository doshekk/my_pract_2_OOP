from abc import ABC, abstractmethod
from tourism.booking_interface import IBookingPlacement


class TravelSubject(IBookingPlacement, ABC):
    """
    Абстрактний клас для ролей в системі туризму
    """

    def __init__(self, name: str):
        self.name = name

    @abstractmethod
    def show_role(self):
        pass
