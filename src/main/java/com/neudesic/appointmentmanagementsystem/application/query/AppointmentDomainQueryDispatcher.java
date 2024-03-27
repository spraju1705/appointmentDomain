package com.neudesic.appointmentmanagementsystem.application.query;

import com.neudesic.appointmentmanagementsystem.domain.entities.Entity;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class AppointmentDomainQueryDispatcher implements QueryDispatcher {

    @Autowired
    GetAppointmentDetailsQueryHandlerMethod getAppointmentDetailsQueryHandlerMethod;

    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public List<? extends Entity> send(BaseQuery query) {
        var handlers = routes.get(query.getClass());
        if (handlers == null || handlers.size() <= 0) {
            throw new RuntimeException("No query handler was registered!");
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("Cannot send query to more than one handler!");
        }
        return handlers.get(0).handle(query);
    }

    @PostConstruct
    public void register(){
        registerHandler(GetPatientAppointmentDetailsQuery.class, getAppointmentDetailsQueryHandlerMethod);

    }
}
