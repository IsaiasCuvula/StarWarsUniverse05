import java.util.ArrayList;

public class StarWarsUniverse {

    private final ArrayList<Planet> planets = new ArrayList<>();

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


    public void createJedi(String planetName, String jediName,
    String jediRank, int jediAge, String saberColor,
            int jediStrength){

        try{
            Planet savedPlanet = getPlanetByName(planetName);

            if(savedPlanet == null){
                System.out.printf("Planet %s does not exist %n", planetName);
                return;
            }else{
                Jedi savedJedi = getJediByName(jediName);
                if(savedJedi != null){
                    System.out.printf("Jedi %s already exist %n", jediName);
                }else{
                    Jedi newJedi = new Jedi(
                            jediName, jediRank, jediAge, saberColor, jediStrength
                    );
                    savedPlanet.addJedi(newJedi);
                }

            }
            System.out.println("Jedi Added Successfully...");
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public void addPlanet(String name){
        try{
            boolean alreadyExist = getPlanetByName(name) != null;
            if(alreadyExist){
                System.out.printf("Planet %s already exist %n", name);
            }else{
                Planet planet = new Planet(name);
                planets.add(planet);
                System.out.printf("Planet %s added successfully...%n", planet.getName());
            }
        }catch (Exception e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private Jedi getJediByName(String name){
        Jedi savedJedi = null;

        if(name.trim().isEmpty()){
            throw new IllegalArgumentException("Jedi name cannot be empty");
        }
        for (Planet planet: planets){
            for (Jedi jedi: planet.getJediList()){
                if (jedi.getName().equalsIgnoreCase(name)) {
                    savedJedi = jedi;
                    break;
                }
            }
            if(savedJedi != null){
                break;
            }
        }
        return savedJedi;
    }

    private Planet getPlanetByName(String name){
        Planet savedPlanet = null;

        if(name.trim().isEmpty()){
            return null;
        }

        for (Planet planet: planets){
            if (planet.getName().equalsIgnoreCase(name)) {
                savedPlanet = planet;
                break;
            }
        }
        return savedPlanet;
    }
}
