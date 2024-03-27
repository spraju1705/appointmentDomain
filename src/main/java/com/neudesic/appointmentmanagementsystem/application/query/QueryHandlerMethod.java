package com.neudesic.appointmentmanagementsystem.application.query;


import com.neudesic.appointmentmanagementsystem.domain.entities.Entity;

import java.util.List;

@FunctionalInterface
public interface QueryHandlerMethod<T extends BaseQuery> {
     List<? extends Entity> handle(T query);
}
