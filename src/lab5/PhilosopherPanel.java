package lab5;

import javax.swing.*;
import java.awt.*;

class PhilosopherPanel extends JPanel {

    private int diameter = 50;
    private Color color;

    public PhilosopherPanel(Color color) {
        this.color = color;
        setPreferredSize(new Dimension(diameter, diameter));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(0, 0, diameter, diameter);
    }

}
