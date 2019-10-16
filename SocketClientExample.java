import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
class SetofWords {
    // SET OF WORDS RIGHT HERE OR GET IT FROM ANOTHER PROGRAM
	private String[][] words = 
	{{"INDIA","AUSTRALIA","SOUTH_AFRICA","JAPAN","CHINA","CANADA","MALDIVES","ZIMBABWE","PORTUGAL","RUSSIA","UNITED_STATES_OF_AMERICA","MEXICO","ARGENTINA","BRAZIL","CHILE","MAURITIUS","PERU","HONDURAS","BOLIVIA","CUBA","HAITI","ICELAND","GERMANY","POLAND","GREECE"}

		,{"FACEBOOK","APPLE","AMAZON","NETFLIX","GOOGLE","YAHOO","XEROX","INFOSYS","TATA","LENOVO","DELL","HEWLETT-PACKARD","SONY","SAMSUNG","NOKIA","WHATSAPP","YOUTUBE","ONE_PLUS","NESTLE","ONIDA"}

			,{"LONDON","PARIS","DUBAI","SINGAPORE","CHENNAI","VENICE","PRAGUE","LISBON","AMSTERDAM","FLORENCE","ROME","BUDAPEST","BRUGES","HANGZHOU","CHONGQING","ZHENGZHOU","HIROSHIMA","NAGASAKI","SAITAMA"}

				,{"THE_LION_KING","THE_GODFATHER","CITIZEN_KANE","THE_SHAWSHANK_REDEMPTION","THE_SHAPE_OF_WATER","INCEPTION","TITANIC","AVATAR","THE_LAST_AIRBENDER","AVENGERS_ENDGAME","LA_LA_LAND","THE_DARK_KNIGHT","THE_TIME_MACHINE"}

					,{"MICHEAL_JACKSON","JEFF_BEZOS","ELON_MUSK","LINUS_TORVALDS","DONALD_TRUMP","NARENDRA_MODI","SATYA_NADELLA","SUNDAR_PICHAI","LARRY_PAGE","ROGER_FEDERER","RAFAEL_NADAL","WILLIAM_SHAKESPEARE"}

						,{"GRAND_THEFT_AUTO","GOD_OF_WAR","ASSASSINS_CREED","NEED_FOR_SPEED","RED_DEAD_REDEMPTION","PRINCE_OF_PERSIA","MORTAL_KOMBAT","FORTNITE","APEX_LEGENDS","CALL_OF_DUTY","ANTHEM","FAR_CRY","PUBG","WOLFENSTEIN","THE_WITCHER","FIFA","SUPER_MARIO","DONKEY_KONG","SKYRIM","CIRCUS_CHARLIE"}

	,{"PRUDENT","ROBUST","MISCELLANEOUS","BROWSING","SPLICING","POLLINATION","PHOTOSYNTHESIS","SORTING","TIME_TRAVEL","MYSTERIOUS","SOS","CERTIFICATION","RECOGNITION","POPULATION","CENSUS"}};
	private String[] categories = {"COUNTRY", "COMPANY", "CITY", "MOVIES", "FAMOUS PERSONALITIES", "VIDEO GAMES", "MISCELLANEOUS"};
    /*public SetofWords() {
        
    }*/
    public String getWord(int i) {
        //GENERATE RANDOM WORDS FROM SET OF WORDS
        //i = rand.nextInt(categories.length);
		Random rand = new Random(System.currentTimeMillis());
		int j = rand.nextInt(words[i].length);
		
		//clue.setText(categories[i]);
        return words[i][j]; 
    }
	public int getCategoryIndex() {
		Random rand = new Random(System.currentTimeMillis());
		int i = rand.nextInt(categories.length);
		return i;
	}	
	public String getCategory(int i) {
		return categories[i];
	}
}
public class SocketClientExample {
	
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
		SetofWords sow = new SetofWords();
		int categoryindex = sow.getCategoryIndex();
		String categorie = sow.getCategory(categoryindex);
		String theword = sow.getWord(categoryindex);
        for(int i=0; i<2;i++){
            //establish socket connection to server
            
			socket = new Socket(host.getHostName(), 9877);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            if(i == 0) oos.writeObject(categorie);
			else if(i == 1) oos.writeObject(theword);
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.print("Message: ");
			int len = message.length();
			while(len-- > 0) System.out.print("X");
			System.out.println();
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
    }
}
