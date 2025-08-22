package domace.dn12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DN12 {
    public static void main(String[] args) {
        JFrame okno = new JFrame("VELIKE CRKE");
        okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        okno.setSize(600, 800);
        okno.setLayout(new GridLayout(1, 3));

        JPanel left = new JPanel();
        JPanel middle = new JPanel(new GridBagLayout());
        JPanel right = new JPanel();
        okno.add(left);
        okno.add(middle);
        okno.add(right);

        JTextArea lt = new JTextArea();
        JTextArea rt = new JTextArea();
        left.add(lt);
        right.add(rt);

        JButton mb = new JButton("Pretvori");
        middle.add(mb);

        mb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rt.setText(lt.getText().toUpperCase());
            }
        });

        okno.pack();
        okno.setVisible(true);


    }
}
