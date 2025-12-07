import manager.FileManager;
import manager.StarWarsUniverse;


public class Application {

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        StarWarsUniverse starWarsUniverse = new StarWarsUniverse();
//        Scanner scanner = new Scanner(System.in);
//        starWarsUniverse.startProgram(scanner, fileManager);
//        scanner.close();
        fileManager.open();
        System.out.println("> Program closed.");
    }
}
