import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Solitaire {

    private static Controller controller;
    public static void main(String[] args) {

        // Lists Look and Feel available
        for(UIManager.LookAndFeelInfo lafInfo : UIManager.getInstalledLookAndFeels()){
            System.out.println(lafInfo.getClassName());
        }

        // Applies Look and Feel UI
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        controller = new Controller();

    }


}