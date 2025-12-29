package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import manager.Universe;

public class PrintTwoPlanetsCommand extends Command {
    public PrintTwoPlanetsCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 3);
        universe.printTwoPlanets(args[1], args[2]);
    }
}