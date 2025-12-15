package com.payroll.webserver;

/**
 * Абстракція веб-сервера для мінімальної заміни фреймворку.
 * Містить лише необхідні методи, які зараз використовує додаток.
 * Реалізацію можна замінити (Javalin → інший) без зміни бізнес-коду.
 */
public abstract class WebServer {
    @FunctionalInterface
    public interface RequestHandler {
        void handle(HttpContext context) throws Exception;
    }

    public abstract void start(int port);

    public abstract void stop();

    public abstract void before(RequestHandler handler);

    public abstract void options(String path, RequestHandler handler);

    public abstract void get(String path, RequestHandler handler);
}
