package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;


public class TimerAndrei {

    static JLabel labelResult;
    static JLabel labelInfo;
    static Timer myTimerAndrei;
    static int contor = 0;

    public TimerAndrei()
    {
        createInterface();
    }

    public static void main(String[] args) {
        createInterface();
    }

    private static void updateLabel()
    {
        labelResult.setText("My name is Andrei " + contor);
    }

    private static void initializeTimer()
    {
        myTimerAndrei = new Timer();
        myTimerAndrei.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                contor++;
                if(contor >= 7)
                {
                    contor=0;
                }

                Toolkit.getDefaultToolkit().beep();
                updateLabel();
            }
        }, 5*1000, 3*1000);
    }

    private static void createInterface()
    {
        JFrame frame = new JFrame("TimerAndrei");
        JButton START = new JButton("START");
        JButton STOP = new JButton("STOP");
        labelResult = new JLabel();
        labelInfo = new JLabel();

        START.setBounds(25,50,80,35);
        STOP.setBounds(125,50,80,35);
        labelResult.setBounds(60,90,190,35);
        labelInfo.setBounds(5, 10, 230, 35);
        labelResult.setText("");
        labelInfo.setText("Startati Timer-ul prin butonul START");
        frame.add(START);
        frame.add(STOP);
        frame.add(labelResult);
        frame.add(labelInfo);
        frame.setLayout(null);
        frame.setSize(240,180);
        frame.setVisible(true);
        setupActionListenerStartButton(START);
        setupActionListenerStopButton(STOP);
    }

    private static void setupActionListenerStartButton(JButton button)
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myTimerAndrei == null)
                {
                    updateLabel();
                    initializeTimer();
                    labelInfo.setText("Opriti Timer-ul prin butonul STOP");
                }
            }
        });
    }

    private static void setupActionListenerStopButton(JButton button)
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myTimerAndrei.cancel();
                myTimerAndrei.purge();
                myTimerAndrei=null;
                labelInfo.setText("Continua Timer-ul prin butonul START");
            }
        });
    }
}
