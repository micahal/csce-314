/*
 * micah alummoottil
 * 4/9/25
 */
import javax.swing.*;
import java.awt.event.*;

public class multiClicker extends JFrame{
    private int count = 0;
    private JLabel label;
    private JButton clickButton;
    private JButton exitButton;

    public multiClicker() {
        super("Cookie Clicker");
        //create count label
        label = new JLabel("Clicks: " + count);
        //create click button
        clickButton = new JButton("Click");
        clickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                label.setText("Clicks: " + count);

            }
        });

        //create exit button

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //exit window
            }
        });

        //layout the components
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(clickButton);
        panel.add(exitButton);

        //setup frame
        add(panel);
        setSize(350, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        //num of counters to launch
        final int numberOfInstances = 4;

        //launch guis
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= numberOfInstances; i++) {
                    multiClicker clicker = new multiClicker();
                    clicker.setVisible(true);
                }
            }
        });
    }
}
