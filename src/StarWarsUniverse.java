import java.util.ArrayList;

public class StarWarsUniverse {

    private ArrayList<Planet> planets = new ArrayList<>();

    public  void printMenu() {
        System.out.println("**************************************");
        System.out.println("Choose an option:");
        System.out.println("1 - Add planet (add_planet <planet_name>)");
        System.out.println("2 - Create Jedi (create_jedi <planet_name> <jedi_name> <jedi_rank> <jedi_age> <saber_color> <jedi_strength>)");
        System.out.println("3 - Remove Jedi (remove_jedi <jedi_name> <planet_name>)");
        System.out.println("4 - Promote Jedi (<jedi_name> <multiplier>)");
        System.out.println("5 - Demote Jedi (<jedi_name> <multiplier>)");
        System.out.println("6 - Get Strongest Jedi  (get_strongest_jedi <planet_name>)");
        System.out.println("7 - Get Youngest Jedi (get_youngest_jedi <planet_name> <jedi_rank>)");
        System.out.println("8 - Get Most Used Saber Color (get_most_used_saber_color <planet_name> <jedi_rank>)");
        System.out.println("9 - Get Most Used Saber Color (get_most_used_saber_color <planet_name>)");
        System.out.println("10 - Show planet/moon details (print <planet_name>)");
        System.out.println("11 - Show details for a Jedi (print <jedi_name>)");
        System.out.println("12 - Show Jedis details for two planets (<planet_name> <planet_name>)");
        System.out.println("13 - Exit (To close the program)");
        System.out.println("**************************************");
        System.out.println("Command: ");
    }


    public void createJedi(String planetName, String jediName){
        System.out.println("Jedi Added Successfully...");
    }


    public void addPlanet(String name){

        if(name.trim().isEmpty()){
            System.out.println("Planet name cannot be empty");
            return;
        }

        boolean alreadyExist = false;

        for (Planet planet: planets){
            if (planet.getName().equalsIgnoreCase(name)) {
                alreadyExist = true;
                break;
            }
        }

        if(alreadyExist){
            System.out.printf("Planet %s already exist %n", name);
        }else{
            Planet planet = new Planet(name);
            planets.add(planet);
            System.out.printf("Planet %s added successfully...%n", planet.getName());
        }
    }
}
