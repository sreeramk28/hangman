import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import java.util.Random;

class Menu extends JFrame {
    JPanel p1;
    JButton newgame, checkhistory, exit;
    Menu() {
        newgame = new JButton("NEW GAME");
        checkhistory = new JButton("VIEW PROGRESS");
        exit = new JButton("EXIT");
        newgame.addActionListener(new ListentoNewGame());
		p1 = new JPanel();
        p1.add(newgame);
        p1.add(checkhistory);
        p1.add(exit);
        add(p1);
    }
    class ListentoNewGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new Player().startGame();
        }
    }
    /*class ListentoViewProgress implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            (new Player()).saveGame();
        }
    }
    class ListentoExit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new Player.exitGame();
        }
    }*/
}
class Bodyparts extends JPanel {

    int mistake_number;

    public void paintBody(int mistake_num) {
        mistake_number = mistake_num;
    }

    @Override
    public void paint(Graphics g) {
        /*parts drawing*/
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if (mistake_number == 0) {
            return;
        }
        if (mistake_number >= 1) {
            Ellipse2D eli;
            eli = new Ellipse2D.Double(240, 62, 20, 20);
            g2.draw(eli);
        }
        if (mistake_number >= 2) {
            Line2D blin;
            blin = new Line2D.Double(250, 82, 250, 102);
            g2.draw(blin);
        }
        if (mistake_number >= 3) {
            Line2D blin;
            blin = new Line2D.Double(250, 102, 230, 122);
            g2.draw(blin);
        }
        if (mistake_number >= 4) {
            Line2D blin;
            blin = new Line2D.Double(250, 102, 270, 122);
            g2.draw(blin);
        }
        if (mistake_number >= 5) {
            Line2D blin;
            blin = new Line2D.Double(250, 92, 230, 82);
            g2.draw(blin);
        }
        if (mistake_number >= 6) {
            Line2D blin;
            blin = new Line2D.Double(250, 92, 270, 82);
            g2.draw(blin);
        }
    }
}
class GamePlay extends JFrame {
    // COPY FROM HANGMAN FRAME IN REPO WITH LITTLE TWEAKS
	public static Integer num_mistakes = 0;
    private JLabel mist, over, clue;
    private JPanel jp, jp1;
    private JTextField mistake_count;
    private JTextField[] letter;
    private JTextField guess;
    private JButton submit,replay;
    public static String ww;
    Bodyparts bp;
	public GamePlay(String w, String cat) {
        this.ww = w;
		num_mistakes = 0;
		clue = new JLabel("");
		clue.setText(cat);
		//ww = new SetofWords().getWord();
		letter = new JTextField[ww.length()];
		submit = new JButton("GUESS");
		replay = new JButton("REPLAY");
        jp = new JPanel();
        jp1 = new JPanel();
        mist = new JLabel("Mistakes");
        over = new JLabel("");
		
        over.setHorizontalAlignment(JLabel.LEFT);
        mistake_count = new JTextField(5);
        mistake_count.setHorizontalAlignment(JTextField.RIGHT);
        mistake_count.setEditable(false);
        /*guess*/
        guess = new JTextField(2);
        guess.setHorizontalAlignment(JTextField.RIGHT);
        guess.setEditable(true);
		
        for (int u = 0; u < ww.length(); u++) {
            letter[u] = new JTextField(2);
            letter[u].setHorizontalAlignment(JTextField.LEFT);
            letter[u].setEditable(false);
        }
        submit.addActionListener(new ListenToSubmit());
		//replay.addActionListener(new ListenToReplay(this));
        jp1.add(clue);
		for (int u = 0; u < ww.length(); u++) {
            jp1.add(letter[u]);
        }
		clue.setHorizontalAlignment(JLabel.RIGHT);
        
		jp1.add(mist);
        jp1.add(mistake_count);
        jp1.add(over);
        jp.add(guess);
        jp.add(submit);
		jp.add(replay);
        add(jp1, BorderLayout.NORTH);
        add(jp, BorderLayout.SOUTH);
        bp = new Bodyparts();
        add(bp);
    }
	@Override
    public void paint(Graphics g) {
        /* Gallow Drawing */
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin;
        lin = new Line2D.Float(300, 100, 300, 250);
        g2.draw(lin);
        lin = new Line2D.Float(260, 100, 300, 100);
        g2.draw(lin);
        lin = new Line2D.Float(260, 100, 260, 120);
        g2.draw(lin);
        lin = new Line2D.Float(280, 250, 320, 250);
        g2.draw(lin);

    }
	 class ListenToSubmit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
			String p = "";
			int f;
            int[] pos = new int[50];
            //int k = 0;
            char let = guess.getText().charAt(0);
            f = Game.checkPresence(let, pos);
			
			//System.out.println(pos.length); System.out.println();
			if(f == -1) {
				num_mistakes++;
                mistake_count.setText(num_mistakes.toString());
                bp.paintBody(num_mistakes);
                repaint();
                if (num_mistakes == 6) {
                    over.setText("GAME OVER, YOU LOST!");
					for(int i=0; i<ww.length(); i++) {
						p = "";
						letter[i].setText(p += ww.charAt(i));
					}	
                    submit.setVisible(false);
                    guess.setVisible(false);
				}	
				
			}	
			else {
				for (int i = 0; i <= f; i++) {
                    p = "";
                    letter[pos[i]].setText(p += let);
					//System.out.println("Hello");
                }
				int ll = 0;
                for (int u = 0; u < ww.length(); u++) {
                    String s = letter[u].getText();
                    ll += s.length();
                }
                if (ll == ww.length()) {
                    over.setText("CONGRATS, YOU WON!!");
                    submit.setVisible(false);
                    guess.setVisible(false);
					//replay.setVisible(true);
                }
			}
			guess.setText("");
            repaint();
		}
	 }	
}
class Player
{
	private String name;
	
	Player() {
	    name = "";
	}
	
	void startGame() {
	    // CREATE GAME OBJECT
		Game gm = new Game();
		gm.playGame();
	}
	
	void getHistory() {
	    
	}
}

class Game {
    private int sessionID, thecategoryidx;
	private static String theword, thecategory;
	private static int mistakes_count;
    public static int checkPresence(char let, int pos[]) {
        
		int k = -1, f = 0;
        String p = "";
        for (int i = 0; i < theword.length(); i++) {
            if (theword.charAt(i) == let) {
                    f = 1;
                    pos[++k] = i;
            }
        }
		if(f == 0) {
			mistakes_count++;	
		}
		return k;
	}
    public void playGame() {
		mistakes_count = 0;
        // LOGIC FOR EVERYTHING + compute score
		//get word, send it to ui for squares filling
		//get the guess, get score and all
		SetofWords sow = new SetofWords();
		thecategoryidx = sow.getCategoryIndex();
		thecategory = sow.getCategory(thecategoryidx);
		theword = sow.getWord(thecategoryidx);
		GamePlay ui = new GamePlay(theword, thecategory);
		ui.setSize(1000, 563);
        ui.setLocationRelativeTo(null);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setVisible(true);
    }
    
    public void saveGame() {
        // save the score and words played in file
        // close the files
		;
    }
    public void exitGame() {
        // save the game and exit
		;
    }
}

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
public class Hangman {
    public static void main(String[] args) {
        // create menu object and kick things off!!
		Menu l = new Menu();
		l.setSize(1000, 563);
        l.setLocationRelativeTo(null);
        l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l.setVisible(true);
    }
}

