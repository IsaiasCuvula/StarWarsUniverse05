import manager.ApplicationManager;
import datastorage.FileManager;
import manager.StarWarsUniverse;

/**
 * Main application entry point. Constructs the FileManager and StarWarsUniverse,
 * wires them into the ApplicationManager and starts the interactive loop.
 */
public class Application {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        StarWarsUniverse starWarsUniverse = new StarWarsUniverse();
        ApplicationManager manager = new ApplicationManager(fileManager, starWarsUniverse);
        manager.start();
        System.out.println("> Program closed.");
    }
}
