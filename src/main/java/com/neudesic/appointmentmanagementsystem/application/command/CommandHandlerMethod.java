package com.neudesic.appointmentmanagementsystem.application.command;

import com.neudesic.appointmentmanagementsystem.controller.dto.UpdateResponse;

public interface CommandHandlerMethod<T extends BaseCommand> {
    UpdateResponse handle(T command);
}
