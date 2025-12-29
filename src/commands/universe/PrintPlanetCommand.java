package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Command to print planet information.
 */
public class PrintPlanetCommand extends Command {
    
    /**
     * Creates the command.
     */
    public PrintPlanetCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes planet printing.
     */
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        universe.printPlanet(args[1]);
    }
}
