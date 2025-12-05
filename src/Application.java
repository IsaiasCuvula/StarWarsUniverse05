import java.util.Scanner;
public class Application {


    public static void main(String[] args){
        StarWarsUniverse starWarsUniverse = new StarWarsUniverse();
        String flag = "open";
        Scanner scanner = new Scanner(System.in);

        starWarsUniverse.printMenu();

        while (!flag.equalsIgnoreCase("exit")){

            System.out.print("> ");
            flag = scanner.nextLine();
            String[] data = flag.split(" ");

            if(flag.equalsIgnoreCase("exit")){
                break;
            }

            if(data.length != 0){
                String action = data[0];
                String param = data[1];

                switch (action){
                    case "add_planet":
                        System.out.println("add_planet called");
                        starWarsUniverse.addPlanet(param);
                        break;
                    case "create_jedi":
                        System.out.println("create_jedi called");
                        break;
                    case "remove_jedi":
                        System.out.println("remove_jedi called");
                        break;
                    case "promote_jedi":
                        System.out.println("promote_jedi called");
                        break;
                    case "demote_jedi":
                        System.out.println("demote_jedi called");
                        break;
                    case "get_strongest_jedi":
                        System.out.println("get_strongest_jedi called");
                        break;
                    case "get_youngest_jedi":
                        System.out.println("get_youngest_jedi called");
                        break;
                    case "get_most_used_saber_color":
                        System.out.println("get_most_used_saber_color called");
                        break;
                    case "print":
                        System.out.println("print called");
                        break;
                    default:
                        System.out.println("Unexpected value: " + action);
                }
            }
            System.out.println("flag you entered:  " + flag);
        }
        scanner.close();
        System.out.println("> Program closed.");
    }
}
