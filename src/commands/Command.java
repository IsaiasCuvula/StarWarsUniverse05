package commands;

import datastorage.DataStorage;
import universes.Universe;

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
    
    /**
     * Parses an integer value.
     *
     * @param value string value
     * @param fieldName field name for error message
     * @return parsed integer
     * @throws Exception if parsing fails
     */
    protected int parseInt(String value, String fieldName) throws Exception {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new Exception("The field '" + fieldName + "' must be a whole number.");
        }
    }
    
    /**
     * Parses a double value.
     *
     * @param value string value
     * @param fieldName field name for error message
     * @return parsed double
     * @throws Exception if parsing fails
     */
    protected double parseDouble(String value, String fieldName) throws Exception {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new Exception("The field '" + fieldName + "' must be a decimal number (e.g., 1.5).");
        }
    }
}