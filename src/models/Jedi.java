package models;

import exceptions.JediException;

import java.text.DecimalFormat;

public class Jedi {
    private String name;
    private Rank rank;
    private int age;
    private String lightsaberColor;
    private double strength;

    public Jedi(String name, String rank, int age, String lightsaberColor, double strength)  throws JediException{
        setName(name);
        setRank(rank);
        setAge(age);
        setLightsaberColor(lightsaberColor);
        setStrength(strength);
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
        this.rank = Rank.fromString(rankString);
    }


    public void setAge(int age) throws JediException {
        if(age < 0){
            throw new JediException("Invalid Jedi age");
        }
        this.age = age;
    }

    public void setStrength(double strength) throws JediException {
        if(strength < 0 || strength > 2){
            throw new JediException("Enter a valid Jedi Strength (1 to 2)");
        }
        DecimalFormat df = new DecimalFormat("#.00");
        this.strength = Double.parseDouble(df.format(strength));
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

    public double getStrength() {
        return strength;
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
                "  force=" + strength + "\n" +
                "}";
    }
}

