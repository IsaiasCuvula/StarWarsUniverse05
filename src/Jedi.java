import exceptions.JediException;

public class Jedi {
    private String name;
    private Rank rank;
    private int age;
    private String lightsaberColor;
    private int force;

    public Jedi(String name, String rank, int age, String lightsaberColor, int force)  throws JediException{
        setName(name);
        setRank(rank);
        setAge(age);
        setLightsaberColor(lightsaberColor);
        setForce(force);
    }



    public void setName(String name) throws JediException {
        if(name == null){
            throw new JediException("Invalid Jedi name");
        }
        if(name.trim().isEmpty()){
            throw new JediException("Invalid Jedi name");
        }
        this.name = name;
    }


    public void setRank(String rankString) throws  JediException{
        if(rankString == null){
            throw new JediException("Invalid Jedi Rank");
        }
        if(rankString.trim().isEmpty()){
            throw new JediException("Invalid Jedi Rank");
        }
        Rank rank = Rank.fromString(rankString);

        if(rank == null){
            throw new JediException("Invalid Jedi Rank (YOUNGLING, INITIATE," +
                    "PADAWAN, KNIGHT-ASPIRANT, KNIGHT, MASTER, BATTLE_MASTER and" +
                    "GRAND_MASTER)");
        }
        this.rank = rank;
    }


    public void setAge(int age) throws JediException {
        if(age < 0){
            throw new JediException("Invalid Jedi age");
        }
        this.age = age;
    }

    public void setForce(int force) throws JediException {
        if(force != 1 && force != 2){
            throw new JediException("Enter a valid Jedi Strength (1 to 2)");
        }
        this.force = force;
    }

    public void setLightsaberColor(String lightsaberColor)  throws JediException {
        if(lightsaberColor == null){
            throw new JediException("Invalid light saber color");
        }
        if(lightsaberColor.trim().isEmpty()){
            throw new JediException("Invalid light saber color");
        }
        this.lightsaberColor = lightsaberColor;
    }

    public int getForce() {
        return force;
    }
    public int getAge() {
        return age;
    }
    public String getName() {
        return name;
    }

    public Rank getRank() {
        return rank;
    }
    public String getLightsaberColor() {
        return lightsaberColor;
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

