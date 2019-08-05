import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HangMan extends JFrame  {
	public static Integer num_mistakes = 0;
	private JLabel mist, over; 
	private JPanel jp, jp1;
	private JTextField mistake_count, letter0, letter1, letter2, letter3, letter4; 
	private JTextField guess;
	private JButton submit;
	public static String ww = "INDIA";
	public HangMan() {
		submit = new JButton("GUESS");
		jp = new JPanel();
		jp1 = new JPanel();		
		mist = new JLabel("Mistakes");
		over = new JLabel("");
		over.setHorizontalAlignment(JLabel.LEFT);
		mistake_count = new JTextField(5);
		mistake_count.setHorizontalAlignment(JTextField.RIGHT);
		/*guess*/
		guess = new JTextField(2);
		guess.setHorizontalAlignment(JTextField.RIGHT);
		guess.setEditable(true);
		/*guessing*/
		letter0 = new JTextField(2);
		letter1 = new JTextField(2);
		letter2 = new JTextField(2);
		letter3 = new JTextField(2);
		letter4 = new JTextField(2);
		letter0.setHorizontalAlignment(JTextField.LEFT);
		letter0.setEditable(false);	
		letter1.setHorizontalAlignment(JTextField.LEFT);
		letter1.setEditable(false);
		letter2.setHorizontalAlignment(JTextField.LEFT);
		letter2.setEditable(false);
		letter3.setHorizontalAlignment(JTextField.LEFT);
		letter3.setEditable(false);
		letter4.setHorizontalAlignment(JTextField.LEFT);
		letter4.setEditable(false);
		submit.addActionListener(new ListenToSubmit());
		jp1.add(letter0);
		jp1.add(letter1);
		jp1.add(letter2);
		jp1.add(letter3);
		jp1.add(letter4);
		jp1.add(mist);
		jp1.add(mistake_count);
		jp1.add(over);
		jp.add(guess);
		jp.add(submit);
		add(jp1, BorderLayout.NORTH);
		add(jp, BorderLayout.SOUTH);
	}
	class ListenToSubmit implements ActionListener {
		
		public void actionPerformed(ActionEvent ae) {
			int f = 0;
			int[] pos = new int[50];
			int k = 0;
			char let = guess.getText().charAt(0);
			String p = "";
			for(int i=0; i<ww.length(); i++) {
				if(ww.charAt(i) == let) {
					f = 1;
					pos[k++] = i;
				}
			}
			if(f == 0) {
				num_mistakes++;
				mistake_count.setText(num_mistakes.toString());
				if(num_mistakes == 6) {
					over.setText("GAME OVER, YOU LOST!");
					submit.setVisible(false);
					guess.setVisible(false);
				}
				
			}	
			else {
				for(int i=0; i<k; i++) {
					p = "";
					switch(pos[i]) {
						case 0 : letter0.setText(p += let); break;
						case 1 : letter1.setText(p += let); break;
						case 2 : letter2.setText(p += let); break;
						case 3 : letter3.setText(p += let); break;
						case 4 : letter4.setText(p += let); break;
					}
				}
				String l1 = letter1.getText();
				String l2 = letter2.getText();
				String l3 = letter3.getText();
				String l4 = letter4.getText();
				String l0 = letter0.getText();
				if(l1.length() > 0 && l2.length() > 0 
					&& l3.length() > 0 && l4.length() > 0 
					&& l0.length() > 0 ) {
					over.setText("CONGRATS, YOU WON!!");
					submit.setVisible(false);
					guess.setVisible(false);
				}			
			}	
			
		}
	}	
	public static void main(String[] args) {
		HangMan l = new HangMan();
		l.setSize(450, 300);
		//l.pack();
		l.setLocationRelativeTo(null);
		l.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		l.setVisible(true);
	}
		
}	
