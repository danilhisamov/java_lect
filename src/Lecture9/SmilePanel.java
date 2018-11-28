package Lecture9;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

public class SmilePanel extends JPanel {

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.YELLOW);
        int r = getWidth() / 2 - 100;
        g2.fillOval(getWidth() / 2 - r, getHeight() / 2 - r, 2 * r, 2 * r);

        int eyeR = 30;
        g2.setColor(Color.BLACK);
        g2.fillOval(getWidth() / 2 - eyeR * 4, getHeight() / 4, eyeR * 2, eyeR * 2);
        g2.fillOval(getWidth() / 2 + eyeR * 2, getHeight() / 4, eyeR * 2, eyeR * 2);

        int smileWidth = 2 * r - 300;
        int smileHeight = r / 2;
        g2.setStroke(new BasicStroke(10));
        g2.draw(new Arc2D.Double(getWidth() / 2.0 - smileWidth / 2.0, getHeight() / 2.0 + smileHeight / 2.0,
                smileWidth,
                smileHeight,
                200, 140,
                Arc2D.OPEN));
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Window Frame");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(800, 800);
        jFrame.setLocationRelativeTo(null); // centralize
        jFrame.getContentPane().add(new SmilePanel());
        jFrame.setVisible(true);
    }
}
