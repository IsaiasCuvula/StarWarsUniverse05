package commands.universe;


import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

public class GetMostUsedColorCommand extends Command {
    public GetMostUsedColorCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
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