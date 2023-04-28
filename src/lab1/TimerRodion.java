package lab1;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimerRodion extends JPanel {
    private Color color;
    private Timer timer;
    private JButton startButton, stopButton;


    public void RodionInterface() {

        JFrame frame = new JFrame("Color Changing Shape");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);

        TimerRodion panel = new TimerRodion();
        frame.add(panel);

        frame.setVisible(true);

    }
    public TimerRodion() {
        color = Color.RED;

        timer = new Timer();


        startButton = new JButton("Start");
        startButton.addActionListener(e -> startTimer());

        stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> stopTimer());

        add(startButton);
        add(stopButton);
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            public void run() {

                color = new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                repaint();
                //timer.cancel();
            }
        },0, 5000);
    }

    private void stopTimer() {
        timer.cancel();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(color);
        g.fillRect(50, 50, 100, 100);
    }
}


/* main
        var rodion = new TimerRodion();
        rodion.RodionInterface();
 */
