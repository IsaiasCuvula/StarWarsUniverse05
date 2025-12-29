package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

public class GetStrongestJediCommand extends Command {
    public GetStrongestJediCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        universe.getStrongestJedi(args[1]);
    }
}