package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

public class CloseCommand extends Command {
    public CloseCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        storage.close();
    }
}