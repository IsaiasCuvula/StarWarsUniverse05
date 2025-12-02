public class Planet {
    private String name;
    private Jedi jedi;
    public Planet(String name, Jedi jedi) {
        this.name = name;
        this.jedi = jedi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Jedi getJedi() {
        return jedi;
    }

    public void setJedi(Jedi jedi) {
        this.jedi = jedi;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "name='" + name + '\'' +
                ", jedi=" + jedi +
                '}';
    }
}
