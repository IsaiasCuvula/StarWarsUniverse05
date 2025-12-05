import java.util.ArrayList;
import java.util.List;

public class Planet {
    private String name;
    private final List<Jedi> jediList = new ArrayList<>();

    public Planet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Jedi> getJediList() {
        return jediList;
    }

    public void addJedi(Jedi jedi) {
        if(jedi == null){
             throw new IllegalArgumentException("Jedi cannot be null");
        }
        jediList.add(jedi);
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Planet name " + name;
    }
}
