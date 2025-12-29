package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import manager.Universe;

public class OpenCommand extends Command {
    public OpenCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        storage.open(args[1]);
    }
}