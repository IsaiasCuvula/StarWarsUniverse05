package manager;

import exceptions.UniverseException;

/**
 * Abstract base class for all commands in the application.
 * Each concrete command implements the execute method with its specific logic.
 *
 * This class demonstrates:
 * - Abstraction: defines the structure that all commands must follow
 * - Inheritance: concrete commands extend this class
 * - Encapsulation: protects shared resources (universe and storage)
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
}