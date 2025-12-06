package models;

import exceptions.JediException;

public enum Rank {
    YOUNGLING,
    INITIATE,
    PADAWAN,
    KNIGHT_ASPIRANT,
    KNIGHT,
    MASTER,
    BATTLE_MASTER,
    GRAND_MASTER;

    public Rank stepDownRank(){
        try{
            //The YOUN GLING is the last in this case
            if(this == Rank.YOUNGLING){
                return this;
            }
            int actualIndex = this.ordinal();
            return Rank.values()[actualIndex - 1];
        }catch (Exception e){
            return this;
        }
    }

    public Rank stepUpRank(){
        try{
            //The GRAND_MASTER is the last in this case
            if(this == Rank.GRAND_MASTER){
               return this;
            }
            int actualIndex = this.ordinal();
            return Rank.values()[actualIndex + 1];
        }catch (Exception e){
            return this;
        }
    }

    public static Rank fromString(String s) throws  JediException{
        if(s == null) {
           throw new JediException("Invalid Jedi Rank (YOUNGLING, INITIATE," +
                   "PADAWAN, KNIGHT-ASPIRANT, KNIGHT, MASTER, BATTLE_MASTER and" +
                   "GRAND_MASTER)");

        };
        String cleanString = s.trim().toUpperCase();
        return Rank.valueOf(cleanString);
    }
}
