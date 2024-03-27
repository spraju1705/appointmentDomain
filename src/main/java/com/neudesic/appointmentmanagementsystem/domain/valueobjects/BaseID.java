package com.neudesic.appointmentmanagementsystem.domain.valueobjects;

public abstract class BaseID<T> {
    public final T id;

    protected BaseID(T id) {
        this.id = id;
    }
}
