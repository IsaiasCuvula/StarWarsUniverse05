import java.util.ArrayList;
import java.util.List;

public class Moon {
    private String name;
    private final List<Jedi> inhabitants = new ArrayList<>();

    public Moon(String name) {
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
        return "Moon name " + name;
    }
}
