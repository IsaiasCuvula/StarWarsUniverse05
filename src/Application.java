import manager.FileManager;
import manager.StarWarsUniverse;

import java.util.Scanner;


public class Application {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        StarWarsUniverse starWarsUniverse = new StarWarsUniverse();
        fileManager.open();
        Scanner scanner = new Scanner(System.in);
        starWarsUniverse.startProgram(scanner, fileManager);
        scanner.close();

        System.out.println("> Program closed.");
    }
}
