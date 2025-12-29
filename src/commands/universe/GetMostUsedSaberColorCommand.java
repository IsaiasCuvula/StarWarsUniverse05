package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

/**
 * Command to get the most used lightsaber color.
 */
public class GetMostUsedSaberColorCommand extends Command {
    
    /**
     * Creates the command.
     */
    public GetMostUsedSaberColorCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    /**
     * Executes color analysis.
     */
    @Override
    public void execute(String[] args) throws Exception {
        if (args.length == 2) {
            universe.getMostUsedSaberColor(args[1]);
        } else if (args.length == 3) {
            universe.getMostUsedSaberColor(args[1], args[2]);
        } else {
            throw new Exception("Usage: get_most_used_color <planet> [rank]");
        }
    }
}
