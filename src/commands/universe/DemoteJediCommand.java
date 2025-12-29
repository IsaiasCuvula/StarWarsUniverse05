package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import manager.Universe;

public class DemoteJediCommand extends Command {
    public DemoteJediCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 3);
        String jediName = args[1];
        double multiplier = parseDouble(args[2], "multiplier");
        universe.demoteJedi(jediName, multiplier);
    }
}