package com.neudesic.appointmentmanagementsystem.application.command;

import com.neudesic.appointmentmanagementsystem.controller.dto.UpdateResponse;

public interface CommandDispatcher  {
    <T extends BaseCommand>void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    UpdateResponse send(BaseCommand command);
}
