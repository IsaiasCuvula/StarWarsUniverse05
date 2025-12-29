package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Command to add a new planet to the universe.
 */
public class AddPlanetCommand extends Command {
    
    /**
     * Creates the add-planet command.
     */
    public AddPlanetCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes planet creation.
     */
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        universe.addPlanet(args[1]);
    }
}
