package com.tourism;

import com.google.inject.Inject;
import com.payroll.webserver.WebServer;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Клас-вигляд для відображення даних з бази даних на веб-сторінці
 * Реалізує веб-інтерфейс з використанням Javalin
 */
public class TourismWebView {
    private WebServer webServer;
    private TourismController controller;

    /**
     * Конструктор з впровадженням залежностей
     *
     * @param webServer веб-сервер Javalin
     * @param controller контролер для отримання даних
     */
    @Inject
    public TourismWebView(WebServer webServer, TourismController controller) {
        this.webServer = webServer;
        this.controller = controller;
    }

    /**
     * Запускає веб-сервер та налаштовує маршрути
     *
     * @param port порт для запуску сервера
     */
    public void start(int port) {
        // Налаштування CORS middleware
        webServer.before(ctx -> {
            ctx.header("Access-Control-Allow-Origin", "*");
            ctx.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            ctx.header("Access-Control-Allow-Headers", "Content-Type");
        });

        // Обробка CORS preflight запитів
        webServer.options("/api/*", ctx -> {
            ctx.status(200);
        });

        // Маршрут для отримання всіх позицій бронювання у форматі JSON
        webServer.get("/api/bookings", ctx -> {
            List<BookingItem> bookingItems = controller.getAllBookingItems();
            
            // Перетворення на формат для відображення
            List<Map<String, Object>> response = bookingItems.stream()
                .map(item -> {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("roomType", item.getRoomType());
                    map.put("price", item.getPrice());
                    return map;
                })
                .collect(Collectors.toList());
            
            ctx.json(response);
        });

        // Маршрут для статичних файлів (HTML, CSS, JS)
        webServer.get("/", ctx -> {
            ctx.result(getIndexHtml());
            ctx.header("Content-Type", "text/html; charset=UTF-8");
        });

        webServer.get("/style.css", ctx -> {
            ctx.result(getStyleCss());
            ctx.header("Content-Type", "text/css; charset=UTF-8");
        });

        webServer.get("/script.js", ctx -> {
            ctx.result(getScriptJs());
            ctx.header("Content-Type", "application/javascript; charset=UTF-8");
        });

        // Запуск сервера
        webServer.start(port);
        System.out.println("✓ Веб-сервер запущений на http://localhost:" + port);
    }

