package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Saves universe data to the current file.
 */
public class SaveCommand extends Command {
    
    /**
     * Creates the save command.
     */
    public SaveCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes save operation.
     */
    @Override
    public void execute(String[] args) throws Exception {
        String filename = universe.getCurrentFilename();
        
        if (filename == null) {
            throw new Exception("No file is currently associated with this session. Use 'saveas'.");
        }
        
        storage.open(filename);
        storage.save(universe.getPlanets());
        storage.close();
        
        System.out.println("> Universe state successfully saved to " + filename);
    }
}
