import java.awt.*;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.fazecast.jSerialComm.*;

public class StartScreen extends JPanel{
    
    private SerialPort[] allPorts;
    private SerialPort[] activePorts;
    private int hgap = 5;
    private int vgap = 5;
    private JPanel comPanel, startPanel;
    private JComboBox comSelector;
    private JButton startButton, detectPortsButton;

    StartScreen() {
        detectPorts();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        comPanel = new JPanel();
        comPanel.setPreferredSize(new Dimension(300,300));
        comPanel.setLayout(new BorderLayout(hgap,vgap));
        comPanel.setBorder(new EmptyBorder(10,10,10,10));
        JPanel test = new JPanel();
        test.setPreferredSize(new Dimension(100,10));
        String[] testPorts = {"Test1", "Test2", "Test3"};
        comSelector = new JComboBox<>(testPorts);
        comSelector.setSize(new Dimension(100,10));
        detectPortsButton = new JButton("Detect COM Ports");
        detectPortsButton.setPreferredSize(new Dimension(30,10));
        test.add(comSelector);
        comPanel.add(test, BorderLayout.CENTER);
        comPanel.add(detectPortsButton, BorderLayout.SOUTH);
        this.add(comPanel);
    }

    public void detectPorts() {
        allPorts = SerialPort.getCommPorts();
    }

    // public void detectPorts() {
    //     allPorts = SerialPort.getCommPorts();
    //     for (int i = 0; i < allPorts.length;i++) {
    //         if (handShake(allPorts[i])) {
    //             for (int j = 0; j < activePorts.length; j++) {
    //                 if (activePorts[j] != null) {
    //                     activePorts[j+1] = allPorts[i];
    //                     break;
    //                 }
    //             }
    //         }
    //     }
    // }

    private boolean handShake(SerialPort port) {
        String messageString = "I can only show you the door";
        byte[] message = messageString.getBytes();
        port.writeBytes(message, message.length);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String replyString = "you're the one that has to walk through it";
        byte[] reply = replyString.getBytes();
        port.readBytes(message, reply.length);
        if (Arrays.equals(message, reply)) {
            return true;
        }
        return false;
    }
}
