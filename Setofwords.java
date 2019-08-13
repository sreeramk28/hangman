
import java.util.Random;

public class Setofwords {
    String[] words = new String[10];
    
    public Setofwords() {
        words[0] = "INDIA";
        words[1] = "AUSTRALIA";
        words[2] = "SOUTH_AFRICA";
        words[3] = "JAPAN";
        words[4] = "CHINA";
        words[5] = "CANADA";
        words[6] = "MALDIVES";
        words[7] = "ZIMBABWE";
        words[8] = "PORTUGAL";
        words[9] = "RUSSIA";
    }
    
    public String getWord() {
        Random rand = new Random();
        int i;
        i = rand.nextInt(10);
        return words[i];
    }
}
