
public class Jedi {
    private String name;
    private Rank rank;
    private int age;
    private String lightsaberColor;
    private int force;

    public Jedi(String name, Rank rank, int age, String lightsaberColor, int force) {
        this.name = name;
        this.rank = rank;
        this.age = age;
        this.lightsaberColor = lightsaberColor;
        this.force = force;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLightsaberColor() {
        return lightsaberColor;
    }

    public void setLightsaberColor(String lightsaberColor) {
        this.lightsaberColor = lightsaberColor;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    @Override
    public String toString() {
        return "Jedi {\n" +
                "  name='" + name + "',\n" +
                "  rank=" + rank + ",\n" +
                "  age=" + age + ",\n" +
                "  lightsaberColor='" + lightsaberColor + "',\n" +
                "  force=" + force + "\n" +
                "}";
    }
}

