package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;


public class SaveAsCommand extends Command {
    public SaveAsCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        String newFilename = args[1];
        storage.saveAs(newFilename, universe.getPlanets());
        universe.setCurrentFilename(newFilename);
        System.out.printf("> Universe successfully saved as '%s'.%n", newFilename);
    }
}