package priprava;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Naloga24 {
    public static void main(String[] args) {
        JFrame okno = new JFrame("Urejevalnik");
        okno.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea text = new JTextArea();
        text.setPreferredSize(new Dimension(500, 500));
        okno.add(text, BorderLayout.CENTER);

        JPanel spodaj = new JPanel();
        okno.add(spodaj, BorderLayout.PAGE_END);

        JButton odpri = new JButton("Odpri");
        spodaj.add(odpri);
        JButton shrani = new JButton("Shrani");
        spodaj.add(shrani);

        final JFileChooser dat = new JFileChooser();

        odpri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JFileChooser.APPROVE_OPTION == dat.showOpenDialog(null)){
                    try {
                        Scanner sc = new Scanner(dat.getSelectedFile());
                        text.setText("");
                        while (sc.hasNextLine()){
                            text.append(sc.nextLine());
                            text.append("\n");
                        }
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        shrani.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (PrintWriter pw = new PrintWriter(dat.getSelectedFile())) {
                    System.out.println(text.getText());
                    pw.print(text.getText());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        okno.pack();
        okno.setVisible(true);

        
    }
}
