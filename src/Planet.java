import java.util.ArrayList;
import java.util.List;

public class Planet {
    private String name;
    private final List<Jedi> inhabitants = new ArrayList<>();

    public Planet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Planet name " + name;
    }
}
