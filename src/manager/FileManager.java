package manager;

import java.io.File;

public class FileManager {
    private final String filename =  "star_wars.txt";


    public void initFile(){
        try{
            File file = new File(filename);
            if(file.createNewFile()){
                System.out.println("File created successfully: " + file.getName());
            }else {
                System.out.println("File already exists.");
            }
        }catch (Exception e){
            System.out.println("Error executing init file manager: " + e.getMessage());
        }
    }

    public void open(){

    }

    public void close(){

    }

    public void save(){

    }

    public void saveAs(){

    }

    public void help() {
        System.out.println("************************************************************");
        System.out.println("                  STAR WARS COMMANDS MENU                   ");
        System.out.println("************************************************************");

        System.out.println("  JEDI MANAGEMENT (Creation, Removal, Rank)");
        System.out.println("------------------------------------------------------------");
        System.out.println(" 1. Add Planet:         add_planet <planet_name>");
        System.out.println(" 2. Create Jedi:        create_jedi <planet> <name> <rank> <age> <saber> <strength>");
        System.out.println(" 3. Remove Jedi:        remove_jedi <jedi_name> <planet_name>");
        System.out.println(" 4. Promote Jedi:       promote_jedi <jedi_name> <multiplier>");
        System.out.println(" 5. Demote Jedi:        demote_jedi <jedi_name> <multiplier>");
        System.out.println();

        System.out.println("  PLANET & QUERY OPERATIONS (Statistics and Details)");
        System.out.println("------------------------------------------------------------");
        System.out.println(" 6. Get Strongest Jedi: get_strongest_jedi <planet_name>");
        System.out.println(" 7. Get Youngest Jedi:  get_youngest_jedi <planet_name> <jedi_rank>");
        System.out.println(" 8. Most Used Saber:    get_most_used_saber_color <planet_name> <jedi_rank>");
        System.out.println(" 9. Most Used Saber (All): get_most_used_saber_color <planet_name>");
        System.out.println("10. Show Planet Details: print_planet <planet_name>");
        System.out.println("11. Show Jedi Details:   print_jedi <jedi_name>");
        System.out.println("12. Compare Planets:     print_jedis_for_planets <planet1> <planet2>");
        System.out.println();

        System.out.println("  FILE & PROGRAM CONTROL (System Commands)");
        System.out.println("------------------------------------------------------------");
        System.out.println("13. Exit Program:      exit");
        System.out.println("14. Show Menu:         help");
        System.out.println("15. Open file:         open <file>");
        System.out.println("16. Close file:        close");
        System.out.println("17. Save file:         save");
        System.out.println("18. Save As:           saveas <file>");
        System.out.println("************************************************************");
    }

}
