import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Solitaire extends JFrame {

    private static Controller controller;
    JPanel gameArea;
    JPanel columns;
    JPanel topColumns;

    public static void main(String[] args) {

        controller = new Controller();


    }

    public Solitaire(){
        setTitle("Solitaire");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);


    }

}