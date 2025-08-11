package priprava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kalkulator {
    public static void main(String[] args) {
        JFrame okno = new JFrame("Kalkulator");
        okno.setResizable(false);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel zgoraj = new JPanel();
        okno.add(zgoraj, BorderLayout.PAGE_START);

        JTextField prvo = new JTextField(5);
        zgoraj.add(prvo);

        JTextField operator = new JTextField(2);
        zgoraj.add(operator);

        JTextField drugo = new JTextField(5);
        zgoraj.add(drugo);

        JLabel equals = new JLabel("=");
        equals.setFont(new Font("SansSerif", Font.PLAIN, 20));
        zgoraj.add(equals);

        JTextField rez = new JTextField(5);
        zgoraj.add(rez);

        JButton izracun = new JButton("Izracunaj");
        okno.add(izracun, BorderLayout.PAGE_END);

        okno.pack();
        okno.setVisible(true);

        //action listener
        izracun.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ae){
                int rezultat = 0;
                switch (operator.getText()){
                    case "+": {rezultat = Integer.parseInt(prvo.getText()) + Integer.parseInt(drugo.getText()); break;}
                    case "-": {rezultat = Integer.parseInt(prvo.getText()) - Integer.parseInt(drugo.getText()); break;}
                    case "*": {rezultat = Integer.parseInt(prvo.getText()) * Integer.parseInt(drugo.getText()); break;}
                    case "/": {rezultat = Integer.parseInt(prvo.getText()) / Integer.parseInt(drugo.getText()); break;}
                }
                rez.setText(Integer.toString(rezultat));
            }
        });
    }
}
