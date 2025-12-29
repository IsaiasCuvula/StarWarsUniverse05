package commands;

import datastorage.DataStorage;
import manager.Universe;

/**
 * Abstract base class for all commands in the application.
 * Each concrete command implements the execute method with its specific logic.
 */
public abstract class Command {
    
    protected Universe universe;
    protected DataStorage storage;
    
    /**
     * Constructs a Command with references to the universe and storage systems.
     *
     * @param universe the universe management system
     * @param storage the data persistence system
     */
    public Command(Universe universe, DataStorage storage) {
        this.universe = universe;
        this.storage = storage;
    }
    
    /**
     * Executes the command with the provided arguments.
     *
     * @param args command arguments from user input (including command name at index 0)
     * @throws Exception if command execution fails
     */
    public abstract void execute(String[] args) throws Exception;
    
    protected void validateArgsCount(String[] args, int expected) throws Exception {
        if (args.length != expected) {
            throw new Exception("Expected " + expected + " arguments");
        }
    }
}