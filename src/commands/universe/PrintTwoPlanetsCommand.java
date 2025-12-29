package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Command to print two planets side by side.
 */
public class PrintTwoPlanetsCommand extends Command {
    
    /**
     * Creates the command.
     */
    public PrintTwoPlanetsCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes two-planet comparison.
     */
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 3);
        universe.printTwoPlanets(args[1], args[2]);
    }
}
