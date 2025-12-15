package com.payroll.webserver;

/**
 * Абстракція HTTP-відповіді для незалежності від фреймворку.
 */
public interface Response {
    Response status(int code);
    Response header(String name, String value);
    Response json(Object obj);
    Response result(String content);
}
