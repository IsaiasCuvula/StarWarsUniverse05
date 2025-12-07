import manager.ApplicationManager;
import manager.FileManager;
import manager.StarWarsUniverse;

import java.util.Scanner;


public class Application {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        fileManager.open("stars_war.text");
        StarWarsUniverse starWarsUniverse = new StarWarsUniverse();
        ApplicationManager manager = new ApplicationManager(fileManager, starWarsUniverse);
        Scanner scanner = new Scanner(System.in);
        manager.startProgram(scanner);
        scanner.close();
        System.out.println("> Program closed.");
    }
}
