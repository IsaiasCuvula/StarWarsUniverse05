import manager.FileManager;
import manager.StarWarsUniverse;

import java.util.Scanner;


public class Application {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        fileManager.open();
        StarWarsUniverse starWarsUniverse = new StarWarsUniverse(fileManager);
        Scanner scanner = new Scanner(System.in);
        starWarsUniverse.startProgram(scanner);
        scanner.close();

        System.out.println("> Program closed.");
    }
}
