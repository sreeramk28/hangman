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
	private JLabel mist; 
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
		//for(int i=0; i<5; i++) {
			letter0.setHorizontalAlignment(JTextField.LEFT);
			letter0.setEditable(true);
		//}	
		letter1.setHorizontalAlignment(JTextField.LEFT);
		//letter1.setEditable(true);
		letter2.setHorizontalAlignment(JTextField.LEFT);
		//letter2.setEditable(true);
		letter3.setHorizontalAlignment(JTextField.LEFT);
		//letter3.setEditable(true);
		letter4.setHorizontalAlignment(JTextField.LEFT);
		//letter4.setEditable(true);
		//letter5.setHorizontalAlignment(JTextField.LEFT);
		//letter5.setEditable(true);
		
			//password.setHorizontalAlignment(JTextField.LEFT);
			//password.setEditable(true);
		submit.addActionListener(new ListenToSubmit());
		jp1.add(letter0);
		jp1.add(letter1);
		jp1.add(letter2);
		jp1.add(letter3);
		jp1.add(letter4);
		
		jp1.add(mist);
		jp1.add(mistake_count);
		jp.add(guess);
		jp.add(submit);
		//add(jp); 
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
				//String p= "";
				if(ww.charAt(i) == let) {
					f = 1;
					/*switch(i) {
						case 0 : letter0.setText(p += let); break;
						case 1 : letter1.setText(p += let); break;
						case 2 : letter2.setText(p += let); break;
						case 3 : letter3.setText(p += let); break;
						case 4 : letter4.setText(p += let); break;
					}*/
					pos[k++] = i;
				}
			}
			//for(int i=0; i<k; i++) System.out.print(pos[i]);
			//System.out.println();
			if(f == 0) {
				num_mistakes++;
				mistake_count.setText(num_mistakes.toString());
				
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
			}	
			
		}
	}	
	public static void main(String[] args) {
		HangMan l = new HangMan();
		l.setSize(600, 400);
		//l.pack();
		l.setLocationRelativeTo(null);
		l.setDefaultCloseOperation(EXIT_ON_CLOSE);
		l.setVisible(true);
	}
		
}	
