/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arun_y99, sreeramk100
 */
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Hangman extends JFrame {

    public static Integer num_mistakes = 0;
    private JLabel mist, over;
    private JPanel jp, jp1;
    private JTextField mistake_count;
    private JTextField[] letter = new JTextField[ww.length()];
    private JTextField guess;
    private JButton submit;
    public static String ww = "SRINIVASA_ARUN";

    public Hangman() {
        submit = new JButton("GUESS");
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
        for (int u = 0; u < ww.length(); u++) {
            jp1.add(letter[u]);
        }
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
            for (int i = 0; i < ww.length(); i++) {
                if (ww.charAt(i) == let) {
                    f = 1;
                    pos[k++] = i;
                }
            }
            if (f == 0) {
                num_mistakes++;
                mistake_count.setText(num_mistakes.toString());
                if (num_mistakes == 6) {
                    over.setText("GAME OVER, YOU LOST!");
                    submit.setVisible(false);
                    guess.setVisible(false);
                }
            } else {
                for (int i = 0; i < k; i++) {
                    p = "";
                    letter[pos[i]].setText(p += let);

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
                }
            }
        }
    }

    public static void main(String[] args) {
        Hangman l = new Hangman();
        l.setSize(450, 300);
        l.setLocationRelativeTo(null);
        l.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        l.setVisible(true);
    }
}
