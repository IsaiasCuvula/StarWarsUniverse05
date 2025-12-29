package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import universes.Universe;

public class HelpCommand extends Command {
    public HelpCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        storage.help();
    }
}