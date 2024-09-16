import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args) {
        //exo1();
        //exo2();
        exo3();
    }

    // Exercice n°1 : afficher une fenêtre en fixant son titre, sa position et sa taille.
    private static void exo1() {
        JFrame frame = new JFrame("Java Avancée - TP2 Exo 1");
        frame.setSize(800, 600);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        //Display the window.
        frame.setVisible(true);
    }

    // Exercice n°2 : afficher une fenêtre et faire un déplacement horizontal continue.
    private static void exo2() {
        int speedX = 5;
        int speedY = 5;

        JFrame frame = new JFrame("Java Avancée - TP2 Exo 2");
        frame.setSize(300, 200);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(100, 200);

        //Display the window.
        frame.setVisible(true);

        while (true) {
            frame.setLocation((int) (frame.getLocation().getX() + speedX), (int) (frame.getLocation().getY() + speedY));

            try {
                Thread.sleep(10);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }

            if ((frame.getLocation().getX() < 0) || (frame.getLocation().getX() + frame.getWidth() > dim.width))
            {
                speedX = -speedX;
            }

            if ((frame.getLocation().getY() < 0) || (frame.getLocation().getY() + frame.getHeight() > dim.height))
            {
                speedY = -speedY;
            }

            frame.validate();
        }
    }

    /* Exercice n°3 : afficher une fenêtre et ajouter un écouteur de souris qui lors d'un clique,
    déplace à une position aléatoire la fenêtre */
    private static void exo3() {
        new CustomFrameWithListeners("Java Avancée - TP2 Exo 2", 400, 400, 100, 200);
    }
}