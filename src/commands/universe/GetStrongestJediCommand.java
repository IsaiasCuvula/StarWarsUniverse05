package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Command to get the strongest Jedi on a planet.
 */
public class GetStrongestJediCommand extends Command {
    
    /**
     * Creates the command.
     */
    public GetStrongestJediCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes strongest Jedi lookup.
     */
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        universe.getStrongestJedi(args[1]);
    }
}
