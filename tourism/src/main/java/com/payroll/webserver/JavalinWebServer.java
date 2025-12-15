package com.payroll.webserver;

import io.javalin.Javalin;

/**
 * Реалізація абстрактного WebServer на основі Javalin.
 *
 * Javalin — мінімалістичний Java/Kotlin веб-фреймворк.
 * Має простий API для маршрутизації, middleware, WebSocket, статичних файлів,
 * асинхронних обробників і плагінів (наприклад, OpenAPI).
 *
 * Підходить для швидкої розробки REST API та прототипів.
 */
public class JavalinWebServer extends WebServer {
    private final Javalin app;

    public JavalinWebServer() {
        this.app = Javalin.create(config -> {
            config.showJavalinBanner = false;
        });
    }

    @Override
    public void start(int port) {
        app.start(port);
    }

    @Override
    public void stop() {
        app.stop();
    }

    @Override
    public void before(RequestHandler handler) {
        app.before(context -> handler.handle(new JavalinHttpContext(context)));
    }

    @Override
    public void options(String path, RequestHandler handler) {
        app.options(path, context -> handler.handle(new JavalinHttpContext(context)));
    }

    @Override
    public void get(String path, RequestHandler handler) {
        app.get(path, context -> handler.handle(new JavalinHttpContext(context)));
    }
}
