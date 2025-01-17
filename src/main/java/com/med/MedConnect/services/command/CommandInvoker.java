package com.med.MedConnect.services.command;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandInvoker {
    public List<?> executeCommand(Command command) {
        return command.execute();
    }
}
