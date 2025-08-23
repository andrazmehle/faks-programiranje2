package izpit2;


import edu.princeton.cs.algs4.StdDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer {
    public static void main(String[] args) throws InterruptedException {
        final int DOLZINA = 30 * 60;



        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Timer");
        frame.setVisible(true);
        frame.setSize(250, 100);

        JTextArea time = new JTextArea();
        time.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        frame.add(time);


        final int[] i = {DOLZINA};
            while (i[0] >= 0) {
                time.setText(Integer.toString(i[0] / 60) + ":" + (i[0] % 60));
                Thread.sleep(1000);
                i[0]--;
            }

            frame.toFront();
            frame.setLocationRelativeTo(null);
            time.setBackground(Color.RED);
            Toolkit.getDefaultToolkit().beep();
    }
}
