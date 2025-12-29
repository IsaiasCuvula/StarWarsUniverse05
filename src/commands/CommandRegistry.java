package commands;

import commands.datastorage.*;
import commands.universe.*;
import datastorage.DataStorage;
import universes.Universe;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    public static Map<String, Command> initialize(DataStorage storage, Universe universe) {
        Map<String, Command> commands = new HashMap<>();
        commands.put("open", new OpenCommand(universe, storage));
        commands.put("close", new CloseCommand(universe, storage));
        commands.put("save", new SaveCommand(universe, storage));
        commands.put("saveas", new SaveAsCommand(universe, storage));
        commands.put("help", new HelpCommand(universe, storage));
        commands.put("add_planet", new AddPlanetCommand(universe, storage));
        commands.put("create_jedi", new CreateJediCommand(universe, storage));
        commands.put("remove_jedi", new RemoveJediCommand(universe, storage));
        commands.put("promote_jedi", new PromoteJediCommand(universe, storage));
        commands.put("demote_jedi", new DemoteJediCommand(universe, storage));
        commands.put("print_planet", new PrintPlanetCommand(universe, storage));
        commands.put("print_jedi", new PrintJediCommand(universe, storage));
        commands.put("print_two_planets", new PrintTwoPlanetsCommand(universe, storage));
        commands.put("get_most_used_color", new GetMostUsedColorCommand(universe, storage));
        commands.put("get_youngest_jedi", new GetYoungestJediCommand(universe, storage));
        commands.put("get_strongest_jedi", new GetStrongestJediCommand(universe, storage));
        return commands;
    }
}