package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Command to remove a Jedi from a planet.
 */
public class RemoveJediCommand extends Command {
    
    /**
     * Creates the command.
     */
    public RemoveJediCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes Jedi removal.
     */
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 3);
        universe.removeJedi(args[1], args[2]); // jediName, planetName
    }
}
