package Lecture9;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class WindowPanel extends JPanel {
    private boolean isDay = true;

    public WindowPanel() {
        JButton toggle = new JButton("Toggle Day / Night");
        setLayout(new GroupLayout(this));
        toggle.addActionListener(e -> {
            isDay = !isDay;
            repaint();
        });
        toggle.setSize(200, 50);
        toggle.setLocation(0, 0);
        add(toggle);
    }

    public void paintComponent(Graphics g) { // JFrame doesn't have this method
        super.paintComponent(g);
        setBackground(isDay ? Color.BLUE : Color.BLACK);
        drawEllipse((Graphics2D) g, isDay ? Color.YELLOW : Color.WHITE);
        drawWindow((Graphics2D) g);
        drawFlower((Graphics2D) g);
    }

    private void drawWindow(Graphics2D g2) {
        Stroke oldStroke = g2.getStroke();

        int lineWidth = 10;
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(lineWidth));

        g2.draw(new Line2D.Double(0, 0, 0, getHeight())); // left border
        g2.draw(new Line2D.Double(0, getHeight(), getWidth(), getHeight())); // bottom border
        g2.draw(new Line2D.Double(getWidth(), getHeight(), getWidth(), 0)); // right border
        g2.draw(new Line2D.Double(0, 0, getWidth(), 0)); // top border
        g2.draw(new Line2D.Double(getWidth() / 2, 0, getWidth() / 2, getHeight())); // middle line

        g2.setStroke(oldStroke);
    }

    private void drawEllipse(Graphics2D g2, Color color) {
        Color oldColor = g2.getColor();
        g2.setColor(color);

        int r = 300;
        int x = getWidth();
        int y = getInsets().top;
        x = x - (r / 2);
        y = y - (r / 2);
        g2.fillOval(x, y, r, r);

        g2.setColor(oldColor);
    }

    private void drawFlower(Graphics2D g2) {
        Color oldColor = g2.getColor();

        g2.setColor(new Color(222, 184, 135));
        int potSize = 100;
        int potStartX = getWidth() / 4 - potSize / 2;
        int potStartY = getHeight() - potSize;
        g2.fill(new Rectangle2D.Double(potStartX, potStartY, potSize, potSize));

        g2.setColor(Color.GREEN);
        int flowerStickHeight = 150;
        int flowerStickWidth = 16;
        int flowerStickStartX = getWidth() / 4 - flowerStickWidth / 2;
        int flowerStickStartY = potStartY - flowerStickHeight;
        g2.fill(new Rectangle2D.Double(flowerStickStartX, flowerStickStartY, flowerStickWidth, flowerStickHeight));

        g2.setColor(Color.PINK);
        int flowerCircleRad = 50;
        g2.fillOval(getWidth() / 4 - flowerCircleRad / 2, flowerStickStartY - flowerCircleRad, flowerCircleRad, flowerCircleRad);

        g2.setColor(oldColor);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Window Frame");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(800, 800);
        jFrame.setLocationRelativeTo(null); // centralize
        jFrame.getContentPane().add(new WindowPanel());
        jFrame.setVisible(true);
    }
}
