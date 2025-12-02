public class Moon {
    private String name;
    private Jedi jedi;

    public Moon(String name, Jedi jedi) {
        this.name = name;
        this.jedi = jedi;
    }

    public String getName() {
        return name;
    }
    public Jedi getJedi() {
        return jedi;
    }

    public void setJedi(Jedi jedi) {
        this.jedi = jedi;

    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Moon{" +
                "name='" + name + '\'' +
                ", jedi=" + jedi +
                '}';
    }
}
