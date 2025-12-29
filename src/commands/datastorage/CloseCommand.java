package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Closes the currently opened file.
 */
public class CloseCommand extends Command {
    
    /**
     * Creates the close command.
     */
    public CloseCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes the close operation.
     */
    @Override
    public void execute(String[] args) throws Exception {
        storage.close();
    }
}