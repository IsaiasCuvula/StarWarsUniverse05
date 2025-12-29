package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Command to demote a Jedi.
 */
public class DemoteJediCommand extends Command {
    
    /**
     * Creates the command.
     */
    public DemoteJediCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes Jedi demotion.
     */
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 3);
        String jediName = args[1];
        double multiplier = parseDouble(args[2], "multiplier");
        universe.demoteJedi(jediName, multiplier);
    }
}
