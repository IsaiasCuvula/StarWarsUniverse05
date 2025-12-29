package commands;

import commands.datastorage.*;
import commands.universe.*;
import datastorage.DataStorage;
import manager.Universe;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    public static Map<String, Command> initialize(Universe u, DataStorage s) {
        Map<String, Command> commands = new HashMap<>();
        commands.put("open", new OpenCommand(u, s));
        commands.put("close", new CloseCommand(u, s));
        commands.put("save", new SaveCommand(u, s));
        commands.put("saveas", new SaveAsCommand(u, s));
        commands.put("help", new HelpCommand(u, s));
        commands.put("add_planet", new AddPlanetCommand(u, s));
        commands.put("create_jedi", new CreateJediCommand(u, s));
        commands.put("remove_jedi", new RemoveJediCommand(u, s));
        commands.put("promote_jedi", new PromoteJediCommand(u, s));
        commands.put("demote_jedi", new DemoteJediCommand(u, s));
        commands.put("print_planet", new PrintPlanetCommand(u, s));
        commands.put("print_jedi", new PrintJediCommand(u, s));
        commands.put("print_two_planets", new PrintTwoPlanetsCommand(u, s));
        commands.put("get_most_used_color", new GetMostUsedColorCommand(u, s));
        commands.put("get_youngest_jedi", new GetYoungestJediCommand(u, s));
        commands.put("get_strongest_jedi", new GetStrongestJediCommand(u, s));
        return commands;
    }
}