/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arun_y99, sreeramk100, (c) 2019
 */
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

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

class Hangman extends JFrame {

    public static Integer num_mistakes = 0;
    private JLabel mist, over;
    private JPanel jp, jp1;
    private JTextField mistake_count;
    private JTextField[] letter = new JTextField[ww.length()];
    private JTextField guess;
    private JButton submit;
    public static String ww = "HELLO";
    Bodyparts bp;

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
                bp.paintBody(num_mistakes);
                repaint();
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
            repaint();
        }
    }
    public static void main(String[] args) {
        Hangman l = new Hangman();
        l.setSize(750, 563);
        l.setLocationRelativeTo(null);
        l.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        l.setVisible(true);
    }
}
