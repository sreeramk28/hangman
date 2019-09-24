
class Menu extends JFrame {
    JPanel p1;
    JButton newgame, checkhistory, exit;
    Menu() {
        newgame = new JButton("NEW GAME");
        checkhistory = new JButton("VIEW PROGRESS");
        exit = new JButton("EXIT");
        p1 = new JPanel();
        p1.add(newgame);
        p1.add(checkhistory);
        p1.add(exit);
        add(p1);
    }
    class ListentoNewGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new Player().startGame()
        }
    }
    class ListentoViewProgress implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new Player().saveGame();
        }
    }
    class ListentoExit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            new Player.exitGame();
        }
    }
}
class GamePlay extends JFrame {
    // COPY FROM HANGMAN FRAME IN REPO WITH LITTLE TWEAKS
}
class Player
{
	private String name;
	
	Player() {
	    name = "";
	}
	
	void startGame() {
	    // CREATE GAME OBJECT
	}
	
	void getHistory() {
	    
	}
}

class Game {
    private int sessionID;
    
    public Game() {
        
    }
    
    public void playGame() {
        // LOGIC FOR EVERYTHING + compute score
    }
    
    public void saveGame() {
        // save the score and words played in file
        // close the files
    }
    public void exitGame() {
        // save the game and exit
    }
}

class SetofWords {
    // SET OF WORDS RIGHT HERE OR GET IT FROM ANOTHER PROGRAM
    public SetofWords() {
        
    }
    public String getWord() {
        //GENERATE RANDOM WORDS FROM SET OF WORDS
    }
}
public class Hangman {
    public static void main(String[] args) {
        // create menu object and kick things off!!
    }
}


