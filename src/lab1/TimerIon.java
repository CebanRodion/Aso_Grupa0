package lab1;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class GUI extends JFrame
{
    Timer timer = new Timer();

    JButton button1 = new JButton();

    public GUI() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(500,500);

        JLabel oraLabel = new JLabel("Setati ora");
        oraLabel.setBounds(50,80,100,50);

        frame.add(oraLabel);

        JTextField oraText = new JTextField();
        oraText.setBounds( 120,100 , 50, 20);
        JTextField minuteText = new JTextField();
        minuteText.setBounds( 170,100 , 50, 20);

        button1.setBounds(120, 125, 100,50);
        button1.setText("Apasa");

        frame.add(minuteText);
        frame.add(oraText);
        frame.add(button1);

        button1.addActionListener(e -> {
            MyTimerTask myTimerTask = new MyTimerTask();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(oraText.getText()));
            calendar.set(Calendar.MINUTE, Integer.parseInt(minuteText.getText()));
            calendar.set(Calendar.SECOND, 0);

            Date date = calendar.getTime();
            timer.schedule(myTimerTask, date);

            System.out.println(date);
        });
    }


}

class MyTimerTask extends TimerTask{

    public void run(){
        Toolkit.getDefaultToolkit().beep();
        System.out.println("Este ora specificata");
    }
}
public class TimerIon {
    public TimerIon()
    {
        GUI gui = new GUI();
    }
    public static void main(String[] args) {

        GUI gui = new GUI();
    }
}
