package com.github.intelligence.orchestrator.framework;

import com.command.*;
import com.command.handlers.CommandExecutesHandler;
import com.command.handlers.CommandExistsHandler;
import com.command.handlers.Handler;

import java.util.List;

public class SubcommandFactory {
    public List<Command> subcommands;
    public void addSubcommand(Command command) throws InvalidCommandException {
        Handler handler = new CommandExistsHandler(command)
                .setNextHandler(new CommandExecutesHandler(command));

        if (handler.handle(command)) {
            subcommands.add(command);
        } else {
            throw new InvalidCommandException("An invalid command has been provided.");
        }
    }
}
