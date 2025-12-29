package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import manager.Universe;

public class SaveCommand extends Command {
    public SaveCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        storage.save(universe.getPlanets());
    }
}