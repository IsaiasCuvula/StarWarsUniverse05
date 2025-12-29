package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Command to promote a Jedi.
 */
public class PromoteJediCommand extends Command {
    
    /**
     * Creates the command.
     */
    public PromoteJediCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes Jedi promotion.
     */
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 3);
        String jediName = args[1];
        double multiplier = parseDouble(args[2], "multiplier");
        universe.promoteJedi(jediName, multiplier);
    }
}
