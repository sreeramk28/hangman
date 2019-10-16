import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.util.Random;

class Menu extends JFrame {
    JPanel p1;
    JButton newgame, checkhistory, exit;
    Menu() {
        newgame = new JButton("NEW GAME");
        checkhistory = new JButton("VIEW PROGRESS");
        exit = new JButton("EXIT");
        newgame.addActionListener(new ListentoNewGame());
		checkhistory.addActionListener(new ListentoViewProgress());
		p1 = new JPanel();
        p1.add(newgame);
        p1.add(checkhistory);
        p1.add(exit);
        add(p1);
    }
    class ListentoNewGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae)  {
            try {
				try {
				new Player().startGame(); 
				}
				catch (IOException ie) {
					System.out.println("IOE");
				}	
			}
			catch (ClassNotFoundException cnfe) {
				System.out.println("CNFE");
			}	
        }
    }
    class ListentoViewProgress implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            (new Player()).saveGame();
        }
    }
    /*class ListentoExit implements ActionListener {
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
	public static int getMistakeCount() {
		return num_mistakes;
	}
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
class Dialog extends JFrame {
	JLabel L;
	public Dialog(Integer mis) {
		String s1 = "Score = ";
		Integer val = 6 - mis;
		String s = val.toString();
		s1 += s;
		L = new JLabel("");
		L.setText(s1);
		add(L);
	}
}	
class Player
{
	private String name;
	
	Player() {
	    name = "";
	}
	
	void startGame() throws IOException, ClassNotFoundException {
	    // CREATE GAME OBJECT
		Game gm = new Game();
		gm.playGame();
	}
	
	void saveGame() {
	    int mistakes = GamePlay.getMistakeCount();
		Dialog db = new Dialog(mistakes);
		db.setSize(150, 150);
        db.setLocationRelativeTo(null);
        db.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        db.setVisible(true);
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
    public void playGame() throws IOException, ClassNotFoundException {
		mistakes_count = 0;
        // LOGIC FOR EVERYTHING + compute score
		//get word, send it to ui for squares filling
		//get the guess, get score and all
		SocketServerExample sse = new SocketServerExample();
		thecategory = sse.retCategory();
		theword = sse.retWord();
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

