package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;


public class RemoveJediCommand extends Command {
    public RemoveJediCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 3);
        universe.removeJedi(args[1], args[2]); // jediName, planetName
    }
}