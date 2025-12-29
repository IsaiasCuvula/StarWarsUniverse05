package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

public class SaveCommand extends Command {
    public SaveCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        String filename = universe.getCurrentFilename();
        
        if (filename == null) {
            throw new Exception("No file is currently associated with this session. Use 'saveas'.");
        }
        storage.open(filename);
        storage.save(universe.getPlanets());
        storage.close();
        
        System.out.println("> Universe state successfully persisted to " + filename);
    }
}