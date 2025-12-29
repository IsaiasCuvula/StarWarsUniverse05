package commands.datastorage;

import commands.Command;
import datastorage.DataStorage;
import models.Jedi;
import universes.Universe;
import models.Planet;

import java.util.List;

public class OpenCommand extends Command {
   
    public OpenCommand(Universe universe, DataStorage storage) {
        super(universe, storage);
    }
    
    @Override
    public void execute(String[] args) throws Exception {
        validateArgsCount(args, 2);
        String filename = args[1];
        
        storage.open(filename);
        
        List<Planet> loadedPlanets = storage.loadPlanets();
        //Close immediately to avoid memory leak
        storage.close();
        
        List<Planet> currentMemoryPlanets = universe.getPlanets();
        
        for (Planet loaded : loadedPlanets) {
            Planet existingPlanet = findPlanetInList(
                    currentMemoryPlanets, loaded.getName());
            
            if (existingPlanet != null) {
                for (Jedi jedi : loaded.getJediList()) {
                    if (existingPlanet.getJediByName(jedi.getName()) == null) {
                        existingPlanet.addJedi(jedi);
                    }
                }
            } else {
                currentMemoryPlanets.add(loaded);
            }
        }
    
        universe.setPlanets(currentMemoryPlanets);
        universe.setCurrentFilename(filename);
        
        System.out.printf("> Success: File '%s' opened and %d planets loaded into memory.%n",
                filename, loadedPlanets.size());
    }
    
    private Planet findPlanetInList(List<Planet> list, String name) {
        Planet result = null;
        for(Planet p: list){
            if(p.getName().equalsIgnoreCase(name)){
                result = p;
                break;
            }
        }
        return result;
    }
}