import exceptions.PlanetException;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    private String name;
    private final List<Jedi> jediList = new ArrayList<>();

    public Planet(String name)  throws PlanetException {
        setName(name);
    }

    public void addJedi(Jedi jedi)throws PlanetException {
        if(jedi == null){
             throw new PlanetException("Jedi cannot be null");
        }
        if(jediList.contains(jedi)){
            throw new PlanetException("Jedi already exist");
        }
        jediList.add(jedi);
    }

    public void setName(String name) throws PlanetException {
        if (name == null){
            throw new PlanetException("Name cannot be null");
        }
        if (name.trim().isEmpty()){
            throw new PlanetException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Jedi> getJediList() {
        return jediList;
    }

    @Override
    public String toString() {
        return "Planet name " + name;
    }
}
