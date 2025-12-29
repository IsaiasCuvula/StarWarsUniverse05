package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;
import models.Planet;

import java.util.List;

public class OpenCommand extends Command {
    public OpenCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        String filename = args[1];
        storage.open(filename);
        List<Planet> loadedPlanets = storage.loadPlanets();
        universe.setPlanets(loadedPlanets);
        
        System.out.printf("> Success: File '%s' opened and %d planets loaded into memory.%n",
                filename, loadedPlanets.size());
    }
}