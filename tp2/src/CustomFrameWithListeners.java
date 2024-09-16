import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class CustomFrameWithListeners extends JFrame implements MouseListener
{
    public CustomFrameWithListeners(String title, int width, int height, int x, int y)
    {
        super(); // Constructeur vide de JFrame
        this.setTitle(title);
        this.setSize(width, height);
        this.setLocation(x, y);
        this.addMouseListener(this); // Pose de l'écouteur
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Random rand = new Random();
        Color c = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(rand.nextInt(dim.width-this.getWidth()), rand.nextInt(dim.height-this.getHeight()));
        this.getContentPane().setBackground(c);
        this.validate();
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
