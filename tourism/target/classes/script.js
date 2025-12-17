const API_URL = 'http://localhost:8080';
const messageDiv = document.getElementById('message');
const tableBody = document.getElementById('tableBody');

// Завантажити дані при старті
loadBookings();

function showMessage(text, type) {
    messageDiv.textContent = text;
    messageDiv.className = `message ${type} show`;
    setTimeout(() => {
        messageDiv.className = 'message';
    }, 3000);
}

async function loadBookings() {
    try {
        const response = await fetch(`${API_URL}/api/bookings`, {
            method: 'GET',
            mode: 'cors'
        });

        if (response.ok) {
            const bookings = await response.json();
            updateTable(bookings);
        } else {
            const errorText = await response.text();
            showMessage('Помилка завантаження даних: ' + errorText, 'error');
            tableBody.innerHTML = '<tr><td colspan="3" class="empty-state">Помилка: ' + errorText + '</td></tr>';
        }
    } catch (error) {
        showMessage('Помилка з\'єднання з сервером', 'error');
        tableBody.innerHTML = '<tr><td colspan="3" class="empty-state">Помилка з\'єднання з сервером: ' + error.message + '</td></tr>';
    }
}

function updateTable(bookings) {
    if (bookings.length === 0) {
        tableBody.innerHTML = '<tr><td colspan="3" class="empty-state">Немає даних про бронювання</td></tr>';
        return;
    }

    tableBody.innerHTML = bookings.map((item, index) => `
        <tr>
            <td>${index + 1}</td>
            <td>${item.roomType}</td>
            <td>${item.price.toFixed(2)} грн</td>
        </tr>
    `).join('');
}
