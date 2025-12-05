import java.util.Locale;

public enum Rank {
    YOUNGLING,
    INITIATE,
    PADAWAN,
    KNIGHT_ASPIRANT,
    KNIGHT,
    MASTER,
    BATTLE_MASTER,
    GRAND_MASTER;

    public static Rank fromString(String s){
        try{
            if(s == null) return null;
            String cleanString = s.trim().toUpperCase();
            return Rank.valueOf(cleanString);
        }catch (Exception e){
            return null;
        }
    }
}
