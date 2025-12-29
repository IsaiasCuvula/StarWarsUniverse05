package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Command to get the youngest Jedi by rank.
 */
public class GetYoungestJediCommand extends Command {
    
    /**
     * Creates the command.
     */
    public GetYoungestJediCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes youngest Jedi lookup.
     */
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 3);
        universe.getYoungestJedi(args[1], args[2]);
    }
}
