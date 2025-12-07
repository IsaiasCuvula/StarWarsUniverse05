import manager.ApplicationManager;
import manager.FileManager;
import manager.StarWarsUniverse;

import java.util.Scanner;

/**
 * Main application entry point. Constructs the FileManager and StarWarsUniverse,
 * wires them into the ApplicationManager and starts the interactive loop.
 */
public class Application {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        StarWarsUniverse starWarsUniverse = new StarWarsUniverse();
        ApplicationManager manager = new ApplicationManager(fileManager, starWarsUniverse);
        Scanner scanner = new Scanner(System.in);
        manager.startProgram(scanner);
        scanner.close();
        System.out.println("> Program closed.");
    }
}
