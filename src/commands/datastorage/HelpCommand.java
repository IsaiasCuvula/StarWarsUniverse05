package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;


/**
 * Displays available commands.
 */
public class HelpCommand extends Command {
    
    /**
     * Creates the help command.
     */
    public HelpCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes help display.
     */
    @Override
    public void execute(String[] args) throws Exception {
        storage.help();
    }
}