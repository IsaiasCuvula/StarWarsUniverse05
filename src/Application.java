import commands.CommandShell;
import datastorage.FileManager;
import universes.StarWarsUniverse;

/**
 * Main application entry point. Constructs the
 * Storage (FileManager) and (Universe) StarWarsUniverse,
 * wires them into the CommandShell and starts the interactive loop.
 */
public class Application {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        StarWarsUniverse starWarsUniverse = new StarWarsUniverse();
        CommandShell manager = new CommandShell(fileManager, starWarsUniverse);
        manager.start();
        System.out.println("> Program closed.");
    }
}
