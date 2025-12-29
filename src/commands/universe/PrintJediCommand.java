package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

public class PrintJediCommand extends Command {
    public PrintJediCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        universe.printJedi(args[1]);
    }
}