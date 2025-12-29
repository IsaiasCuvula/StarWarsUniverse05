package commands.universe;

import commands.Command;
import datastorage.DataStorage;
import manager.Universe;


public class CreateJediCommand extends Command {
    public CreateJediCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 7);
        
        String planetName = args[1];
        String jediName = args[2];
        String rank = args[3];
        
        int age = parseInt(args[4], "age");
        String color = args[5];
        double strength = parseDouble(args[6], "strength");
        
        universe.createJedi(planetName, jediName, rank, age, color, strength);
    }
}