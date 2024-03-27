package com.neudesic.appointmentmanagementsystem.application.command;

import com.neudesic.appointmentmanagementsystem.controller.dto.UpdateResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
@Component
public class AppointmentDomainCommandDispatcher implements CommandDispatcher{

    @Autowired
    CreateDoctorCommandHandler createPatientCommandHandler;

    @Autowired
    CreateAppointmentCommandHandler createAppointmentCommandHandler;

    @Autowired
    CancelAppointmentCommandHandler cancelAppointmentCommandHandler;

    @Autowired
    CreatePrescriptionCommandHandler createPrescriptionCommandHandler;

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public UpdateResponse send(BaseCommand command) {
        var handlers = routes.get(command.getClass());
        if (handlers == null || handlers.size() == 0) {
            throw new RuntimeException("No command handler was registered!");
        }

        return handlers.get(0).handle(command);
    }

    @PostConstruct
    public void registerHandlers() {
        registerHandler(CreateDoctorCommand.class, createPatientCommandHandler);
        registerHandler(CreateAppointmentCommand.class, createAppointmentCommandHandler);
        registerHandler(CancelAppointmentCommand.class, cancelAppointmentCommandHandler);
        registerHandler(CreatePrescriptionCommand.class, createPrescriptionCommandHandler);

    }
}
