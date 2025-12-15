package com.payroll.webserver;

import io.javalin.http.Context;

/**
 * Адаптер Javalin Context до абстракції HttpContext.
 */
public class JavalinHttpContext implements HttpContext {
    private final Context ctx;

    public JavalinHttpContext(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public Response status(int code) {
        ctx.status(code);
        return this;
    }

    @Override
    public Response header(String name, String value) {
        ctx.header(name, value);
        return this;
    }

    @Override
    public Response json(Object obj) {
        ctx.json(obj);
        return this;
    }

    @Override
    public Response result(String content) {
        ctx.result(content);
        return this;
    }
}
