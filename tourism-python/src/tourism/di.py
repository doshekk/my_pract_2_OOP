import sqlite3
from tourism.booking_service import BookingService

class TourismModule:
    """
    Аналог Google Guice Module.
    Відповідає за:
    - створення з'єднання з БД
    - ініціалізацію таблиці
    - надання сервісів (Singleton)
    """

    DB_PATH = "data/tourism.db"

    def __init__(self):
        self.connection = self._provide_connection()
        self.booking_service = BookingService(self.connection)

    def _provide_connection(self):
        try:
            connection = sqlite3.connect(self.DB_PATH)
            self._create_table_if_not_exists(connection)
            return connection
        except Exception as e:
            raise RuntimeError("Failed to create database connection") from e

    def _create_table_if_not_exists(self, connection):
        sql = """
        CREATE TABLE IF NOT EXISTS booking_items (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            room_type TEXT NOT NULL,
            price REAL NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )
        """
        cursor = connection.cursor()
        cursor.execute(sql)
        connection.commit()