    /**
     * Повертає HTML сторінку
     */
    private String getIndexHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html lang=\"uk\">\n");
        sb.append("<head>\n");
        sb.append("    <meta charset=\"UTF-8\">\n");
        sb.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        sb.append("    <title>Система бронювання туризму</title>\n");
        sb.append("    <link rel=\"stylesheet\" href=\"style.css\">\n");
        sb.append("</head>\n");
        sb.append("<body>\n");
        sb.append("    <div class=\"container\">\n");
        sb.append("        <h1>Бронювання готельних номерів</h1>\n\n");
        sb.append("        <div id=\"message\" class=\"message\"></div>\n\n");
        sb.append("        <div class=\"table-container\">\n");
        sb.append("            <table>\n");
        sb.append("                <thead>\n");
        sb.append("                    <tr>\n");
        sb.append("                        <th>№</th>\n");
        sb.append("                        <th>Тип номера</th>\n");
        sb.append("                        <th>Ціна</th>\n");
        sb.append("                    </tr>\n");
        sb.append("                </thead>\n");
        sb.append("                <tbody id=\"tableBody\">\n");
        sb.append("                    <tr>\n");
        sb.append("                        <td colspan=\"3\" class=\"empty-state\">Завантаження даних...</td>\n");
        sb.append("                    </tr>\n");
        sb.append("                </tbody>\n");
        sb.append("            </table>\n");
        sb.append("        </div>\n");
        sb.append("    </div>\n\n");
        sb.append("    <script src=\"script.js\"></script>\n");
        sb.append("</body>\n");
        sb.append("</html>\n");
        return sb.toString();
    }

    /**
     * Повертає CSS стилі
     */
    private String getStyleCss() {
        StringBuilder sb = new StringBuilder();
        sb.append("* {\n");
        sb.append("    margin: 0;\n");
        sb.append("    padding: 0;\n");
        sb.append("    box-sizing: border-box;\n");
        sb.append("}\n\n");
        sb.append("body {\n");
        sb.append("    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;\n");
        sb.append("    background: #f5f5f5;\n");
        sb.append("    padding: 40px 20px;\n");
        sb.append("}\n\n");
        sb.append(".container {\n");
        sb.append("    max-width: 800px;\n");
        sb.append("    margin: 0 auto;\n");
        sb.append("    background: white;\n");
        sb.append("    padding: 30px;\n");
        sb.append("    border-radius: 8px;\n");
        sb.append("    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n");
        sb.append("}\n\n");
        sb.append("h1 {\n");
        sb.append("    font-size: 24px;\n");
        sb.append("    margin-bottom: 30px;\n");
        sb.append("    color: #333;\n");
        sb.append("}\n\n");
        sb.append(".message {\n");
        sb.append("    margin-top: 15px;\n");
        sb.append("    padding: 10px;\n");
        sb.append("    border-radius: 4px;\n");
        sb.append("    font-size: 14px;\n");
        sb.append("    display: none;\n");
        sb.append("}\n\n");
        sb.append(".message.show {\n");
        sb.append("    display: block;\n");
        sb.append("}\n\n");
        sb.append(".message.error {\n");
        sb.append("    background: #ffebee;\n");
        sb.append("    color: #c62828;\n");
        sb.append("}\n\n");
        sb.append(".message.success {\n");
        sb.append("    background: #e8f5e9;\n");
        sb.append("    color: #2e7d32;\n");
        sb.append("}\n\n");
        sb.append(".table-container {\n");
        sb.append("    margin-top: 40px;\n");
        sb.append("}\n\n");
        sb.append("table {\n");
        sb.append("    width: 100%;\n");
        sb.append("    border-collapse: collapse;\n");
        sb.append("}\n\n");
        sb.append("thead {\n");
        sb.append("    background: #f5f5f5;\n");
        sb.append("}\n\n");
        sb.append("th {\n");
        sb.append("    padding: 12px;\n");
        sb.append("    text-align: left;\n");
        sb.append("    font-size: 14px;\n");
        sb.append("    color: #666;\n");
        sb.append("    border-bottom: 2px solid #ddd;\n");
        sb.append("}\n\n");
        sb.append("td {\n");
        sb.append("    padding: 12px;\n");
        sb.append("    font-size: 14px;\n");
        sb.append("    color: #333;\n");
        sb.append("    border-bottom: 1px solid #eee;\n");
        sb.append("}\n\n");
        sb.append("tbody tr:hover {\n");
        sb.append("    background: #fafafa;\n");
        sb.append("}\n\n");
        sb.append(".empty-state {\n");
        sb.append("    text-align: center;\n");
        sb.append("    padding: 40px;\n");
        sb.append("    color: #999;\n");
        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Повертає JavaScript код
     */
    private String getScriptJs() {
        StringBuilder sb = new StringBuilder();
        sb.append("const API_URL = 'http://localhost:8080';\n");
        sb.append("const messageDiv = document.getElementById('message');\n");
        sb.append("const tableBody = document.getElementById('tableBody');\n\n");
        sb.append("// Завантажити дані при старті\n");
        sb.append("loadBookings();\n\n");
        sb.append("function showMessage(text, type) {\n");
        sb.append("    messageDiv.textContent = text;\n");
        sb.append("    messageDiv.className = `message ${type} show`;\n");
        sb.append("    setTimeout(() => {\n");
        sb.append("        messageDiv.className = 'message';\n");
        sb.append("    }, 3000);\n");
        sb.append("}\n\n");
        sb.append("async function loadBookings() {\n");
        sb.append("    try {\n");
        sb.append("        const response = await fetch(`${API_URL}/api/bookings`, {\n");
        sb.append("            method: 'GET',\n");
        sb.append("            mode: 'cors'\n");
        sb.append("        });\n\n");
        sb.append("        if (response.ok) {\n");
        sb.append("            const bookings = await response.json();\n");
        sb.append("            updateTable(bookings);\n");
        sb.append("        } else {\n");
        sb.append("            const errorText = await response.text();\n");
        sb.append("            showMessage('Помилка завантаження даних: ' + errorText, 'error');\n");
        sb.append("            tableBody.innerHTML = '<tr><td colspan=\"3\" class=\"empty-state\">Помилка: ' + errorText + '</td></tr>';\n");
        sb.append("        }\n");
        sb.append("    } catch (error) {\n");
        sb.append("        showMessage('Помилка з\\'єднання з сервером', 'error');\n");
        sb.append("        tableBody.innerHTML = '<tr><td colspan=\"3\" class=\"empty-state\">Помилка з\\'єднання з сервером: ' + error.message + '</td></tr>';\n");
        sb.append("    }\n");
        sb.append("}\n\n");
        sb.append("function updateTable(bookings) {\n");
        sb.append("    if (bookings.length === 0) {\n");
        sb.append("        tableBody.innerHTML = '<tr><td colspan=\"3\" class=\"empty-state\">Немає даних про бронювання</td></tr>';\n");
        sb.append("        return;\n");
        sb.append("    }\n\n");
        sb.append("    tableBody.innerHTML = bookings.map((item, index) => `\n");
        sb.append("        <tr>\n");
        sb.append("            <td>${index + 1}</td>\n");
        sb.append("            <td>${item.roomType}</td>\n");
        sb.append("            <td>${item.price.toFixed(2)} грн</td>\n");
        sb.append("        </tr>\n");
        sb.append("    `).join('');\n");
        sb.append("}\n");
        return sb.toString();
    }
}
