package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

public class PrintPlanetCommand extends Command {
    public PrintPlanetCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        universe.printPlanet(args[1]);
    }
}