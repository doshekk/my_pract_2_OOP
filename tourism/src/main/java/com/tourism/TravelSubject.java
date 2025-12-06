package com.tourism;

/**
 * Абстрактний клас для ролей в системі туризму
 */
public abstract class TravelSubject implements IBookingPlacement {
    protected String name;

    public TravelSubject(String name) {
        this.name = name;
    }

    public abstract void showRole();
}
