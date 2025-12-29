package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Command to print Jedi information.
 */
public class PrintJediCommand extends Command {
    
    /**
     * Creates the command.
     */
    public PrintJediCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes Jedi printing.
     */
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        universe.printJedi(args[1]);
    }
}
