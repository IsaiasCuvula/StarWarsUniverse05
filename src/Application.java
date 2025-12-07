import manager.StarWarsUniverse;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        StarWarsUniverse starWarsUniverse = new StarWarsUniverse();
        Scanner scanner = new Scanner(System.in);
        starWarsUniverse.startProgram(scanner);
        scanner.close();
        System.out.println("> Program closed.");
    }
}
