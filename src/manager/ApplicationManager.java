package manager;

import commands.Command;
import commands.CommandRegistry;
import datastorage.DataStorage;

import java.util.Map;
import java.util.Scanner;

/**
 * ApplicationManager coordinates command execution between the CLI and the StarWarsUniverse.
 * It parses input strings and calls the appropriate StarWarsUniverse or FileManager methods.
 */
public class ApplicationManager {
    private final DataStorage storage;
    private final Scanner scanner;
    private boolean running;
    
    private final Map<String, Command> commands;

    /**
     * Constructs an ApplicationManager with the provided FileManager and StarWarsUniverse instances.
     *
     * @param storage Storage instance used for file operations
     * @param universe Universe instance used for in-memory operations
     */
    public ApplicationManager(DataStorage storage, Universe universe) {
        this.scanner = new Scanner(System.in);
        this.storage = storage;
        this.running = true;
        
        //Initialize the commands once
        this.commands = CommandRegistry.initialize(storage, universe);
    }

    /**
     * Starts the main program loop that reads commands from the given Scanner.
     * This method blocks until the user issues the "exit" command.
     */
    public void start() {
        System.out.println("*** Star Wars Universe Management System ***");
        System.out.println("Type 'help' for a list of available commands.");
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }
            if (input.equalsIgnoreCase("exit")) {
                handleExit();
                break;
            }
            processCommand(input);
        }
    }
    
    
    private void processCommand(String input){
        String[] data = input.split("\\s+");
        String action = data[0];
        Command command = commands.get(action);
        try{
            if(command == null){
                System.out.println("> Invalid command. Type 'help' to see available options.");
                return;
            }
            command.execute(data);
        } catch (Exception e) {
            System.out.println("> Error: " + e.getMessage());
        }
    }
    
    private void handleExit() {
        try {
            System.out.println("> Exiting the program...");
            if (storage.isOpen()) {
                storage.close();
            }
        } finally {
            this.running = false;
            this.scanner.close();
        }
    }
}
