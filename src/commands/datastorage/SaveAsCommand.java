package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

public class SaveAsCommand extends Command {
    public SaveAsCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        storage.saveAs(args[1], universe.getPlanets());
    }
}