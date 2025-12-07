import manager.FileManager;
import manager.StarWarsUniverse;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        StarWarsUniverse starWarsUniverse = new StarWarsUniverse();
        Scanner scanner = new Scanner(System.in);
        starWarsUniverse.startProgram(scanner, fileManager);
        scanner.close();
       // fileManager.initFile();
        System.out.println("> Program closed.");
    }
}
